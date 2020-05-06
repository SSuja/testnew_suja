package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;
import com.tokyo.supermix.data.repositories.FinishProductSampleIssueRepository;

@Service
public class FinishProductSampleIssueServiceImpl implements FinishProductSampleIssueService {

  @Autowired
  public FinishProductSampleIssueRepository finishProductSampleIssueRepository;

  @Transactional(readOnly = true)
  public List<FinishProductSampleIssue> getAllFinishProductSampleIssues() {
    return finishProductSampleIssueRepository.findAll();
  }

  @Transactional
  public void saveFinishProductSampleIssue(FinishProductSampleIssue finishProductSampleIssue) {
    finishProductSampleIssueRepository.save(finishProductSampleIssue);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductSampleIssue(Long id) {
    finishProductSampleIssueRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public FinishProductSampleIssue getFinishProductSampleIssueById(Long id) {
    return finishProductSampleIssueRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isIdExists(Long id) {
    return finishProductSampleIssueRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public Page<FinishProductSampleIssue> searchFinishProductSampleIssue(Predicate predicate,
      int size, int page) {
    return finishProductSampleIssueRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }
}
