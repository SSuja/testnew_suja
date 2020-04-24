package com.tokyo.supermix.querydsl.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.QEmployee;
import com.tokyo.supermix.data.entities.QPlant;
import com.tokyo.supermix.querydsl.common.SearchCriteria;
import com.tokyo.supermix.querydsl.common.SearchQueryCriteriaConsumer;


@org.springframework.stereotype.Service
public class EmployeeQuerydslServiceImpl implements EmployeeQuerydslService {
  @PersistenceContext
  private EntityManager entityManager;


  @Override
  public List<Employee> searchUser(List<SearchCriteria> params) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
    final Root r = query.from(Employee.class);

    Predicate predicate = builder.conjunction();
    SearchQueryCriteriaConsumer searchConsumer =
        new SearchQueryCriteriaConsumer(predicate, builder, r);
    params.stream().forEach(searchConsumer);
    predicate = searchConsumer.getPredicate();
    query.where(predicate);

    return entityManager.createQuery(query).getResultList();
  }

  @Override
  public List<Employee> findEmployeesByPlantCodeQueryDSL(String plantCode) {
    QEmployee employee = QEmployee.employee;
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QPlant plant = QPlant.plant;
    List<Employee> employees =
        queryFactory.selectFrom(employee).where(employee.plant.code.eq(plantCode)).fetch();
    return employees;
  }



}
