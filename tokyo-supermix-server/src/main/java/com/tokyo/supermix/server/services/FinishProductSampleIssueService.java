package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.FinishProductSampleResponseDto;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;

public interface FinishProductSampleIssueService {
  public List<FinishProductSampleIssue> getAllFinishProductSampleIssues();

  public void saveFinishProductSampleIssue(FinishProductSampleIssue finishProductSampleIssue);

  public void deleteFinishProductSampleIssue(String code);

  public FinishProductSampleIssue getFinishProductSampleIssueById(String code);

  public boolean isCodeExists(String code);

  public Page<FinishProductSampleIssue> searchFinishProductSampleIssue(Predicate predicate,
      int size, int page);

  public List<FinishProductSample> getFinishProductSampleIssueByPlantCode(String plantCode,
      Pageable pageable);

  public List<FinishProductSample> getAllFinishProductSampleIssueByPlant(UserPrincipal currentUser,
      Pageable pageable);

  public Long countFinishProductSampleIssue();

  public Long countFinishProductSampleIssueByPlant(String plantCode);

  public List<FinishProductSample> searchFinishProductSampleIssue(BooleanBuilder booleanBuilder,
      String finishProductCode, String equipmentName, String mixDesignCode, String plantName,
      String plantCode, Status status, String date, String code, String rawMaterialName,
      String workOrderNumber, String customer, String project, Pageable pageable,
      Pagination pagination);

  public List<FinishProductSample> getAllFinishProductSampleIssue();
}
