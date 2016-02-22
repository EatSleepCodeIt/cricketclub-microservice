--frontend
INSERT INTO oauth_client_details (client_name, client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) 
VALUES ('frontend', '9ecc8459ea5f39f9da55cb4d71a70b5d1e0f0b80', null, null, 'all,read,write', 'refresh_token,password', null, 'ROLE_CLIENT', null, 999999999, null, true);

--user-service
INSERT INTO oauth_client_details (client_name, client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) 
VALUES ('user-service', '4f7ec648a48b9d3fa239b497f7b6b4d8019697bd', null, '$2a$10$er7kPyZHaX2Js3NYQ/8xQejUR/F78hytrxNVOZd5MB5QwsIBf0BH6', 'all,read,write', null, null, 'ROLE_RESOURCE_SERVER', null, null, null, true);

--payment-service
INSERT INTO oauth_client_details (client_name, client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) 
VALUES ('payment-service', '0e80ce8e64541c50ed830a54cfd33d6823d080a9', null, '$2a$10$OSRH0ciLSarwKUADv601YucXXrrRzvJX9MSq3gSDg0m/arMfbJbhu', 'all,read,write', null, null, 'ROLE_RESOURCE_SERVER', null, null, null, true);

--billing service
INSERT INTO oauth_client_details (client_name, client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) 
VALUES ('billing-service', 'd178a0356404d8fd32248c5ea02599c01eed745c', null, '$2a$10$sKI6ZrWknet7GD9Bft38.epZm9ybqEogGG7PHWKKcZAqDTtVJfvFS', 'all,read,write', null, null, 'ROLE_RESOURCE_SERVER', null, null, null, true);

--profile-service
INSERT INTO oauth_client_details (client_name, client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) 
VALUES ('profile-service', '7beff3df15bc20310fe0d7a2c341f1f027bbb060', null, '$2a$10$oNzfKcW.GK6ubgxB6WIc1u0/LeBCOXYulEx.Sk65MzyF40Lcjr1A.', 'all,read,write', null, null, 'ROLE_RESOURCE_SERVER', null, null, null, true);

--notification-service
INSERT INTO oauth_client_details (client_name, client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) 
VALUES ('notification-service', '1e22eb228d5d7f2b0c965b0c327e0ea626bb5285', null, '$2a$10$ml8YHndeKJGcx/3rP1.94uU02HGz68ZvBAdEe0vXltQnIJ3jehrHO', 'all,read,write', null, null, 'ROLE_RESOURCE_SERVER', null, null, null, true);