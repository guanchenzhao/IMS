CREATE TABLE `ims`.`user`
(
    `user_id`     int(11) NOT NULL AUTO_INCREMENT,
    `password`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `salt`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `account`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `role`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `email`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `phone`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `status`      tinyint(4) NULL DEFAULT NULL,
    `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'User' ROW_FORMAT = Dynamic;

CREATE TABLE `inventory`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `type`         varchar(255) DEFAULT NULL,
    `name`         varchar(255) DEFAULT NULL,
    `description`  varchar(255) DEFAULT NULL,
    `status`       tinyint(4) DEFAULT NULL COMMENT 'status 0-showing; 1-ready  2- repair  3- store',
    `rfid_no`      varchar(255) DEFAULT NULL,
    `location`     varchar(255) DEFAULT NULL,
    `exhibit_time` datetime     DEFAULT NULL,
    `create_time`  datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `update_time`  datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `is_del`       tinyint(4) DEFAULT NULL COMMENT '0- normal; 1-deleted',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `appointment`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `status`       tinyint(4) DEFAULT NULL COMMENT '0- normal ; 1-cancel; 2-finished',
    `user_id`      int(11) DEFAULT NULL,
    `inventory_id` int(11) DEFAULT NULL,
    `plan_date`    datetime DEFAULT NULL,
    `create_time`  datetime DEFAULT NULL,
    `update_time`  datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `subscribe`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `user_id`      int(11) DEFAULT NULL,
    `inventory_id` int(11) DEFAULT NULL,
    `create_time`  datetime DEFAULT NULL,
    `update_time`  datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;