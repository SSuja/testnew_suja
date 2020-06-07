-- plant
INSERT INTO `tokyo-supermix`.`plant` (`code`, `address`, `name`, `phone_number`) VALUES ('pl001', 'jaffna', 'jaffnaplant', '0215486546');
-- designation
INSERT INTO `tokyo-supermix`.`designation` (`id`, `description`, `name`) VALUES ('1', 'administration', 'administrater');
-- employee
INSERT INTO `tokyo-supermix`.`employee` (`id`, `address`, `email`, `first_name`, `last_name`, `phone_number`, `designation_id`, `plant_code`, `has_user`) VALUES ('1', 'jaffna', 'tokyotester4@gmail.com', 'tokyoTester', 'supermix', '02155489', '1', 'pl001', 1);
-- role
INSERT INTO `tokyo-supermix`.`role` (`id`, `name`) VALUES ('1', 'ADMIN');
-- user
INSERT INTO `tokyo-supermix`.`user` (`id`, `email`, `password`, `user_name`, `role_id`,`employee_id`, `created_at`, `updated_at`) VALUES ('1', 'admin@gmail.com', '$2y$10$WYI8/0dTM5y.0VZKCEbFWuU1Y39zOx6V3oFj6EZvh6AGXp0T.VQVK', 'admin', '1','1', '2020-05-06 16:58:02', '2020-05-06 21:28:58');

--privillege
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('1', 'plant');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('2', 'category');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('3', 'unit');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('4', 'material');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('5', 'equipment');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('6', 'test');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('7', 'parameter');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('8', 'site');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('9', 'sieve');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('10', 'sample');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('11', 'mix_design');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('13', 'test_configuration');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('14', 'manage_test');

