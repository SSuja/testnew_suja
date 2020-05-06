package com.tokyo.supermix.data.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findUserById(Long id);

	User findByEmail(String email);

	Optional<User> findByUserNameOrEmail(String username, String email);

	List<User> findByIdIn(List<Long> userIds);

	Boolean existsByUserName(String username);

	Boolean existsByEmail(String email);

	boolean existsByEmployeeId(Long employeeId);

}
