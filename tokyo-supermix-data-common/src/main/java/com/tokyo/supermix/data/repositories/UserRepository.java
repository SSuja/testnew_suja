package com.tokyo.supermix.data.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByUserName(String userName);
  boolean existsByEmployeeId(Long employeeId);
  User findUserById(Long id);
  Boolean existsByEmail(String email);
  User findByEmail(String email);
  Optional<User> findByUserNameOrEmail(String username, String email);
}
