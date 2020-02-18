package com.github.dapeng.mesh.auth.common

import com.github.dapeng.core.{SoaBaseCodeInterface, SoaException}
/**
 * @author with struy.
 *         Create by 2018/6/20 10:17
 *         email :yq1724555319@gmail.com
 */
object Assert {
  def assert(assertion: scala.Boolean, errorCodeEnums: SoaBaseCodeInterface): Unit =
    if (!assertion) throw new SoaException(errorCodeEnums.getCode, errorCodeEnums.getMsg)
}
