namespace java com.github.dapeng.mesh.auth.service

include "auth_request.thrift"

/**
* dapeng网关鉴权服务
**/
service MeshAuthService {

/**
# dapeng-mesh鉴权接口
## 业务描述
    api调用鉴权支持
    详情见:https://github.com/dapeng-soa/dapeng-soa/wiki/dapeng-mesh%E9%89%B4%E6%9D%83%E6%96%B9%E6%A1%88
## 接口依赖
    无
## 边界异常说明
    无
## 输入
    服务调用者ip,密匙,时间戳,apiKey
## 前置检查
     无
##  权限检查
     无
##  逻辑处理
    1.将 apiKey+timestamp+password 进行 MD5 后与 secret 匹配验证,未通过返回错误信息(非法请求)
    2.secret校验通过后校验请求ip是否合法(是否在apiKey对应的ip规则之内),未通过则返回错误信息(非法请求)
    3.校验时间戳的实效性,如果时间戳跟当前系统时间之差超出系统设定的限制(默认一分钟), 那么返回错误信息(请求超时)
## 数据库变更
    无
##  事务处理
    无
##  输出
    无
*/
         void checkGateWayAuth(1:auth_request.CheckGateWayAuthRequest request)

}(group="meshAuth")
