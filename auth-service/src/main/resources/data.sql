--create Role 
INSERT INTO `tokyo-supermix`.`role` (`id`, `role_name`) VALUES ('1', 'ADMIN');
INSERT INTO `tokyo-supermix`.`role` (`id`, `role_name`) VALUES ('2', 'EMPLOYEE');
INSERT INTO `tokyo-supermix`.`role` (`id`, `role_name`) VALUES ('3', 'HR');
--create User 
INSERT INTO `tokyo-supermix`.`user` (`id`, `email`, `password`, `user_name`, `role_id`, `created_at`, `updated_at`) VALUES ('1', 'admin@gmail.com', '$2y$10$WYI8/0dTM5y.0VZKCEbFWuU1Y39zOx6V3oFj6EZvh6AGXp0T.VQVK', 'admin', '1', '2020-05-06 16:58:02', '2020-05-06 21:28:58');
--admin pwd --

--create Permission--
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('1', 'add_test_configure');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('2', 'get_test_configure');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('3', 'edit_test_configure');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('4', 'delete_test_configure');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('5', 'add_test_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('6', 'get_test_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('7', 'edit_test_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('8', 'delete_test_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('9', 'add_equation');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('10', 'get_equation');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('11', 'edit_equation');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('12', 'delete_equation');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('13', 'add_equation_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('14', 'get_equation_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('15', 'edit_equation_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('16', 'delete_equation_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('17', 'add_accepted_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('18', 'get_accepted_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('19', 'edit_accepted_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('20', 'delete_accepted_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('21', 'add_accepted_admixture_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('22', 'get_accepted_admixture_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('23', 'edit_accepted_admixture_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('24', 'delete_accepted_admixture_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('25', 'add_incoming_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('26', 'get_incoming_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('27', 'edit_incoming_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('28', 'delete_incoming_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('29', 'add_process_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('30', 'get_process_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('31', 'edit_process_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('32', 'delete_process_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('33', 'add_finish_product_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('34', 'get_finish_product_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('35', 'edit_finish_product_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('36', 'delete_finish_product_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('37', 'add_finish_product_sample_issue');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('38', 'get_finish_product_sample_issue');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('39', 'edit_finish_product_sample_issue');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('40', 'delete_finish_product_sample_issue');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('41', 'add_process_sample_load');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('42', 'get_process_sample_load');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('43', 'edit_process_sample_load');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('44', 'delete_process_sample_load');


INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('45', 'add_material_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('46', 'get_material_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('47', 'delete_material_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('48', 'edit_material_test');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('49', 'get_test_type');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('50', 'add_test_type');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('51', 'delete_test_type');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('52', 'edit_test_type');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('53', 'add_concrete_test_type');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('54', 'get_concrete_test_type');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('55', 'delete_concrete_test_type');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('56', 'edit_concrete_test_type');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('57', 'add_concrete_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('58', 'get_concrete_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('59', 'delete_concrete_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('60', 'edit_concrete_test');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('61', 'get_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('62', 'add_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('63', 'delete_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('64', 'edit_parameter');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('65', 'add_quality_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('66', 'get_quality_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('67', 'delete_quality_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('68', 'edit_quality_parameter');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('69', 'add_material_quality_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('70', 'get_material_quality_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('71', 'delete_material_quality_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('72', 'edit_material_quality_parameter');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('73', 'add_project');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('74', 'get_project');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('75', 'delete_project');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('76', 'edit_project');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('77', 'add_pour');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('78', 'get_pour');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('79', 'delete_pour');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('80', 'edit_pour');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('81', 'add_finess_module');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('82', 'get_finess_module');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('83', 'delete_finess_module');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('84', 'edit_finess_module');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('85', 'add_sieve_accepted_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('86', 'get_sieve_accepted_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('87', 'edit_sieve_accepted_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('88', 'delete_sieve_accepted_value');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('89', 'add_sieve_size');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('90', 'get_sieve_size');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('91', 'edit_sieve_size');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('92', 'delete_sieve_size');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('93', 'add_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('94', 'get_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('95', 'delete_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`) VALUES ('96', 'edit_test');











