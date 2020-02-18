namespace java com.github.dapeng.mesh.auth

/**
* 网关鉴权请求体
**/
struct CheckGateWayAuthRequest {
  /**
  * apiKey
  **/
  1:string apiKey,
  /**
  * 时间戳
  **/
  2:string timestamp,
  /**
  * 密匙(apikey+timestamp+password)
  **/
  3:optional string secret,
  /**
  * 调用者ip
  **/
  4:string invokeIp,

  /**
  * 本次请求的请求参数
  **/
  5:optional string parameter

  /**
    * 新的密匙(apikey+timestamp+password+parameter),防止参数篡改
  **/
  6:optional string secret2
}

