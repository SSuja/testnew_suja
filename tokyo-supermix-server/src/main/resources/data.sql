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
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('2', 'equipment');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('3', 'material');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('4', 'customer');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('5', 'supplier');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('6', 'employee');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('7', 'test');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('8', 'parameter');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('9', 'configure_test');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('10', 'manage_configure_test');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('11', 'sample');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('12', 'mix_design');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('13', 'result_and_report');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('14', 'plant_equipment_calibration');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('15', 'privilege');
INSERT INTO `tokyo-supermix`.`main_module` (`id`, `name`) VALUES ('16', 'settings');

--privilege model
INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('1', 'production_unit', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('1', 'view_production_unit', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('2', 'create_production_unit', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('3', 'edit_production_unit', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('4', 'delete_production_unit', '1');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('2', 'equipment', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('5', 'view_equipment', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('6', 'create_equipment', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('7', 'edit_equipment', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('8', 'delete_equipment', '2');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('3', 'plant_equipment', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('9', 'view_plant_equipment', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('10', 'create_plant_equipment', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('11', 'edit_plant_equipment', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('12', 'delete_plant_equipment', '3');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('4', 'material_category', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('13', 'view_material_category', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('14', 'create_material_category', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('15', 'edit_material_category', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('16', 'delete_material_category', '4');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('5', 'material_sub_category', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('17', 'view_material_sub_category', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('18', 'create_material_sub_category', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('19', 'edit_material_sub_category', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('20', 'delete_material_sub_category', '5');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('6', 'material_state', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('21', 'view_material_state', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('22', 'create_material_state', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('23', 'edit_material_state', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('24', 'delete_material_state', '6');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('7', 'materials', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('25', 'view_raw_material', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('26', 'create_raw_material', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('27', 'edit_raw_material', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('28', 'delete_raw_material', '7');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('8', 'unit', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('29', 'view_unit', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('30', 'create_unit', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('31', 'edit_unit', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('32', 'delete_unit', '8');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('9', 'customer', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('33', 'view_customer', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('34', 'create_customer', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('35', 'edit_customer', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('36', 'delete_customer', '9');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('10', 'project', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('37', 'view_project', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('38', 'create_project', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('39', 'edit_project', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('40', 'delete_project', '10');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('11', 'delivery_cycle', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('41', 'view_delivery_cycle', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('42', 'create_delivery_cycle', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('43', 'edit_delivery_cycle', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('44', 'delete_delivery_cycle', '11');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('12', 'supplier_category', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('45', 'view_supplier_category', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('46', 'create_supplier_category', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('47', 'edit_supplier_category', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('48', 'delete_supplier_category', '12');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('13', 'supplier', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('49', 'view_supplier', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('50', 'create_supplier', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('51', 'edit_supplier', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('52', 'delete_supplier', '13');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('14', 'designation', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('53', 'view_designation', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('54', 'create_designation', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('55', 'edit_designation', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('56', 'delete_designation', '14');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('15', 'role', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('57', 'view_role', '15');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('58', 'create_role', '15');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('59', 'edit_role', '15');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('60', 'delete_role', '15');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('16', 'employee', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('61', 'view_employee', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('62', 'create_employee', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('63', 'edit_employee', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('64', 'delete_employee', '16');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('17', 'user', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('65', 'view_user', '17');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('66', 'create_user', '17');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('67', 'edit_user_status', '17');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('68', 'edit_user_role', '17');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('69', 'delete_user', '17');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('18', 'material_test', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('70', 'view_material_test', '18');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('71', 'create_material_test', '18');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('72', 'edit_material_test', '18');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('73', 'delete_material_test', '18');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('19', 'parameter', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('74', 'view_parameter', '19');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('75', 'create_parameter', '19');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('76', 'edit_parameter', '19');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('77', 'delete_parameter', '19');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('20', 'quality_parameter', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('78', 'view_quality_parameter', '20');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('79', 'create_quality_parameter', '20');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('80', 'edit_quality_parameter', '20');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('81', 'delete_quality_parameter', '20');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('21', 'quality_parameter_value', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('82', 'view_quality_parameter_value', '21');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('83', 'create_quality_parameter_value', '21');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('84', 'edit_quality_parameter_value', '21');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('85', 'delete_quality_parameter_value', '21');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('22', 'seive_size', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('86', 'view_sieve_size', '22');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('87', 'create_sieve_size', '22');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('88', 'edit_sieve_size', '22');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('89', 'delete_sieve_size', '22');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('23', 'seive_accepted_value', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('90', 'view_sieve_accepted_value', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('91', 'create_sieve_accepted_value', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('92', 'edit_sieve_accepted_value', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('93', 'delete_sieve_accepted_value', '23');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('24', 'test_configuration', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('94', 'create_manage_test', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('95', 'create_manage_accepted_value', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('96', 'create_manage_test_parameter', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('97', 'create_manage_equation', '24');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('25', 'manage_test', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('98', 'view_manage_test', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('99', 'edit_manage_test', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('100', 'delete_manage_test', '25');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('26', 'manage_accepted_value', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('101', 'view_manage_accepted_value', '26');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('102', 'edit_manage_accepted_value', '26');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('103', 'delete_manage_accepted_value', '26');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('27', 'manage_test_parameter', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('104', 'view_manage_test_parameter', '27');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('105', 'edit_manage_test_parameter', '27');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('106', 'delete_manage_test_parameter', '27');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('28', 'manage_equation', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('107', 'view_manage_equation', '28');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('108', 'edit_manage_equation', '28');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('109', 'delete_manage_equation', '28');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('29', 'incoming_sample', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('110', 'view_incoming_sample', '29');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('111', 'create_incoming_sample', '29');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('112', 'edit_incoming_sample', '29');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('113', 'delete_incoming_sample', '29');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('30', 'process_sample', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('114', 'view_process_sample', '30');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('115', 'create_process_sample', '30');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('116', 'edit_process_sample', '30');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('117', 'delete_process_sample', '30');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('31', 'finish_product_sample', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('118', 'view_finish_product_sample', '31');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('119', 'create_finish_product_sample', '31');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('120', 'edit_finish_product_sample', '31');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('121', 'delete_finish_product_sample', '31');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('32', 'finish_product_sample_issue', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('122', 'view_finish_product_sample_issue', '32');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('123', 'create_finish_product_sample_issue', '32');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('124', 'edit_finish_product_sample_issue', '32');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('125', 'delete_finish_product_sample_issue', '32');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('33', 'mix_design', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('126', 'view_mix_design', '33');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('127', 'create_mix_design', '33');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('128', 'edit_mix_design', '33');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('129', 'delete_mix_design', '33');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('130', 'import', '33');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('131', 'export', '33');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('34', 'finish_product_test_result', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('132', 'view_finish_product_test_result', '34');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('133', 'create_finish_product_test_result', '34');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('134', 'edit_finish_product_test_result', '34');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('135', 'delete_finish_product_test_result', '34');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('35', 'raw_material_test_result', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('136', 'view_raw_material_test_result', '35');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('137', 'create_raw_material_test_result', '35');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('138', 'edit_raw_material_test_result', '35');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('139', 'delete_raw_material_test_result', '35');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('36', 'plant_equipment_calibration', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('140', 'view_plant_equipment_calibration', '36');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('141', 'create_plant_equipment_calibration', '36');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('142', 'edit_plant_equipment_calibration', '36');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('143', 'delete_plant_equipment_calibration', '36');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('37', 'test_configuration', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('144', 'view_test_configuration', '37');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('145', 'create_test_configuration', '37');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('146', 'edit_test_configuration', '37');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('147', 'delete_test_configuration', '37');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('38', 'mixdesign_proportion', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('148', 'view_mix_design_proportion', '38');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('149', 'create_mix_design_proportion', '38');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('150', 'edit_mix_design_proportion', '38');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('151', 'delete_mix_design_proportion', '38');


INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('39', 'parameter_result', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('152', 'view_parameter_result', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('153', 'create_parameter_result', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('154', 'edit_parameter_result', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('155', 'delete_parameter_result', '39');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('40', 'material_accepted_value', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('156', 'delete_material_accepted_value', '40');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('157', 'view_material_accepted_value', '40');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('158', 'create_material_accepted_value', '40');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('159', 'edit_material_accepted_value', '40');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('41', 'parameter_equation', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('160', 'view_parameter_equation', '41');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('161', 'create_parameter_equation', '41');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('162', 'edit_parameter_equation', '41');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('163', 'delete_parameter_equation', '41');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('42', 'privilege', '15');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('164', 'edit_role_permission', '42');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('165', 'view_user_plant_permission_by_user', '42');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('166', 'edit_user_plant_permission', '42');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('167', 'view_plant_permission', '42');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('43', 'finish_product_test', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('168', 'create_finish_product_test', '43');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('169', 'view_finish_product_test', '43');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('170', 'edit_finish_product_test', '43');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('171', 'delete_finish_product_test', '43');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('44', 'material_test_trial', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('172', 'create_material_test_trial', '44');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('173', 'view_material_test_trial', '44');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('174', 'edit_material_test_trial', '44');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('175', 'delete_material_test_trial', '44');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('45', 'finish_product_trail', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('176', 'create_finish_product_trail', '45');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('177', 'view_finish_product_trail', '45');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('178', 'edit_finish_product_trail', '45');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('179', 'delete_finish_product_trail', '45');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('46', 'report', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('180', 'material_test_report', '46');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('181', 'cement_report', '46');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('182', 'incoming_sample_summary_report', '46');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('183', 'incoming_sample_delivery_report', '46');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('184', 'admixture_report', '46');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('185', 'material_status_total_count_dashboard', '46');
	
INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('47', 'admin_privilege', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('186', 'create_admin_privilege', '47');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('187', 'view_admin_privilege', '47');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('188', 'edit_admin_privilege', '47');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('189', 'delete_admin_privilege', '47');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('48', 'other_plant_access', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('190', 'create_other_plant_access', '47');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('191', 'view_other_plant_access', '47');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('192', 'edit_other_plant_access', '47');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('193', 'delete_other_plant_access', '47');


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
(156,1,156,1),(157,1,157,1),(158,1,158,1),(159,1,159,1),(160,1,160,1),(161,1,161,1),(162,1,162,1),(163,1,163,1),(164,1,164,1),(165,1,165,1),(166,1,166,1),(167,1,167,1),(168,1,168,1),(169,1,169,1),(170,1,170,1),
(171,1,171,1),(172,1,172,1),(173,1,173,1),(174,1,174,1),(175,1,175,1),(176,1,176,1),(177,1,177,1),(178,1,178,1),(179,1,179,1),(180,1,180,1),
(181,1,181,1),(182,1,182,1),(183,1,183,1),(184,1,184,1),(185,1,185,1),(186,1,186,1),(187,1,187,1),(188,1,188,1),(189,1,189,1),(190,1,190,1),
(191,1,191,1),(192,1,192,1),(193,1,193,1);
