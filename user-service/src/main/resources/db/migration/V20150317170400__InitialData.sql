INSERT INTO role(id, description, is_selectable, name) VALUES (1, "Indicates the role is for site admin", 'Y', "ROLE_ADMIN");
INSERT INTO role(id, description, is_selectable, name) VALUES (2, "Indicates the role is for club admin", 'Y', "ROLE_CLUB_ADMIN");
INSERT INTO role(id, description, is_selectable, name) VALUES (3, "Indicates the role is of type captain", 'Y', "ROLE_CAPTAIN");
INSERT INTO role(id, description, is_selectable, name) VALUES (4, "Indicates the role is of type player", 'Y', "ROLE_PLAYER");

INSERT INTO user_status(id, name, description, is_selectable) VALUES (1, "ACTIVE", "Indicates the user is active", 'Y');
INSERT INTO user_status(id, name, description, is_selectable) VALUES (2, "PENDING", "Indicates the user is pending", 'Y');
INSERT INTO user_status(id, name, description, is_selectable) VALUES (3, "SUSPENDED", "Indicates the user is suspended", 'Y');
INSERT INTO user_status(id, name, description, is_selectable) VALUES (4, "BLACKLISTED", "Indicates the user is blacklisted", 'Y');

--Functional Test admin
INSERT INTO user(id, username, password, updated_by, updated_ts, version) VALUES (1, "admin@ratedsports.com", "5f4dcc3b5aa765d61d8327deb882cf99", 3, '2014-09-12 16:46:45', 0);
INSERT INTO user_roles(id, role_id, user_id) VALUES (1, 1, 1);

