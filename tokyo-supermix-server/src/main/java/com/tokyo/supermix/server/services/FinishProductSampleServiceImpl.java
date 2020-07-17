package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class FinishProductSampleServiceImpl implements FinishProductSampleService {
  @Autowired
  FinishProductSampleRepository finishProductSampleRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;

  @Autowired
  private MixDesignRepository mixDesignRepository;

  @Transactional(readOnly = true)
  public boolean isFinishProductCodeExist(String code) {
    return finishProductSampleRepository.existsByFinishProductCode(code);
  }

  @Transactional()
  public void saveFinishProductSample(FinishProductSample finishProductSample) {
    if (finishProductSample.getCode() == null) {
      String plantPrefix = mixDesignRepository.getOne(finishProductSample.getMixDesign().getCode())
          .getPlant().getCode();
      String codePrefix = plantPrefix + "-PP-";
      List<FinishProductSample> finishProductSampleList =
          finishProductSampleRepository.findByCodeContaining(codePrefix);
      if (finishProductSampleList.size() == 0) {
        finishProductSample.setCode(codePrefix + String.format("%04d", 1));
      } else {
        finishProductSample.setCode(
            codePrefix + String.format("%04d", maxNumberFromCode(finishProductSampleList) + 1));
      }
    }

    finishProductSample.setStatus(Status.NEW);
    finishProductSampleRepository.save(finishProductSample);
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<FinishProductSample> finishProductSampleList) {
    List<Integer> list = new ArrayList<Integer>();
    finishProductSampleList.forEach(obj -> {
      String code = obj.getCode();
      list.add(getNumberFromCode(code.substring(code.length() - code.indexOf("-"))));
    });
    return Collections.max(list);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getAllFinishProductSamples() {
    return finishProductSampleRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductSampleExist(String code) {
    return finishProductSampleRepository.existsById(code);
  }

  @Transactional(readOnly = true)
  public FinishProductSample getFinishProductSampleById(String code) {
    return finishProductSampleRepository.findById(code).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductSample(String code) {
    finishProductSampleRepository.deleteById(code);
  }

  public boolean isUpdatedFinishProductCodeExist(String code, String finishProductCode) {
    if ((!getFinishProductSampleById(code).getFinishProductCode().equals(finishProductCode))
        && (isFinishProductCodeExist(finishProductCode))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isMixDesignCodeExist(String code) {
    return finishProductSampleRepository.existsByMixDesignCode(code);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSampleByMixDesignCode(String mixDesignCode) {
    return finishProductSampleRepository.findByMixDesignCode(mixDesignCode);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSampleByEquipmentId(Long id) {
    return finishProductSampleRepository.findByEquipmentId(id);
  }

  @Transactional(readOnly = true)
  public Page<FinishProductSample> searchFinishProductSample(Predicate predicate, int page,
      int size) {
    return finishProductSampleRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSampleByPlantCode(String plantCode) {
    return finishProductSampleRepository.findByMixDesignPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSampleByStatus(Status status) {
    return finishProductSampleRepository.findByStatus(status);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductSampleStatusExist(Status status) {
    return finishProductSampleRepository.existsByStatus(status);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getAllFinishProductSamplesByPlant(UserPrincipal currentUser) {
    return finishProductSampleRepository.findByMixDesignPlantCodeIn(
        currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
            PermissionConstants.VIEW_FINISH_PRODUCT_SAMPLE));
  }
}
