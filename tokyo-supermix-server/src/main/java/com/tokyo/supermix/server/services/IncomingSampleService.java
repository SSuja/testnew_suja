package com.tokyo.supermix.server.services;

import java.sql.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;

public interface IncomingSampleService {
  public void deleteIncomingSample(String code);

  public IncomingSample getIncomingSampleById(String code);

  public List<IncomingSample> getAllIncomingSamples();

  // public List<IncomingSample> getAllIncomingSamplesByCurrentUser(
  // @CurrentUser UserPrincipal currentUser);

  boolean isIncomingSampleExist(String code);

  public void updateStatusForIncomingSample(String code, Status status);

  boolean isIncomingSampleStatusExist(Status status);

  public void createIncomingSample(IncomingSample incomingSample);

  public void updateIncomingSample(IncomingSample incomingSample);

  public List<IncomingSample> getIncomingSampleByStatus(Status status);

  // public Page<IncomingSample> searchIncomingSample(Predicate predicate, int page, int size);

  // public List<IncomingSample> getIncomingSampleByPlantCode(String plantCode);

  // public List<IncomingSample> getByMaterialSubCategoryPlantWise(Long materialSubCategoryId,
  // String plantCode);

  // public List<IncomingSample> getByMaterialSubCategory(Long materialSubCategoryId);

  public List<IncomingSample> getAllIncomingSamplesByCurrentUser(
      @CurrentUser UserPrincipal currentUser, Pageable pageable);

  public List<IncomingSample> getIncomingSampleByPlantCode(String plantCode, Pageable pageable);

  public Long getCountIncomingSample();

  public Long getCountIncomingSampleByPlantCode(String plantCode);

  public List<IncomingSample> getIncomingSampleCodeByPlantCode(String plantCode, String code);

  public List<IncomingSample> getIncomingSampleCode(String code);

  public List<IncomingSample> getByMaterialSubCategory(Long materialSubCategoryId, String code);

  public List<IncomingSample> getByMaterialSubCategoryPlantWise(Long materialSubCategoryId,
      String plantCode, String code);

  public List<IncomingSample> searchIncomingSample(String code, String vehicleNo, Date date,
      String status, String rawMaterialName, String plantName, String supplierName,
      BooleanBuilder booleanBuilder, Pageable pageable, String plantCode, Pagination pagination);

  public List<IncomingSample> getByMaterialSubCategory(Long materialSubCategoryId);

  public List<IncomingSample> getByMaterialSubCategoryPlantWise(Long materialSubCategoryId,
      String plantCode);

  public List<IncomingSample> getBySupplierId(Long supplierId);

  public List<IncomingSample> getByMaterialCategoryId(Long materialCategoryId);

  public List<IncomingSample> getByMaterialCategoryPlantWise(Long materialCategoryId,
      String plantCode);
}
