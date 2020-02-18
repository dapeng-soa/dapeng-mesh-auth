package com.github.dapeng.mesh.auth.common

/**
 * @author with struy.
 *         Create by 2018/6/20 10:17
 *         email :yq1724555319@gmail.com
 */
case class Md5String(value : String) {

  lazy private val md5handle = java.security.MessageDigest.getInstance("MD5")
  private val hexDigits = Array[Char]('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

  def md5 : String = {
    val encrypt = md5handle.digest(value.getBytes)
    val b = new StringBuilder(32)
    for (i <- 0.to(15)) {
      b.append(hexDigits(encrypt(i) >>> 4 & 0xf)).append(hexDigits(encrypt(i) & 0xf))
    }
    b.mkString
  }
}
