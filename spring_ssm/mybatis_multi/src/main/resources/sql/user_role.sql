CREATE TABLE `user_role`
(
    `userId` bigint NOT NULL,
    `roleId` bigint NOT NULL,
    PRIMARY KEY (`userId`, `roleId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;

INSERT INTO user_role (userId, roleId) VALUES(1, 1);
INSERT INTO user_role (userId, roleId) VALUES(1, 2);
INSERT INTO user_role (userId, roleId) VALUES(2, 2);
INSERT INTO user_role (userId, roleId) VALUES(2, 3);
