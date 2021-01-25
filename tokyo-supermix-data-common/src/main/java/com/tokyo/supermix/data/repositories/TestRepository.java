package com.tokyo.supermix.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.Test;

public interface TestRepository extends JpaRepository<Test, Long>, QuerydslPredicateExecutor<Test>,
    PagingAndSortingRepository<Test, Long> {

  boolean existsByName(String name);

  Page<Test> findAllByOrderByIdDesc(Pageable pegeable);
}
