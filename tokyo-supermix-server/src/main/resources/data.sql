---- designation
INSERT INTO `tokyo-supermix`.`designation` (`id`, `description`, `name`) VALUES ('1', 'Main Admin', 'Administrator');
-- employee
INSERT INTO `tokyo-supermix`.`employee` (`id`, `address`, `email`, `first_name`, `last_name`, `phone_number`, `designation_id`, `has_user`) VALUES ('1', 'Colombo-12', 'tokyotester4@gmail.com', 'Tokyo', 'Tester', '0761452365', '1', 1);
-- role
INSERT INTO `tokyo-supermix`.`role` (`id`, `name`) VALUES ('1', 'ADMIN');
INSERT INTO `tokyo-supermix`.`role` (`id`, `name`) VALUES ('2', 'USER');
-- user
INSERT INTO `tokyo-supermix`.`user` (`id`, `email`, `password`, `user_name`, `user_type`,`employee_id`, `created_at`, `updated_at`,`is_active`) VALUES ('1', 'admin@gmail.com', '$2y$10$WYI8/0dTM5y.0VZKCEbFWuU1Y39zOx6V3oFj6EZvh6AGXp0T.VQVK', 'admin', '1','1', '2020-05-06 16:58:02', '2020-05-06 21:28:58',1);
--user role
INSERT INTO `tokyo-supermix`.`user_role` (`id`, `role_id`, `user_id`,`role_type`) VALUES ('1', '1', '1','0');

--privillege
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('1', 'plant');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('2', 'category');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('3', 'unit');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('4', 'material');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('5', 'equipment');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('6', 'test');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('7', 'parameter');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('8', 'site');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('9', 'sieve');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('10', 'sample');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('11', 'mix_design');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('12', 'test_report');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('13', 'test_configuration');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('14', 'manage_test');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('15', 'privilege');

