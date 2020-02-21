package com.github.dapeng.mesh.auth.common

import com.github.dapeng.core.SoaBaseCodeInterface

/**
 * @author with struy.
 *         Create by 2018/6/20 10:17
 *         email :yq1724555319@gmail.com
 */

class GateWayException(val errorCode: String, val message: String) extends SoaBaseCodeInterface {
  override def getMsg: String = message

  override def getCode: String = errorCode
}

object GateWayException {
  private val bizTag = "Err-GateWay-"

  // 没有对应的apikey
  def NotFoundKey() = new GateWayException(s"${bizTag}001", "认证失败，非法请求")

  // Secret密匙不符合
  def CheckSecretError() = new GateWayException(s"${bizTag}002", "认证失败，非法请求")

  // ip规则不符合
  def CheckIpsError() = new GateWayException(s"${bizTag}003", "认证失败，非法请求")

  // 时效不符合
  def CheckTimeOutError() = new GateWayException(s"${bizTag}004", "请求时间戳失效")

  // APIKEY被禁用
  def ApiKeyDisabled() = new GateWayException(s"${bizTag}005", "认证失败，非法请求")
}
