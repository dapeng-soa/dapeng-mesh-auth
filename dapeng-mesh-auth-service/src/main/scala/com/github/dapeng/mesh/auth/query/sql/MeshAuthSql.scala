package com.github.dapeng.mesh.auth.query.sql

import com.github.dapeng.mesh.auth.common.{Assert, GateWayException}
import com.github.dapeng.mesh.auth.datasource.MeshDataSource
import com.github.dapeng.mesh.auth.dto.ApiKeyInfo
import wangzx.scala_commons.sql._

/**
 * @author with struy.
 *         Create by 2020/2/18 23:08
 *         email :yq1724555319@gmail.com
 */

object MeshAuthSql {

  /**
   * 获取网关apiKey
   *
   * @param apikey
   * @return
   */
  def getApiInfo(apikey: String): ApiKeyInfo = {
    val apiKeyInfo = MeshDataSource.mysqlData.row[ApiKeyInfo](sql"SELECT * FROM api_key_info WHERE api_key = ${apikey}")
    Assert.assert(apiKeyInfo.isDefined, GateWayException.NotFoundKey())
    apiKeyInfo.get
  }

  /**
   * 获取全量apikey信息
   *
   * @return
   */
  def listApiInfo(): List[ApiKeyInfo] = {
    MeshDataSource.mysqlData.rows[ApiKeyInfo](sql"SELECT * FROM api_key_info")
  }
}