--privilege model
INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('1', 'plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('1', 'view_plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('2', 'create_plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('3', 'edit_plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('4', 'delete_plant', '1');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('2', 'congrete_mixer', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('5', 'view_concrete_mixer', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('6', 'create_concrete_mixer', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('7', 'edit_concrete_mixer', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('8', 'delete_concrete_mixer', '2');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('3', 'designation', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('9', 'view_designation', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('10', 'create_designation', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('11', 'edit_designation', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('12', 'delete_designation', '3');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('18', 'role', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('69', 'view_role', '18');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('70', 'create_role', '18');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('71', 'edit_role', '18');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('72', 'delete_role', '18');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('19', 'user', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('73', 'view_user', '19');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('74', 'create_user', '19');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('75', 'edit_user_status', '19');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('76', 'edit_user_role', '19');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('77', 'delete_user', '19');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('5', 'customer', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('17', 'view_customer', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('18', 'create_customer', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('19', 'edit_customer', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('20', 'delete_customer', '5');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('7', 'supplier', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('25', 'view_supplier', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('26', 'create_supplier', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('27', 'edit_supplier', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('28', 'delete_supplier', '7');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('6', 'supplier_category', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('21', 'view_supplier_category', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('22', 'create_supplier_category', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('23', 'edit_supplier_category', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('24', 'delete_supplier_category', '6');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('8', 'material_category', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('29', 'view_material_category', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('30', 'create_material_category', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('31', 'edit_material_category', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('32', 'delete_material_category', '8');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('9', 'material_sub_category', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('33', 'view_material_sub_category', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('34', 'create_material_sub_category', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('35', 'edit_material_sub_category', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('36', 'delete_material_sub_category', '9');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('10', 'manage_unit', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('37', 'view_unit', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('38', 'create_unit', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('39', 'edit_unit', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('40', 'delete_unit', '10');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('11', 'material_state', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('41', 'view_material_state', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('42', 'create_material_state', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('43', 'edit_material_state', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('44', 'delete_material_state', '11');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('12', 'materials', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('45', 'view_raw_material', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('46', 'create_raw_material', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('47', 'edit_raw_material', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('48', 'delete_raw_material', '12');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('13', 'equipment', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('49', 'view_equipment', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('50', 'create_equipment', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('51', 'edit_equipment', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('52', 'delete_equipment', '13');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('14', 'plant_equipment', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('53', 'view_plant_equipment', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('54', 'create_plant_equipment', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('55', 'edit_plant_equipment', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('56', 'delete_plant_equipment', '14');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('27', 'parameter', '7');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('106', 'view_parameter', '27');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('107', 'create_parameter', '27');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('108', 'edit_parameter', '27');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('109', 'delete_parameter', '27');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('41', 'quality_parameter', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('162', 'view_quality_parameter', '41');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('163', 'create_quality_parameter', '41');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('164', 'edit_quality_parameter', '41');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('165', 'delete_quality_parameter', '41');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('16', 'project', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('61', 'view_project', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('62', 'create_project', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('63', 'edit_project', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('64', 'delete_project', '16');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('17', 'pour', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('65', 'view_pour', '17');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('66', 'create_pour', '17');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('67', 'edit_pour', '17');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('68', 'delete_pour', '17');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('31', 'seive_accepted_value', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('123', 'view_sieve_accepted_value', '31');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('124', 'create_sieve_accepted_value', '31');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('125', 'edit_sieve_accepted_value', '31');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('126', 'delete_sieve_accepted_value', '31');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('37', 'seive_size', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('147', 'view_sieve_size', '37');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('148', 'edit_sieve_size', '37');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('149', 'delete_sieve_size', '37');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('35', 'material_test', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('139', 'view_material_test', '35');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('140', 'create_material_test', '35');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('141', 'edit_material_test', '35');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('142', 'delete_material_test', '35');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('25', 'test_configuration', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('98', 'view_test_configuration', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('99', 'create_test_configuration', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('100', 'edit_test_configuration', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('101', 'delete_test_configuration', '25');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('26', 'equation', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('102', 'view_equation', '26');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('103', 'create_equation', '26');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('104', 'edit_equation', '26');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('105', 'delete_equation', '26');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('32', 'accepted_value', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('127', 'view_accepted_value', '32');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('128', 'create_accepted_value', '32');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('129', 'edit_accepted_value', '32');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('130', 'delete_accepted_value', '32');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('28', 'test_parameter', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('110', 'view_test_parameter', '28');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('111', 'create_test_parameter', '28');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('112', 'edit_test_parameter', '28');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('113', 'delete_test_parameter', '28');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('23', 'test', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('90', 'view_test', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('91', 'create_test', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('92', 'edit_test', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('93', 'delete_test', '23');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('21', 'incoming_sample', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('82', 'view_incoming_sample', '21');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('83', 'create_incoming_sample', '21');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('84', 'edit_incoming_sample', '21');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('85', 'delete_incoming_sample', '21');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('22', 'finish_product_sample', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('86', 'view_finish_product_sample', '22');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('87', 'create_finish_product_sample', '22');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('88', 'edit_finish_product_sample', '22');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('89', 'delete_finish_product_sample', '22');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('38', 'finish_product_sample_issue', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('150', 'view_finish_product_sample_issue', '38');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('151', 'create_finish_product_sample_issue', '38');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('152', 'edit_finish_product_sample_issue', '38');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('153', 'delete_finish_product_sample_issue', '38');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('24', 'process_sample', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('94', 'view_process_sample', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('95', 'create_process_sample', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('96', 'edit_process_sample', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('97', 'delete_process_sample', '24');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('15', 'plant_equipment_calibration', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('57', 'view_plant_equipment_calibration', '15');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('58', 'create_plant_equipment_calibration', '15');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('59', 'edit_plant_equipment_calibration', '15');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('60', 'delete_plant_equipment_calibration', '15');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('4', 'employee', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('13', 'view_employee', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('14', 'create_employee', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('15', 'edit_employee', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('16', 'delete_employee', '4');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('20', 'mix_design', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('78', 'view_mix_design', '20');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('79', 'create_mix_design', '20');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('80', 'edit_mix_design', '20');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('81', 'delete_mix_design', '20');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('114', 'material_status_total_count_dashboard', '20');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('29', 'parameter_result', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('115', 'view_parameter_result', '29');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('116', 'create_parameter_result', '29');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('117', 'edit_parameter_result', '29');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('118', 'delete_parameter_result', '29');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('30', 'material_accepted_value', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('119', 'view_material_accepted_value', '30');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('120', 'create_material_accepted_value', '30');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('121', 'edit_material_accepted_value', '30');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('122', 'delete_material_accepted_value', '30');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('33', 'parameter_equation', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('131', 'view_parameter_equation', '33');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('132', 'create_parameter_equation', '33');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('133', 'edit_parameter_equation', '33');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('134', 'delete_parameter_equation', '33');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('34', 'mixdesign_proportion', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('135', 'view_mix_design_proportion', '34');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('136', 'create_mix_design_proportion', '34');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('137', 'edit_mix_design_proportion', '34');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('138', 'delete_mix_design_proportion', '34');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('36', 'material_test_trail', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('143', 'view_material_test_trial', '36');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('144', 'create_material_test_trial', '36');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('145', 'edit_material_test_trial', '36');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('146', 'delete_material_test_trial', '36');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('39', 'finish_product_test', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('154', 'view_finish_product_test', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('155', 'create_finish_product_test', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('156', 'edit_finish_product_test', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('157', 'delete_finish_product_test', '39');


INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('40', 'finish_product_trail', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('158', 'view_finish_product_trail', '40');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('159', 'create_finish_product_trail', '40');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('160', 'edit_finish_product_trail', '40');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('161', 'delete_finish_product_trail', '40');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('42', 'test_report', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('166', 'view_test_report', '42');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('167', 'create_test_report', '42');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('168', 'edit_test_report', '42');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('169', 'delete_test_report', '42');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('43', 'parameter_equation_element', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('170', 'view_equation_element', '43');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('171', 'create_equation_element', '43');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('173', 'edit_equation_element', '43');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('174', 'delete_equation_element', '43');

INSERT INTO `tokyo-supermix`.`email_group` (`id`, `name`,  `schedule`) VALUES ('1', 'Reporting Group',0);
INSERT INTO `tokyo-supermix`.`email_group` (`id`, `name`, `schedule`) VALUES ('2', 'Calibration Group',1);
INSERT INTO `tokyo-supermix`.`email_group` (`id`, `name`, `schedule`) VALUES ('3', 'Incoming Sample Group',0);


INSERT INTO `tokyo-supermix`.`role_permission` (`id`,`role_id`,`permission_id`,`status`)VALUES
(1,1,1,1),(2,1,2,1),(3,1,3,1),(4,1,4,1),(5,1,5,1),(6,1,6,1),(7,1,7,1),(8,1,8,1),(9,1,9,1),(10,1,10,1),
(11,1,11,1),(12,1,12,1),(13,1,13,1),(14,1,14,1),(15,1,15,1),(16,1,16,1),(17,1,17,1),(18,1,18,1),(19,1,19,1),(20,1,20,1),(21,1,21,1),(22,1,22,1),(23,1,23,1),(24,1,24,1),(25,1,25,1),
(26,1,26,1),(27,1,27,1),(28,1,28,1),(29,1,29,1),(30,1,30,1),(31,1,31,1),(32,1,32,1),(33,1,33,1),(34,1,34,1),(35,1,35,1),(36,1,36,1),(37,1,37,1),(38,1,38,1),(39,1,39,1),(40,1,40,1),
(41,1,41,1),(42,1,42,1),(43,1,43,1),(44,1,44,1),(45,1,45,1),(46,1,46,1),(47,1,47,1),(48,1,48,1),(49,1,49,1),(50,1,50,1),(51,1,51,1),(52,1,52,1),(53,1,53,1),(54,1,54,1),(55,1,55,1),
(56,1,56,1),(57,1,57,1),(58,1,58,1),(59,1,59,1),(60,1,60,1),(61,1,61,1),(62,1,62,1),(63,1,63,1),(64,1,64,1),(65,1,65,1),(66,1,66,1),(67,1,67,1),(68,1,68,1),(69,1,69,1),(70,1,70,1),
(71,1,71,1),(72,1,72,1),(73,1,72,1),(74,1,74,1),(75,1,75,1),(76,1,76,1),(77,1,77,1),(78,1,78,1),(79,1,79,1),(80,1,80,1),(81,1,81,1),(82,1,82,1),(83,1,83,1),(84,1,84,1),(85,1,85,1),
(86,1,86,1),(87,1,87,1),(88,1,88,1),(89,1,89,1),(90,1,90,1),(91,1,91,1),(92,1,92,1),(93,1,93,1),(94,1,94,1),(95,1,95,1),(96,1,96,1),(97,1,97,1),(98,1,98,1),(99,1,99,1),(100,1,100,1),
(101,1,101,1),(102,1,102,1),(103,1,103,1),(104,1,104,1),(105,1,105,1),(106,1,106,1),(107,1,107,1),(108,1,108,1),(109,1,109,1),(110,1,110,1),(111,1,111,1),(112,1,112,1),(113,1,113,1),
(114,1,114,1),(115,1,115,1),(116,1,116,1),(117,1,117,1),(118,1,118,1),(119,1,119,1),(120,1,120,1),(121,1,121,1),(122,1,122,1),(123,1,123,1),(124,1,124,1),(125,1,125,1),
(126,1,126,1),(127,1,127,1),(128,1,128,1),(129,1,129,1),(130,1,130,1),(131,1,131,1),(132,1,132,1),(133,1,133,1),(134,1,134,1),(135,1,135,1),(136,1,136,1),(137,1,137,1),(138,1,138,1),(139,1,139,1),(140,1,140,1),
(141,1,141,1),(142,1,142,1),(143,1,143,1),(144,1,144,1),(145,1,145,1),(146,1,146,1),(147,1,147,1),(148,1,148,1),(149,1,149,1),(150,1,150,1),(151,1,151,1),(152,1,152,1),(153,1,153,1),(154,1,154,1),(155,1,155,1),
(156,1,156,1),(157,1,157,1),(158,1,158,1),(159,1,159,1),(160,1,160,1),(161,1,161,1),(162,1,162,1),(163,1,163,1),(164,1,164,1),(165,1,165,1),(166,1,166,1),(167,1,167,1),(168,1,168,1),(169,1,169,1),(170,1,170,1),(171,1,171,1),(173,1,173,1),(174,1,174,1);