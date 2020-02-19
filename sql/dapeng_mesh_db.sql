USE dapeng_mesh_db;
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

# test init
INSERT INTO `api_key_info` (`id`, `api_key`, `password`, `ips`, `timeout`, `validated`, `status`, `created_at`, `updated_at`, `created_by`, `updated_by`, `notes`, `biz`)
VALUES
(1, '210ed3cf9a0ea07ae04e781fe424c087', '210ed3cf9a0ea07ae04e781fe424c087', '*', 60, 0, 0, '2020-02-19 10:07:56', '2020-02-19 10:07:56', 0, 0, 'api_key,password=md5(dapeng,32)', 'test_biz');

