package com.github.dapeng.mesh.auth.util

import com.github.dapeng.core.helper.IPUtils
import com.github.dapeng.mesh.auth.dto.ApiKeyInfo

import scala.collection.mutable

/**
 * @author with struy.
 *         Create by 2018/5/11 11:44
 *         email :yq1724555319@gmail.com
 */

object GateWayUtil {

  private val MULTIPLEIP_CUT_KEY = ","
  private val MASK_CUT_KEY = "/"
  private val ANY_RULE_KEY = "*"
  private val TIMEOUT = 60000L
  val infoCache: mutable.Map[String, ApiKeyInfo] = collection.mutable.Map[String, ApiKeyInfo]()


  /**
   * ip规则详细：
   * 1.单个ip：调用方ip必须与此ip完全匹配
   * 2.多个ip（,间隔）：调用方ip必须为其中一个
   * 3.二进制掩码(/间隔)：matchIpWithMask匹配子网掩码下Ip的合法性
   * 4.* ：默认，放过所有Ip访问
   *
   * @param ips
   * @return
   */
  def checkIpRule(invokeIp: String, ips: String): Boolean = {
    if (null == invokeIp || null == ips) return false
    if (ips.contains(MULTIPLEIP_CUT_KEY)) {
      val ipsArr = ips.split(MULTIPLEIP_CUT_KEY)
      ipsArr.foreach(ip => {
        if (ip.contains(MASK_CUT_KEY)) {
          val splitIp = ip.split(MASK_CUT_KEY)
          if (IPUtils.matchIpWithMask(IPUtils.transferIp(invokeIp), IPUtils.transferIp(splitIp(0)), Integer.valueOf(splitIp(1)))) {
            return true
          }
        } else if (ip == invokeIp) {
          return true
        }
      })
      false
    } else if (ips.contains(MASK_CUT_KEY)) {
      val splitIps = ips.split(MASK_CUT_KEY)
      IPUtils.matchIpWithMask(IPUtils.transferIp(invokeIp), IPUtils.transferIp(splitIps(0)), Integer.valueOf(splitIps(1)))
    }
    else ips == ANY_RULE_KEY || ips == invokeIp
  }
}
