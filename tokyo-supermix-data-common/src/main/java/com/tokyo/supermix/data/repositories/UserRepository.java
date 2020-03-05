package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByUserName(String userName);

  boolean existsByEmployeeId(Long employeeId);
}
