package com.tokyo.supermix.querydsl.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.QEmployee;



public interface MyEmployeeRepository extends JpaRepository<Employee, Long>,
    QuerydslPredicateExecutor<Employee>, QuerydslBinderCustomizer<QEmployee> {
  @Override

  default public void customize(final QuerydslBindings bindings, final QEmployee root) {

    bindings.bind(String.class)

        .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

    bindings.excluding(root.email);

  }
}
