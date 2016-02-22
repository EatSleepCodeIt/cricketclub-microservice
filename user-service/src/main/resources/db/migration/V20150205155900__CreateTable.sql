CREATE TABLE IF NOT EXISTS `revinfo` (
  `rev` int(11) NOT NULL AUTO_INCREMENT,
  `revtstmp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rev`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS role (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  is_selectable char(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY ix_name (name)
);

CREATE TABLE IF NOT EXISTS `user_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `is_selectable` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_name` (`name`),
  KEY `ix_is_selectable` (`is_selectable`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_status_id` int(11) NOT NULL,
  `created_ts` TIMESTAMP NOT NULL,
  `updated_ts` TIMESTAMP NOT NULL,
  `updated_by` varchar(14) NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_username` (`username`),
  CONSTRAINT fk_user_user_status FOREIGN KEY (user_status_id) REFERENCES user_status (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `user_tt` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_status_id` int(11) NOT NULL,
  `created_ts` datetime DEFAULT NULL,
  `updated_ts` TIMESTAMP NOT NULL,
  `updated_by` varchar(14) NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `ix_rev` (`rev`),
  CONSTRAINT `fk_rev_user` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS user_roles (
  id int(11) NOT NULL AUTO_INCREMENT,
  role_id int(11) NOT NULL,
  user_id bigint(20) NOT NULL,
  created_ts TIMESTAMP NOT NULL ,
  PRIMARY KEY (id),
  KEY ix_user_id (user_id),
  KEY fk_user_roles_role (role_id),
  CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES role (id),
  CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS `user_password_token` (
  id int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `token` varchar(255) NOT NULL,
  `created_ts` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_user_id` (`user_id`),
  KEY `ix_token` (`token`),
  KEY `ix_user_id_token` (`user_id`,`token`),
  CONSTRAINT `fk_user_password_token_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;