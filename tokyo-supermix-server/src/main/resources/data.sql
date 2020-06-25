---- plant
INSERT INTO `tokyo-supermix`.`plant` (`code`, `address`, `name`, `phone_number`,`fax_number`) VALUES ('PR', '77, New,Nuge Road', 'Peliyagoda', '0114587452','0112-945866');
---- designation
INSERT INTO `tokyo-supermix`.`designation` (`id`, `description`, `name`) VALUES ('1', 'Main Admin', 'Administrator');
-- employee
INSERT INTO `tokyo-supermix`.`employee` (`id`, `address`, `email`, `first_name`, `last_name`, `phone_number`, `designation_id`, `plant_code`, `has_user`) VALUES ('1', 'Colombo-12', 'tokyotester4@gmail.com', 'Tokyo', 'Tester', '0761452365', '1', 'PR', 1);
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

--privilege model
INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('1', 'plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('1', 'get_plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('2', 'add_plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('3', 'edit_plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('4', 'delete_plant', '1');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('2', 'congrete_mixer', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('5', 'get_concrete_mixer', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('6', 'add_concrete_mixer', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('7', 'edit_concrete_mixer', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('8', 'delete_concrete_mixer', '2');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('3', 'designation', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('9', 'get_designation', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('10', 'add_designation', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('11', 'edit_designation', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('12', 'delete_designation', '3');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('4', 'employee', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('13', 'get_employee', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('14', 'add_employee', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('15', 'edit_employee', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('16', 'delete_employee', '4');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('5', 'customer', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('17', 'view_customer', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('18', 'create_customer', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('19', 'edit_customer', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('20', 'delete_customer', '5');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('6', 'supplier_category', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('21', 'view_supplier_category', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('22', 'create_supplier_category', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('23', 'edit_supplier_category', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('24', 'delete_supplier_category', '6');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('7', 'supplier', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('25', 'get_supplier', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('26', 'add_supplier', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('27', 'edit_supplier', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('28', 'delete_supplier', '7');

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

 

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('15', 'plant_equipment_calibration', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('57', 'view_plant_equipment_calibration', '15');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('58', 'create_plant_equipment_calibration', '15');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('59', 'edit_plant_equipment_calibration', '15');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('60', 'delete_plant_equipment_calibration', '15');

 

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('16', 'material_test', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('61', 'get_test', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('62', 'add_test', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('63', 'edit_test', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('64', 'delete_test', '16');

 

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('17', 'test_type', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('65', 'get_test_type', '17');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('66', 'add_test_type', '17');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('67', 'edit_test_type', '17');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('68', 'delete_test_type', '17');

 

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('18', 'concrete_test_type', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('69', 'get_concrete_test_type', '18');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('70', 'add_concrete_test_type', '18');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('71', 'edit_concrete_test_type', '18');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('72', 'delete_concrete_test_type', '18');

 

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('19', 'concrete_test', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('73', 'get_concrete_test', '19');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('74', 'add_concrete_test', '19');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('75', 'edit_concrete_test', '19');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('76', 'delete_concrete_test', '19');

 

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('20', 'parameter', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('77', 'get_parameter', '20');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('78', 'add_parameter', '20');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('79', 'edit_parameter', '20');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('80', 'delete_parameter', '20');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('21', 'quality_parameter', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('81', 'get_quality_parameter', '21');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('82', 'add_quality_parameter', '21');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('83', 'edit_quality_parameter', '21');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('84', 'delete_quality_parameter', '21');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('22', 'quality_parameter_value', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('85', 'get_material_quality_parameter', '22');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('86', 'add_material_quality_parameter', '22');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('87', 'edit_material_quality_parameter', '22');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('88', 'delete_material_quality_parameter', '22');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('23', 'project', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('89', 'view_project', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('90', 'create_project', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('91', 'edit_project', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('92', 'delete_project', '23');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('24', 'pour', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('93', 'view_pour', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('94', 'create_pour', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('95', 'edit_pour', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('96', 'delete_pour', '24');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('25', 'sieve_size', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('97', 'get_sieve_size', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('98', 'add_sieve_size', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('99', 'edit_sieve_size', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('100', 'delete_sieve_size', '25');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('26', 'sieve_accepted_value', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('101', 'get_sieve_accepted_value', '26');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('102', 'add_sieve_accepted_value', '26');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('103', 'edit_sieve_accepted_value', '26');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('104', 'delete_sieve_accepted_value', '26');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('27', 'finess_module', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('105', 'get_finess_module', '27');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('106', 'add_finess_module', '27');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('107', 'edit_finess_module', '27');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('108', 'delete_finess_module', '27');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('28', 'incoming_sample', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('109', 'get_incoming_sample', '28');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('110', 'add_incoming_sample', '28');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('111', 'edit_incoming_sample', '28');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('112', 'delete_incoming_sample', '28');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('29', 'process_sample', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('113', 'get_process_sample', '29');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('114', 'add_process_sample', '29');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('115', 'edit_process_sample', '29');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('116', 'delete_process_sample', '29');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('30', 'finish_product_sample', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('117', 'get_finish_product_sample', '30');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('118', 'add_finish_product_sample', '30');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('119', 'edit_finish_product_sample', '30');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('120', 'delete_finish_product_sample', '30');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('31', 'finish_product_sample_issue', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('121', 'get_finish_product_sample_issue', '31');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('122', 'add_finish_product_sample_issue', '31');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('123', 'edit_finish_product_sample_issue', '31');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('124', 'delete_finish_product_sample_issue', '31');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('32', 'mix_design', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('125', 'get_mix_design', '32');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('126', 'add_mix_design', '32');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('127', 'edit_mix_design', '32');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('128', 'delete_mix_design', '32');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('33', 'raw_material_test_result', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('129', 'get_raw_material_test_result', '33');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('130', 'add_raw_material_test_result', '33');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('131', 'edit_raw_material_test_result', '33');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('132', 'delete_raw_material_test_result', '33');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('34', 'sieve_test_result', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('133', 'get_sieve_test_result', '34');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('134', 'add_sieve_test_result', '34');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('135', 'edit_sieve_test_result', '34');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('136', 'delete_sieve_test_result', '34');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('35', 'finish_product_test_result', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('137', 'get_finish_product_test_result', '35');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('138', 'add_finish_product_test_result', '35');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('139', 'edit_finish_product_test_result', '35');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('140', 'delete_finish_product_test_result', '35');


INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('36', 'add_material_test', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('141', 'get_material_test', '36');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('142', 'add_material_test', '36');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('143', 'edit_material_test', '36');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('144', 'delete_material_test', '36');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('37', 'add_concrete_test', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('145', 'get_concrete_test', '37');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('146', 'add_concrete_test', '37');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('147', 'edit_concrete_test', '37');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('148', 'delete_concrete_test', '37');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('38', 'manage_tests', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('149', 'get_test_configure', '38');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('150', 'add_test_configure', '38');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('151', 'edit_test_configure', '38');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('152', 'delete_test_configure', '38');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('39', 'manage_accepted_value', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('153', 'get_accepted_value', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('154', 'add_accepted_value', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('155', 'edit_accepted_value', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('156', 'delete_accepted_value', '39');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('165', 'get_accepted_admixture_value', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('166', 'add_accepted_admixture_value', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('167', 'edit_accepted_admixture_value', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('168', 'delete_accepted_admixture_value', '39');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('40', 'manage_test_parameter', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('157', 'get_test_parameter', '40');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('158', 'add_test_parameter', '40');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('159', 'edit_test_parameter', '40');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('160', 'delete_test_parameter', '40');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('41', 'manage_equation', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('161', 'get_equation', '41');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('162', 'add_equation', '41');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('163', 'edit_equation', '41');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('164', 'delete_equation', '41');


INSERT INTO `tokyo-supermix`.role_permission(id,role_id,permission_id, status)VALUES(1,1,1,1),(2,1,2,1),(3,1,3,1),(4,1,4,1),(5,1,5,1),(6,1,6,1),(7,1,7,1),(8,1,8,1),(9,1,9,1),(10,1,10,1)
