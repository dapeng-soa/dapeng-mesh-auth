package com.github.dapeng.mesh.auth.dto

/**
 * @author with struy.
 *         Create by 2018/5/10 10:25
 *         email :yq1724555319@gmail.com
 */

case class ApiKeyInfo(
                       /**
                        * id
                        */
                       id: Int,

                       /**
                        *
                        * *
                        * apiKey 指定业务方对应一个key
                        *
                        **/

                       apiKey: String,

                       /**
                        *
                        * *
                        * apiKey对应的规则
                        *
                        **/

                       ips: String,

                       /**
                        *
                        * *
                        * 创建时间
                        *
                        **/

                       createdAt: java.sql.Timestamp,

                       /**
                        *
                        * *
                        * 修改时间
                        *
                        **/

                       updatedAt: java.sql.Timestamp,

                       /**
                        *
                        * *
                        * 创建者id
                        *
                        **/

                       createdBy: Int,

                       /**
                        *
                        * *
                        * 修改者id
                        *
                        **/

                       updatedBy: Int,

                       /**
                        *
                        * *
                        * 业务
                        *
                        **/

                       biz: String,

                       /**
                        * 密码
                        */
                       password: String,

                       /**
                        * 网关请求超时时间(单位:秒)
                        */
                       timeout: Long = 60,

                       /**
                        * 是否需要验证timeout[0:默认验证,1:不验证]
                        */
                       validated: Int = 0,

                       /**
                        * 数据状态[0:默认启用,1:禁用]
                        */
                       status: Int = 0,

                       /**
                        * 描述
                        */
                       notes: Option[String] = None
                     )
