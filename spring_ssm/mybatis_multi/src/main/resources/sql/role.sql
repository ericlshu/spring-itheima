CREATE TABLE `role`
(
    `id`       bigint NOT NULL AUTO_INCREMENT,
    `roleName` varchar(50) DEFAULT NULL,
    `roleDesc` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8mb3;

INSERT INTO role (id, roleName, roleDesc) VALUES (1, '院长', '负责全面工作');
INSERT INTO role (id, roleName, roleDesc) VALUES (2, '研究员', '课程研发工作');
INSERT INTO role (id, roleName, roleDesc) VALUES (3, '讲师', '授课工作');
INSERT INTO role (id, roleName, roleDesc) VALUES (4, '助教', '协助解决学生');
INSERT INTO role (id, roleName, roleDesc) VALUES (6, '就业指导', '负责就业工作');
