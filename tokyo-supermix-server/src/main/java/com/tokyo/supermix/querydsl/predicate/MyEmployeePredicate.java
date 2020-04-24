package com.tokyo.supermix.querydsl.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.querydsl.common.SearchCriteria;



public class MyEmployeePredicate {
  private SearchCriteria criteria;

  public MyEmployeePredicate(final SearchCriteria criteria) {
    this.criteria = criteria;
  }

  public BooleanExpression getPredicate() {
    PathBuilder<Employee> entityPath = new PathBuilder<>(Employee.class, "employee");

    if (isNumeric(criteria.getValue().toString())) {
      NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
      int value = Integer.parseInt(criteria.getValue().toString());
      switch (criteria.getOperation()) {
        case ":":
          return path.eq(value);
        case ">":
          return path.goe(value);
        case "<":
          return path.loe(value);
      }
    } else {
      StringPath path = entityPath.getString(criteria.getKey());
      if (criteria.getOperation().equalsIgnoreCase(":")) {
        return path.containsIgnoreCase(criteria.getValue().toString());
      }
    }
    return null;
  }

  public SearchCriteria getCriteria() {

    return criteria;

  }



  public void setCriteria(final SearchCriteria criteria) {

    this.criteria = criteria;

  }



  public static boolean isNumeric(final String str) {

    try {

      Integer.parseInt(str);

    } catch (final NumberFormatException e) {

      return false;

    }

    return true;

  }
}
