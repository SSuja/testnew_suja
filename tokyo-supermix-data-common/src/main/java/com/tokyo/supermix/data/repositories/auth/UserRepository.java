package com.tokyo.supermix.data.repositories.auth;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.enums.UserType;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
  boolean existsByUserName(String userName);

  boolean existsByEmployeeId(Long employeeId);

  User findUserById(Long id);

  Boolean existsByEmail(String email);

  User findByEmail(String email);

  Optional<User> findByUserNameOrEmail(String username, String email);

  User findByUserName(String username);

  List<User> findByEmployeePlantCodeIn(List<String> plantCodes);

  List<User> findByUserTypeAndEmployeePlantCodeIn(UserType userType, List<String> plantCodes);

  List<User> findByUserType(UserType userType);

  List<User> findByEmployeePlantCode(String plantCode);

  Long countByEmployeePlantCode(String plantCode);

  List<User> findAllByEmployeePlantCode(String plantCode, Pageable pageable);
  
  List<User> findBySentMailAndUserType(boolean sentMail, UserType userType);

}
