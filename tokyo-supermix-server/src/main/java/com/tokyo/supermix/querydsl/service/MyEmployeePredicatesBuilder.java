package com.tokyo.supermix.querydsl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.tokyo.supermix.querydsl.common.SearchCriteria;
import com.tokyo.supermix.querydsl.predicate.MyEmployeePredicate;

public final class MyEmployeePredicatesBuilder {
  private List<SearchCriteria> params;

  public MyEmployeePredicatesBuilder() {
    params = new ArrayList<>();
  }

  public MyEmployeePredicatesBuilder with(String key, String operation, Object value) {

    params.add(new SearchCriteria(key, operation, value));
    return this;
  }

  public BooleanExpression build() {
    if (params.size() == 0) {
      return null;
    }

    final List<BooleanExpression> predicates = params.stream().map(param -> {
      MyEmployeePredicate predicate = new MyEmployeePredicate(param);
      return predicate.getPredicate();
    }).filter(Objects::nonNull).collect(Collectors.toList());

    BooleanExpression result = Expressions.asBoolean(true).isTrue();
    for (BooleanExpression predicate : predicates) {
      result = result.and(predicate);
    }
    return result;
  }
}