--privilege model
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('1', 'plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('1', 'get_plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('2', 'add_plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('3', 'edit_plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('4', 'delete_plant', '1');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('2', 'congrete_mixer', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('5', 'get_concrete_mixer', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('6', 'add_concrete_mixer', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('7', 'edit_concrete_mixer', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('8', 'delete_concrete_mixer', '2');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('3', 'designation', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('9', 'get_designation', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('10', 'add_designation', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('11', 'edit_designation', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('12', 'delete_designation', '3');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('4', 'employee', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('13', 'get_employee', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('14', 'add_employee', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('15', 'edit_employee', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('16', 'delete_employee', '4');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('5', 'customer', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('17', 'get_customer', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('18', 'add_customer', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('19', 'edit_customer', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('20', 'delete_customer', '5');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('6', 'supplier_category', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('21', 'get_supplier_category', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('22', 'add_supplier_category', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('23', 'edit_supplier_category', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('24', 'delete_supplier_category', '6');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('7', 'supplier', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('25', 'get_supplier', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('26', 'add_supplier', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('27', 'edit_supplier', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('28', 'delete_supplier', '7');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('8', 'material_category', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('29', 'get_material_category', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('30', 'add_material_category', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('31', 'edit_material_category', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('32', 'delete_material_category', '8');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('9', 'material_sub_category', '2');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('33', 'get_material_sub_category', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('34', 'add_material_sub_category', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('35', 'edit_material_sub_category', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('36', 'delete_material_sub_category', '9');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('10', 'manage_unit', '3');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('37', 'get_unit', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('38', 'add_unit', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('39', 'edit_unit', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('40', 'delete_unit', '10');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('11', 'material_state', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('41', 'get_material_state', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('42', 'add_material_state', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('43', 'edit_material_state', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('44', 'delete_material_state', '11');

 

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('12', 'materials', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('45', 'get_raw_material', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('46', 'add_raw_material', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('47', 'edit_raw_material', '12');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('48', 'delete_raw_material', '12');

 

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('13', 'equipment', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('49', 'get_equipment', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('50', 'add_equipment', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('51', 'edit_equipment', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('52', 'delete_equipment', '13');

 

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('14', 'plant_equipment', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('53', 'get_plant_equipment', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('54', 'add_plant_equipment', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('55', 'edit_plant_equipment', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('56', 'delete_plant_equipment', '14');

 

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('15', 'plant_equipment_calibration', '5');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('57', 'get_plant_equipment_calibration', '15');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('58', 'add_plant_equipment_calibration', '15');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('59', 'edit_plant_equipment_calibration', '15');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('60', 'delete_plant_equipment_calibration', '15');

 

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('16', 'material_test', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('61', 'get_test', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('62', 'add_test', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('63', 'edit_test', '16');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('64', 'delete_test', '16');

 

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('17', 'test_type', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('65', 'get_test_type', '17');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('66', 'add_test_type', '17');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('67', 'edit_test_type', '17');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('68', 'delete_test_type', '17');

 

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('18', 'concrete_test_type', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('69', 'get_concrete_test_type', '18');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('70', 'add_concrete_test_type', '18');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('71', 'edit_concrete_test_type', '18');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('72', 'delete_concrete_test_type', '18');

 

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('19', 'concrete_test', '6');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('73', 'get_concrete_test', '19');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('74', 'add_concrete_test', '19');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('75', 'edit_concrete_test', '19');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('76', 'delete_concrete_test', '19');

 

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('20', 'parameter', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('77', 'get_parameter', '20');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('78', 'add_parameter', '20');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('79', 'edit_parameter', '20');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('80', 'delete_parameter', '20');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('21', 'quality_parameter', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('81', 'get_quality_parameter', '21');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('82', 'add_quality_parameter', '21');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('83', 'edit_quality_parameter', '21');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('84', 'delete_quality_parameter', '21');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('22', 'quality_parameter_value', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('85', 'get_material_quality_parameter', '22');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('86', 'add_material_quality_parameter', '22');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('87', 'edit_material_quality_parameter', '22');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('88', 'delete_material_quality_parameter', '22');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('23', 'project', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('89', 'get_project', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('90', 'add_project', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('91', 'edit_project', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('92', 'delete_project', '23');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('24', 'pour', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('93', 'get_pour', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('94', 'add_pour', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('95', 'edit_pour', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('96', 'delete_pour', '24');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('25', 'sieve_size', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('97', 'get_sieve_size', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('98', 'add_sieve_size', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('99', 'edit_sieve_size', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('100', 'delete_sieve_size', '25');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('26', 'sieve_accepted_value', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('101', 'get_sieve_accepted_value', '26');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('102', 'add_sieve_accepted_value', '26');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('103', 'edit_sieve_accepted_value', '26');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('104', 'delete_sieve_accepted_value', '26');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('27', 'finess_module', '9');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('105', 'get_finess_module', '27');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('106', 'add_finess_module', '27');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('107', 'edit_finess_module', '27');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('108', 'delete_finess_module', '27');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('28', 'incoming_sample', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('109', 'get_incoming_sample', '28');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('110', 'add_incoming_sample', '28');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('111', 'edit_incoming_sample', '28');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('112', 'delete_incoming_sample', '28');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('29', 'process_sample', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('113', 'get_process_sample', '29');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('114', 'add_process_sample', '29');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('115', 'edit_process_sample', '29');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('116', 'delete_process_sample', '29');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('30', 'finish_product_sample', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('117', 'get_finish_product_sample', '30');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('118', 'add_finish_product_sample', '30');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('119', 'edit_finish_product_sample', '30');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('120', 'delete_finish_product_sample', '30');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('31', 'finish_product_sample_issue', '10');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('121', 'get_finish_product_sample_issue', '31');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('122', 'add_finish_product_sample_issue', '31');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('123', 'edit_finish_product_sample_issue', '31');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('124', 'delete_finish_product_sample_issue', '31');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('32', 'mix_design', '11');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('125', 'get_mix_design', '32');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('126', 'add_mix_design', '32');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('127', 'edit_mix_design', '32');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('128', 'delete_mix_design', '32');



INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('36', 'add_material_test', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('141', 'get_material_test', '36');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('142', 'add_material_test', '36');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('143', 'edit_material_test', '36');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('144', 'delete_material_test', '36');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('37', 'add_concrete_test', '13');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('145', 'get_concrete_test', '37');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('146', 'add_concrete_test', '37');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('147', 'edit_concrete_test', '37');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('148', 'delete_concrete_test', '37');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('38', 'manage_tests', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('149', 'get_test_configure', '38');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('150', 'add_test_configure', '38');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('151', 'edit_test_configure', '38');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('152', 'delete_test_configure', '38');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('39', 'manage_accepted_value', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('153', 'get_accepted_value', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('154', 'add_accepted_value', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('155', 'edit_accepted_value', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('156', 'delete_accepted_value', '39');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('165', 'get_accepted_admixture_value', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('166', 'add_accepted_admixture_value', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('167', 'edit_accepted_admixture_value', '39');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('168', 'delete_accepted_admixture_value', '39');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('40', 'manage_test_parameter', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('157', 'get_test_parameter', '40');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('158', 'add_test_parameter', '40');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('159', 'edit_test_parameter', '40');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('160', 'delete_test_parameter', '40');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('41', 'manage_equation', '14');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('161', 'get_equation', '41');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('162', 'add_equation', '41');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('163', 'edit_equation', '41');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('164', 'delete_equation', '41');


INSERT INTO `tokyo-supermix`.role_permission(role_id,permission_id, status)VALUES(1,1,1),(1,2,1),(1,3,1),(1,4,1),(1,5,1),(1,6,1),(1,7,1),(1,8,1),(1,9,1),(1,10,1),(1,11,1),(1,12,1),(1,13,1),(1,14,1),(1,15,1),(1,16,1),(1,17,1),(1,18,1),(1,19,1),(1,20,1),(1,21,1),(1,22,1),(1,23,1),(1,24,1),(1,25,1),(1,26,1),(1,27,1),(1,28,1),(1,29,1),(1,30,1),(1,31,1),(1,32,1),(1,33,1),(1,34,1),(1,35,1),(1,36,1),(1,37,1),(1,38,1),(1,39,1),(1,40,1),(1,41,1),(1,42,1),(1,43,1),(1,44,1),(1,45,1),(1,46,1),(1,47,1),(1,48,1),(1,49,1),(1,50,1),(1,51,1),(1,52,1),(1,53,1),(1,54,1),(1,55,1),(1,56,1),(1,57,1),(1,58,1),(1,59,1),(1,60,1),(1,61,1),(1,62,1),(1,63,1),(1,64,1),(1,65,1),(1,66,1),(1,67,1),(1,68,1),(1,69,1),(1,70,1),(1,71,1),(1,72,1),(1,73,1),(1,74,1),(1,75,1),(1,76,1),(1,77,1),(1,78,1),(1,79,1),(1,80,1),(1,81,1),(1,82,1),(1,83,1),(1,84,1),(1,85,1),(1,86,1),(1,87,1),(1,88,1),(1,89,1),(1,90,1),(1,91,1),(1,92,1),(1,93,1),(1,94,1),(1,95,1),(1,96,1),(1,97,1),(1,98,1),(1,99,1),(1,100,1),(1,101,1),(1,102,1),(1,103,1),(1,104,1),(1,105,1),(1,106,1),(1,107,1),(1,108,1),(1,109,1),(1,110,1),(1,111,1),(1,112,1),(1,113,1),(1,114,1),(1,115,1),(1,116,1),(1,117,1),(1,118,1),(1,119,1),(1,120,1),(1,121,1),(1,122,1),(1,123,1),(1,124,1),(1,125,1),(1,126,1),(1,127,1),(1,128,1),(1,141,1),(1,142,1),(1,143,1),(1,144,1),(1,145,1),(1,146,1),(1,147,1),(1,148,1),(1,149,1),(1,150,1),(1,151,1),(1,152,1),(1,153,1),(1,154,1),(1,155,1),(1,156,1),(1,157,1),(1,158,1),(1,159,1),(1,160,1),(1,161,1),(1,162,1),(1,163,1),(1,164,1),(1,165,1),(1,166,1),(1,167,1);
