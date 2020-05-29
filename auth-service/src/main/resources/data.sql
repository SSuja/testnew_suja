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