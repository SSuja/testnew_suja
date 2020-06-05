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