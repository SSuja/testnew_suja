package com.tokyo.supermix.data.repositories.auth;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.auth.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByUserName(String userName);
  boolean existsByEmployeeId(Long employeeId);
  User findUserById(Long id);
  Boolean existsByEmail(String email);
  User findByEmail(String email);
  Optional<User> findByUserNameOrEmail(String username, String email);
  User findByUserName(String username);
}
