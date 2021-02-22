package com.tokyo.supermix.data.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long>, QuerydslPredicateExecutor<Role>,
    PagingAndSortingRepository<Role, Long> {
  Role findByName(String roleName);

  Boolean existsByName(String roleName);
}
