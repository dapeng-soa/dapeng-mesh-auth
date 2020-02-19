# dapeng-mesh-auth
[dapeng-mesh](https://github.com/dapeng-soa/dapeng-mesh) 鉴权服务默认实现

> 启用dapeng-mesh的鉴权时必须启动此服务

鉴权服务参考文档:[dapeng mesh鉴权方案](https://github.com/dapeng-soa/dapeng-soa/wiki/dapeng-mesh%E9%89%B4%E6%9D%83%E6%96%B9%E6%A1%88)

## 数据库准备
```sql
CREATE DATABASE dapeng_mesh_db;

CREATE TABLE `api_key_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `api_key` varchar(100) CHARACTER SET utf8mb4  NOT NULL COMMENT 'apiKey',
  `password` varchar(255) CHARACTER SET utf8mb4  NOT NULL COMMENT 'apikey对应密码',
  `ips` varchar(255) CHARACTER SET utf8mb4  NOT NULL DEFAULT '*' COMMENT 'ip规则,单个ip,多个ip用逗号隔开，掩码，*默认',
  `timeout` int(11) NOT NULL DEFAULT 60 COMMENT '网关请求超时时间,unit(秒)',
  `validated` int(11) NOT NULL DEFAULT 0 COMMENT '是否需要验证timeout[0:默认验证,1:不验证]',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '数据状态[0:默认启用,1:禁用]',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` int(11) NOT NULL COMMENT '添加人',
  `updated_by` int(11) NOT NULL COMMENT '最后更新人',
  `notes` varchar(255) CHARACTER SET utf8mb4  DEFAULT '' COMMENT '描述',
  `biz` varchar(100) CHARACTER SET utf8mb4  NOT NULL DEFAULT '' COMMENT '业务',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `api_key_unique`(`api_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COMMENT = '网关apiKey信息表' ;
```

## 初始化apiKey信息

> 仅供测试的数据⚠️ ⚠️ ⚠️请在测试后删除
```sql
INSERT INTO `api_key_info` (`id`, `api_key`, `password`, `ips`, `timeout`, `validated`, `status`, `created_at`, `updated_at`, `created_by`, `updated_by`, `notes`, `biz`)
VALUES
(1, '210ed3cf9a0ea07ae04e781fe424c087', '210ed3cf9a0ea07ae04e781fe424c087', '*', 60, 0, 0, '2020-02-19 10:07:56', '2020-02-19 10:07:56', 0, 0, 'api_key,password=md5(dapeng,32)', 'test_biz');
```

## 编写docker-compose文件

> dapengMeshAuth.yml
```
version: '2.2'
services:
  dapengMeshAuth:
    container_name: dapengMeshAuth
    image: dapengsoa/dapeng-mesh-auth:latest
    restart: on-failure:3
    environment:
    - soa_zookeeper_host=${hostIp}:2181
    - host_ip=${hostIp}
    - TZ=CST-8
    - LANG=zh_CN.UTF-8
    - DB_MESH_URL=jdbc:mysql://${hostIp}:3306/dapeng_mesh_db?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull
    - DB_MESH_USER=root
    - DB_MESH_PASSWD=123456
    volumes:
    - "~/data/logs/dapeng-mesh-auth:/dapeng-container/logs"
```
> 注意将${hostIp}换成您自己的ip地址

## 启动鉴权服务:
```
docker-compose -f dapengMeshAuth.yml up -d
```
## 启动dapeng-mesh
见[dapeng-mesh](https://github.com/dapeng-soa/dapeng-mesh)

