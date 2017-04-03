-- USER TABLE
INSERT INTO `xbalancer-db`.user (oid, create_date, create_user, update_date, update_user, data_version, account_non_locked, credentials_non_expired, account_enabled, password, username) VALUES ('1', null, 'admin', null, 'admin', 0, true, true, true, '$2a$06$p3YaomcrCgWVaEyS0drqHuoroFVBONTg31omlSNV21P8K5FmCrXxm', 'admin');
-- ROLE TABLE
INSERT INTO `xbalancer-db`.role (oid, create_date, create_user, update_date, update_user, data_version, description, name) VALUES ('1', '2017-04-04 01:33:21', 'admin', '2017-04-04 01:33:26', 'admin', 0, 'Admin Role', 'ADMIN');
-- PERMISSION TABLE
INSERT INTO `xbalancer-db`.permisson (oid, create_date, create_user, update_date, update_user, data_version, authority, description, name) VALUES ('1', '2017-04-04 01:30:57', 'admin', '2017-04-04 01:31:03', 'admin', 0, 'USER', 'User Permission', 'USER');
INSERT INTO `xbalancer-db`.permisson (oid, create_date, create_user, update_date, update_user, data_version, authority, description, name) VALUES ('2', '2017-04-04 01:31:38', 'admin', '2017-04-04 01:31:41', 'admin', 0, 'ROLE', 'Role Permission', null);
INSERT INTO `xbalancer-db`.permisson (oid, create_date, create_user, update_date, update_user, data_version, authority, description, name) VALUES ('3', '2017-04-04 01:32:07', 'admin', '2017-04-04 01:32:11', 'admin', 0, 'BROWSE_ALL', 'Browse all Crud Operations', null);

-- ROLE_PERMISSION CROSS TABLE
INSERT INTO `xbalancer-db`.role_permission (role_oid, permission_oid) VALUES ('1', '1');
INSERT INTO `xbalancer-db`.role_permission (role_oid, permission_oid) VALUES ('1', '2');
INSERT INTO `xbalancer-db`.role_permission (role_oid, permission_oid) VALUES ('1', '3');

-- USER_ROLE CROSS TABLE
INSERT INTO `xbalancer-db`.user_role (user_oid, role_oid) VALUES ('1', '1');

-- PROXY_ADDRESSES
INSERT INTO `xbalancer-db`.proxy_address (oid, create_date, create_user, update_date, update_user, data_version, host, name, port) VALUES ('1', '2017-04-04 01:35:53', 'admin', '2017-04-04 01:35:59', 'admin', 0, '127.0.0.1', 'server1', 8283);
INSERT INTO `xbalancer-db`.proxy_address (oid, create_date, create_user, update_date, update_user, data_version, host, name, port) VALUES ('2', '2017-04-04 01:35:53', 'admin', '2017-04-04 01:35:59', 'admin', 0, '127.0.0.1', 'server2', 8284);
INSERT INTO `xbalancer-db`.proxy_address (oid, create_date, create_user, update_date, update_user, data_version, host, name, port) VALUES ('3', '2017-04-04 01:35:53', 'admin', '2017-04-04 01:35:59', 'admin', 0, '127.0.0.1', 'server3', 8285);


-- PROXY_POINTER
INSERT INTO `xbalancer-db`.proxy_pointer (oid, create_date, create_user, update_date, update_user, data_version, description, path) VALUES ('1', '2017-04-04 01:34:34', 'admin', '2017-04-04 01:34:41', 'admin', 0, 'Dummy Path', 'Dummy');
INSERT INTO `xbalancer-db`.proxy_pointer (oid, create_date, create_user, update_date, update_user, data_version, description, path) VALUES ('2', '2017-04-04 01:35:16', 'admin', '2017-04-04 01:35:20', 'admin', 0, 'Another Path', 'Another');

-- PROXY_POINTER_ADDRESSES CROSS TABLE
INSERT INTO `xbalancer-db`.proxy_pointer_addresses (pointer_oid, address_oid) VALUES ('1', '1');
INSERT INTO `xbalancer-db`.proxy_pointer_addresses (pointer_oid, address_oid) VALUES ('1', '2');
INSERT INTO `xbalancer-db`.proxy_pointer_addresses (pointer_oid, address_oid) VALUES ('2', '1');
INSERT INTO `xbalancer-db`.proxy_pointer_addresses (pointer_oid, address_oid) VALUES ('2', '3');