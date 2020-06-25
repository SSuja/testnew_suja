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

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('4', 'employee', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('13', 'view_employee', '4');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('14', 'create_employee', '4');
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
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('25', 'view_supplier', '7');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('26', 'create_supplier', '7');
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

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('23', 'project', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('61', 'view_project', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('62', 'create_project', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('63', 'edit_project', '23');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('64', 'delete_project', '23');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('24', 'pour', '8');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('65', 'view_pour', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('66', 'create_pour', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('67', 'edit_pour', '24');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('68', 'delete_pour', '24');

INSERT INTO `tokyo-supermix`.`sub_module` (`id`, `name`, `main_module_id`) VALUES ('25', 'role', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('69', 'view_role', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('70', 'create_role', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('71', 'edit_role', '25');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_module_id`) VALUES ('72', 'delete_role', '25');


INSERT INTO `tokyo-supermix`.`role_permission` (`id`,`role_id`,`permission_id`,`status`)VALUES(1,1,1,1),(2,1,2,1),(3,1,3,1),(4,1,4,1),(5,1,5,1),(6,1,6,1),(7,1,7,1),(8,1,8,1),(9,1,9,1),(10,1,10,1),
(11,1,11,1),(12,1,12,1),(13,1,13,1),(14,1,14,1),(15,1,15,1),(16,1,16,1),(17,1,17,1),(18,1,18,1),(19,1,19,1),(20,1,20,1),(21,1,21,1),(22,1,22,1),(23,1,23,1),(24,1,24,1),(25,1,25,1),
(26,1,26,1),(27,1,27,1),(28,1,28,1),(29,1,29,1),(30,1,30,1),(31,1,31,1),(32,1,32,1),(33,1,33,1),(34,1,34,1),(35,1,35,1),(36,1,36,1),(37,1,37,1),(38,1,38,1),(39,1,39,1),(40,1,40,1),
(41,1,41,1),(42,1,42,1),(43,1,43,1),(44,1,44,1),(45,1,45,1),(46,1,46,1),(47,1,47,1),(48,1,48,1),(49,1,49,1),(50,1,50,1),(51,1,51,1),(52,1,52,1),(53,1,53,1),(54,1,54,1),(55,1,55,1),
(56,1,56,1),(57,1,57,1),(58,1,58,1),(59,1,59,1),(60,1,60,1),(61,1,61,1),(62,1,62,1),(63,1,63,1),(64,1,64,1),(65,1,65,1),(66,1,66,1),(67,1,67,1),(68,1,68,1),(69,1,69,1),(70,1,70,1),
(71,1,71,1),(72,1,72,1);



