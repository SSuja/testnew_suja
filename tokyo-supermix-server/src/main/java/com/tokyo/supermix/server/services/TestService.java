package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.Test;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

public interface TestService {

  public void saveTest(Test test);

  public boolean isTestExist(Long id);

  public boolean isTestExist(String name);

  public List<Test> getAllTests();

  public List<Test> getAllTestByPagination(Pageable pegeable);

  public void deleteTest(Long id);

  public Test getTestById(Long id);

  public boolean isUpdatedTestExist(Long id, String name);

  public List<Test> searchTests(String name, BooleanBuilder booleanBuilder, int page, int size,
      Pageable pageable, Pagination pagination);

  public Long countTest();
}
