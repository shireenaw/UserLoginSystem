CREATE TABLE IF NOT EXISTS `users`(
    `user_id`        INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `first_name`     VARCHAR(100),
    `last_name`      VARCHAR(100),
    `mobile_num` VARCHAR(100),
    `email` VARCHAR(100) UNIQUE,
    `password`      VARCHAR(100),
    `role`      VARCHAR(100)
    );