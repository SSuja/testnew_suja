--create Role 
INSERT INTO `tokyo-supermix`.`role` (`id`, `role_name`) VALUES ('1', 'ADMIN');

--create User 
INSERT INTO `tokyo-supermix`.`user` (`id`, `email`, `password`, `user_name`, `role_id`, `created_at`, `updated_at`) VALUES ('1', 'admin@gmail.com', '$2y$10$WYI8/0dTM5y.0VZKCEbFWuU1Y39zOx6V3oFj6EZvh6AGXp0T.VQVK', 'admin', '1', '2020-05-06 16:58:02', '2020-05-06 21:28:58');
--admin pwd --

--create Permission--
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('1', 'add_test_configure');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('2', 'get_test_configure');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('3', 'edit_test_configure');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('4', 'delete_test_configure');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('5', 'add_test_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('6', 'get_test_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('7', 'edit_test_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('8', 'delete_test_parameter');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('9', 'add_equation');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('10', 'get_equation');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('11', 'edit_equation');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('12', 'delete_equation');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('13', 'add_equation_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('14', 'get_equation_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('15', 'edit_equation_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('16', 'delete_equation_parameter');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('17', 'add_accepted_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('18', 'get_accepted_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('19', 'edit_accepted_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('20', 'delete_accepted_value');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('21', 'add_accepted_admixture_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('22', 'get_accepted_admixture_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('23', 'edit_accepted_admixture_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('24', 'delete_accepted_admixture_value');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('25', 'add_incoming_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('26', 'get_incoming_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('27', 'edit_incoming_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('28', 'delete_incoming_sample');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('29', 'add_process_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('30', 'get_process_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('31', 'edit_process_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('32', 'delete_process_sample');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('33', 'add_finish_product_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('34', 'get_finish_product_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('35', 'edit_finish_product_sample');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('36', 'delete_finish_product_sample');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('37', 'add_finish_product_sample_issue');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('38', 'get_finish_product_sample_issue');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('39', 'edit_finish_product_sample_issue');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('40', 'delete_finish_product_sample_issue');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('41', 'add_process_sample_load');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('42', 'get_process_sample_load');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('43', 'edit_process_sample_load');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('44', 'delete_process_sample_load');


INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('45', 'add_material_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('46', 'get_material_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('47', 'delete_material_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('48', 'edit_material_test');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('49', 'get_test_type');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('50', 'add_test_type');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('51', 'delete_test_type');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('52', 'edit_test_type');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('53', 'add_concrete_test_type');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('54', 'get_concrete_test_type');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('55', 'delete_concrete_test_type');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('56', 'edit_concrete_test_type');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('57', 'add_concrete_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('58', 'get_concrete_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('59', 'delete_concrete_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('60', 'edit_concrete_test');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('61', 'get_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('62', 'add_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('63', 'delete_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('64', 'edit_parameter');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('65', 'add_quality_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('66', 'get_quality_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('67', 'delete_quality_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('68', 'edit_quality_parameter');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('69', 'add_material_quality_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('70', 'get_material_quality_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('71', 'delete_material_quality_parameter');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('72', 'edit_material_quality_parameter');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('73', 'add_project');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('74', 'get_project');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('75', 'delete_project');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('76', 'edit_project');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('77', 'add_pour');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('78', 'get_pour');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('79', 'delete_pour');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('80', 'edit_pour');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('81', 'add_finess_module');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('82', 'get_finess_module');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('83', 'delete_finess_module');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('84', 'edit_finess_module');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('85', 'add_sieve_accepted_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('86', 'get_sieve_accepted_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('87', 'edit_sieve_accepted_value');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('88', 'delete_sieve_accepted_value');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('89', 'add_sieve_size');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('90', 'get_sieve_size');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('91', 'edit_sieve_size');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('92', 'delete_sieve_size');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('93', 'add_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('94', 'get_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('95', 'delete_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('96', 'edit_test');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('97', 'add_material_test_trial');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('98', 'get_material_test_trial');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('99', 'add_sieve_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('100', 'get_sieve_test');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('101', 'delete_sieve_test');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('102', 'add_sieve_test_trial');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('103', 'get_sieve_test_trial');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('104', 'add_mix_design');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('105', 'get_mix_design');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('106', 'delete_mix_design');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('107', 'edit_mix_design');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('108', 'get_plant');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('109', 'add_plant');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('110', 'edit_plant');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('111','delete_plant');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('112', 'get_concrete_mixer');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('113', 'add_concrete_mixer');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('114', 'edit_concrete_mixer');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('115','delete_concrete_mixer');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('116', 'get_designation');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('117', 'add_designation');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('118', 'edit_designation');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('119','delete_designation');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('120', 'get_employee');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('121', 'add_employee');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('122', 'edit_employee');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('123','delete_employee');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('124', 'get_customer');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('125', 'add_customer');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('126', 'edit_customer');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('127','delete_customer');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('128', 'get_supplier_category');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('129', 'add_supplier_category');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('130', 'edit_supplier_category');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('131','delete_supplier_category');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('132', 'get_supplier');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('133', 'add_supplier');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('134', 'edit_supplier');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('135','delete_supplier');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('136', 'get_material_category');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('137', 'add_material_category');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('138', 'edit_material_category');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('139','delete_material_category');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('140', 'get_material_sub_category');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('141', 'add_material_sub_category');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('142', 'edit_material_sub_category');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('143','delete_material_sub_category');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('144', 'get_raw_material');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('145', 'add_raw_material');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('146', 'edit_raw_material');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('147','delete_raw_material');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('148', 'get_unit');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('149', 'add_unit');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('150', 'edit_unit');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('151','delete_unit');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('152', 'get_material_state');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('153', 'add_material_state');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('154', 'edit_material_state');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('155','delete_material_state');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('156', 'get_equipment');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('157', 'add_equipment');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('158', 'edit_equipment');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('159','delete_equipment');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('160', 'get_plant_equipment');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('161', 'add_plant_equipment');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('162', 'edit_plant_equipment');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('163','delete_plant_equipment');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('164', 'get_plant_equipment_calibration');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('165', 'add_plant_equipment_calibration');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('166', 'edit_plant_equipment_calibration');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('167','delete_plant_equipment_calibration');



INSERT INTO `tokyo-supermix`.permission_role(role_id,permission_id)VALUES(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(1,41),(1,42),(1,43),(1,44),(1,45),(1,46),(1,47),(1,48),(1,49),(1,50),(1,51),(1,52),(1,53),(1,54),(1,55),(1,56),(1,57),(1,58),(1,59),(1,60),(1,61),(1,62),(1,63),(1,64),(1,65),(1,66),(1,67),(1,68),(1,69),(1,70),(1,71),(1,72),(1,73),(1,74),(1,75),(1,76),(1,77),(1,78),(1,79),(1,80),(1,81),(1,82),(1,83),(1,84),(1,85),(1,86),(1,87),(1,88),(1,89),(1,90),(1,91),(1,92),(1,93),(1,94),(1,95),(1,96),(1,97),(1,98),(1,99),(1,100),(1,101),(1,102),(1,103),(1,104),(1,105),(1,106),(1,107),(1,108),(1,109),(1,110),(1,111),(1,112),(1,113),(1,114),(1,115),(1,116),(1,117),(1,118),(1,119),(1,120),(1,121),(1,122),(1,123),(1,124),(1,125),(1,126),(1,127),(1,128),(1,129),(1,130),(1,131),(1,132),(1,133),(1,134),(1,135),(1,136),(1,137),(1,138),(1,139),(1,140),(1,141),(1,142),(1,143),(1,144),(1,145),(1,146),(1,147),(1,148),(1,149),(1,150),(1,151),(1,152),(1,153),(1,154),(1,155),(1,156),(1,157),(1,158),(1,159),(1,160),(1,161),(1,162),(1,163),(1,164),(1,165),(1,166),(1,167);
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
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('12', 'test_report');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('13', 'test_configuration');
INSERT INTO `tokyo-supermix`.`main_route` (`id`, `name`) VALUES ('14', 'manage_test');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('31', 'finish_product_sample_issue', '10');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('32', 'mix_design', '11');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('33', 'raw_material_test_result', '12');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('34', 'sieve_test_result', '12');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('35', 'finish_product_test_result', '12');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('36', 'add_material_test', '13');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('37', 'add_concrete_test', '13');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('38', 'manage_tests', '13');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('39', 'manage_accepted_value', '13');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('40', 'manage_test_parameter', '13');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('41', 'manage_equation', '13');

INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('1', 'get_plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('2', 'add_plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('3', 'edit_plant', '1');
INSERT INTO `tokyo-supermix`.`permission` (`id`, `name`, `sub_route_id`) VALUES ('4', 'delete_plant', '1');

INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('21', 'quality_parameter', '7');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('22', 'quality_parameter_value', '7');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('23', 'project', '8');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('24', 'pour', '8');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('25', 'sieve_size', '9');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('26', 'sieve_accepted_value', '9');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('27', 'finess_module', '9');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('28', 'incoming_sample', '10');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('29', 'process_sample', '10');
INSERT INTO `tokyo-supermix`.`sub_route` (`id`, `name`, `main_route_id`) VALUES ('30', 'finish_product_sample', '10');

