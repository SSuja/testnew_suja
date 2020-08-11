package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;

public interface IncomingSampleService {
  public void deleteIncomingSample(String code);

  public IncomingSample getIncomingSampleById(String code);

  public List<IncomingSample> getAllIncomingSamples();

  public List<IncomingSample> getAllIncomingSamplesByCurrentUser(
      @CurrentUser UserPrincipal currentUser);

  boolean isIncomingSampleExist(String code);

  public void updateStatusForIncomingSample(String code, Status status);

  boolean isIncomingSampleStatusExist(Status status);

  public void createIncomingSample(IncomingSample incomingSample);

  public void updateIncomingSample(IncomingSample incomingSample);

  public List<IncomingSample> getIncomingSampleByStatus(Status status);

  public Page<IncomingSample> searchIncomingSample(Predicate predicate, int page, int size);

  public List<IncomingSample> getIncomingSampleByPlantCode(String plantCode);

  public List<IncomingSample> getByMaterialSubCategoryPlantWise(Long materialSubCategoryId,
      String plantCode);

  public List<IncomingSample> getByMaterialSubCategory(Long materialSubCategoryId);
}
