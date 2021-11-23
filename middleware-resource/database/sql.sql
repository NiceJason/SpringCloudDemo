CREATE TABLE `business` (
                            `id` int unsigned NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) DEFAULT NULL COMMENT '公司名称',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `undo_log` (
                            `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
                            `xid` varchar(100) NOT NULL COMMENT 'global transaction id',
                            `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
                            `rollback_info` longblob NOT NULL COMMENT 'rollback info',
                            `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
                            `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
                            `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
                            UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='AT transaction mode undo table';

CREATE TABLE `user` (
                        `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                        `name` varchar(30) DEFAULT NULL COMMENT '姓名',
                        `age` int DEFAULT NULL COMMENT '年龄',
                        `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                        `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                        `update_time` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
                        `delete_flag` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '逻辑删除，默认19个0，删除后是具体的删除日期 yyyy-MM-dd HH:mm:ss',
                        `version` int DEFAULT NULL COMMENT '版本号，每次更新版本号+1，用来作乐观锁',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1427173870520201288 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;