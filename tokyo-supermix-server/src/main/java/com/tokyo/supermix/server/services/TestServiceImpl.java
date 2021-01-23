package com.tokyo.supermix.server.services;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.QTest;
import com.tokyo.supermix.data.entities.Test;
import com.tokyo.supermix.data.repositories.TestRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

@Service
public class TestServiceImpl implements TestService {

  @Autowired
  private TestRepository testRepository;

  @Transactional
  public void saveTest(Test test) {
    testRepository.save(test);
  }

  @Transactional(readOnly = true)
  public boolean isTestExist(Long id) {
    return testRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public boolean isTestExist(String name) {
    return testRepository.existsByName(name);
  }

  @Transactional(readOnly = true)
  public List<Test> getAllTestByPagination(Pageable pegeable) {
    return testRepository.findAllByOrderByIdDesc(pegeable).toList();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteTest(Long id) {
    testRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public Test getTestById(Long id) {
    return testRepository.findById(id).get();
  }

  public boolean isUpdatedTestExist(Long id, String name) {
    if ((!getTestById(id).getName().equalsIgnoreCase(name)) && (isTestExist(name))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<Test> searchTests(String name, BooleanBuilder booleanBuilder, int page, int size,
      Pageable pageable, Pagination pagination) {
    if (name != null && !name.isEmpty()) {
      booleanBuilder.and(QTest.test.name.contains(name));
    }
    pagination.setTotalRecords(
        ((Collection<Test>) testRepository.findAll(booleanBuilder)).stream().count());
    return testRepository.findAll(booleanBuilder, pageable).toList();
  }

  @Transactional(readOnly = true)
  public Long countTest() {
    return testRepository.count();
  }

  @Transactional(readOnly = true)
  public List<Test> getAllTests() {
    return testRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
  }
}
