package com.github.dapeng.mesh.auth.query

import com.github.dapeng.mesh.auth.common.{Assert, GateWayException, Md5String}
import com.github.dapeng.mesh.auth.query.sql.MeshAuthSql
import com.github.dapeng.mesh.auth.util.GateWayUtil
import com.github.dapeng.mesh.scala.auth.CheckGateWayAuthRequest

/**
 * @author with struy.
 *         Create by 2018/6/20 10:17
 *         email :yq1724555319@gmail.com
 */

class CheckGateWayAuthQuery(request: CheckGateWayAuthRequest) {

  private val VALIDATED, EFFECTIVE = 0 // 验证时效 | ApiKey有效

  def execute: Unit = {
    val cacheInfo = GateWayUtil.infoCache.get(request.apiKey)
    val realInfo = cacheInfo match {
      case Some(x) => x
      case _ => {
        val queryInfo = MeshAuthSql.getApiInfo(request.apiKey)
        GateWayUtil.infoCache += (request.apiKey -> queryInfo)
        queryInfo
      }
    }
    // 账号是否禁用
    Assert.assert(realInfo.status == EFFECTIVE, GateWayException.ApiKeyDisabled())
    // 兼容新的secret2,如果请求参数有secret2&parameter，就不走secret
    if (request.parameter.isDefined && request.secret2.isDefined) {
      val realSecret = Md5String(s"${realInfo.apiKey}${request.timestamp}${realInfo.password}${request.parameter.get}").md5
      // 密匙是否匹配
      Assert.assert(realSecret.equals(request.secret2.get), GateWayException.CheckSecretError())
    } else {
      // 必须要有secret
      Assert.assert(request.secret.isDefined, GateWayException.CheckSecretError())
      val realSecret = Md5String(s"${realInfo.apiKey}${request.timestamp}${realInfo.password}").md5
      // 密匙是否匹配
      Assert.assert(realSecret.equals(request.secret.get), GateWayException.CheckSecretError())
    }


    // ip规则是否匹配
    Assert.assert(GateWayUtil.checkIpRule(request.invokeIp, realInfo.ips), GateWayException.CheckIpsError())

    if (realInfo.validated == VALIDATED) {
      // 时效是否符合
      Assert.assert(Math.abs(System.currentTimeMillis - request.timestamp.toLong) < (realInfo.timeout * 1000), GateWayException.CheckTimeOutError())
    }
  }
}
