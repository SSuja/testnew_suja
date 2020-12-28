package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.MixDesignResponseDto;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.MixDesignConfirmationToken;
import com.tokyo.supermix.data.entities.QMixDesign;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.MixDesignConfirmationTokenRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class MixDesignServiceImpl implements MixDesignService {
  @Autowired
  public MixDesignRepository mixDesignRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private EmailNotification emailNotification;
  @Autowired
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  MixDesignConfirmationTokenRepository  mixDesignConfirmationTokenRepository;
  @Autowired
  private Mapper mapper;

  @Transactional(readOnly = true)
  public List<MixDesign> getAllMixDesigns() {
    return mixDesignRepository.findAll();
  }

  @Transactional
  public String saveMixDesign(MixDesign mixDesign) {
    if (mixDesign.getCode() == null) {
      String rawMaterialName =
          rawMaterialRepository.findById(mixDesign.getRawMaterial().getId()).get().getName();
      String codePrefix = rawMaterialName + "-";
      List<MixDesign> mixDesignList = mixDesignRepository.findByCodeContaining(codePrefix);
      if (mixDesignList.size() == 0) {
        mixDesign.setCode(codePrefix + String.format("%03d", 1));
      } else {
        mixDesign.setCode(codePrefix + String.format("%03d", maxNumberFromCode(mixDesignList) + 1));
      }
    }
    mixDesign.setApproved(false);
    MixDesign mixDesignObj = mixDesignRepository.save(mixDesign);
    if (mixDesignObj != null) {
      emailNotification.sendMixDesignCreationEmail(mixDesignObj);
    }
    return mixDesign.getCode();
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<MixDesign> mixDesignList) {
    List<Integer> list = new ArrayList<Integer>();
    mixDesignList.forEach(obj -> {
      String code = obj.getCode();
      list.add(getNumberFromCode(code.substring(code.length() - code.indexOf("-"))));
    });
    return Collections.max(list);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMixDesign(String code) {
    mixDesignRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public MixDesign getMixDesignByCode(String code) {
    return mixDesignRepository.findById(code).get();
  }

  @Transactional(readOnly = true)
  public boolean isCodeExist(String code) {
    return mixDesignRepository.existsByCode(code);
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getMixDesignByPlantCode(String plantCode) {
    return mixDesignRepository.findByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getMixDesignByStatus(Status status) {
    return mixDesignRepository.findByStatus(status);
  }

  @Transactional(readOnly = true)
  public boolean isMixDesignStatusExist(Status status) {
    return mixDesignRepository.existsByStatus(status);
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getAllMixDesignByPlant(UserPrincipal currentUser) {
    return mixDesignRepository.findByPlantCodeIn(currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_MIX_DESIGN));
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getAllMixDesignByDecending() {
    return mixDesignRepository.findAll(Sort.by(Sort.Direction.DESC, "code"));
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getAllPlantCodeOrderByUpdatedAtDesc(String plantCode) {
    return mixDesignRepository.findByPlantCodeOrderByUpdatedAtDesc(plantCode);
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getMixDesignsByRawMaterialId(Long rawMaterialId) {
    return mixDesignRepository.findByRawMaterialId(rawMaterialId);
  }

  @Transactional(readOnly = true)
  public boolean isRawMaterialExists(Long rawMaterialId) {
    return mixDesignRepository.existsByRawMaterialId(rawMaterialId);
  }

  public Page<MixDesign> searchMixDesign(Predicate predicate, int size, int page) {
    return mixDesignRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code")));
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getAllMixDesign(Pageable pageable) {
    return mixDesignRepository.findAllByOrderByUpdatedAtDesc(pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getMixDesignByPlantCode(String plantCode, Pageable pageable) {
    return mixDesignRepository.findAllByPlantCodeOrderByUpdatedAtDesc(plantCode, pageable).toList();
  }

  @Transactional(readOnly = true)
  public Long getCountMixDesign() {
    return mixDesignRepository.count();
  }

  @Transactional(readOnly = true)
  public Long getCountMixDesignByPlantCode(String plantCode) {
    return mixDesignRepository.countByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<MixDesignResponseDto> searchMixDesign(BooleanBuilder booleanBuilder,
      String materialName, String subCategoryName, String plantName, String plantCode,
      String status, String date, Pageable pageable, Pagination pagination) {

    if (materialName != null && !materialName.isEmpty()) {
      booleanBuilder.and(QMixDesign.mixDesign.rawMaterial.name.startsWithIgnoreCase(materialName));
    }
    if (subCategoryName != null && !subCategoryName.isEmpty()) {
      booleanBuilder.and(QMixDesign.mixDesign.rawMaterial.materialSubCategory.name
          .startsWithIgnoreCase(subCategoryName));
    }
    if (plantName != null && !plantName.isEmpty()) {
      booleanBuilder.and(QMixDesign.mixDesign.plant.name.startsWithIgnoreCase(plantName));
    }

    if (plantCode != null && !plantCode.isEmpty()
        && !(plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      booleanBuilder.and(QMixDesign.mixDesign.plant.code.startsWithIgnoreCase(plantCode));
    }
    if (status != null && !status.isEmpty()) {
      booleanBuilder.and(QMixDesign.mixDesign.status.stringValue().startsWithIgnoreCase(status));
    }
    if (date != null && !date.isEmpty()) {
      booleanBuilder.and(QMixDesign.mixDesign.date.stringValue().startsWithIgnoreCase(date));
    }
    pagination.setTotalRecords(
        ((Collection<MixDesign>) mixDesignRepository.findAll(booleanBuilder)).stream().count());
    return mapper.map(mixDesignRepository.findAll(booleanBuilder, pageable).toList(),
        MixDesignResponseDto.class);
  }

  public List<MixDesign> getCodeByPlantCode(String plantCode, String code) {
    if (code.isEmpty()) {
      return null;
    }
    return mixDesignRepository.findByPlantCodeAndCodeStartsWith(plantCode, code);
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getCode(String code) {
    if (code.isEmpty()) {
      return null;
    }
    return mixDesignRepository.findByCodeContainsIgnoreCase(code);
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getCodeAndRawMaterialId(Long rawMaterialId, Status status, String code) {
    if (code.isEmpty()) {
      return null;
    }
    return mixDesignRepository.findByRawMaterialIdAndStatusAndCodeContainsIgnoreCase(rawMaterialId,
        status, code);
  }
  
  @Override
  public void updateMixDesignWithConfirmation(String confirmationToken) {
	  MixDesignConfirmationToken token =
    		mixDesignConfirmationTokenRepository.findByConfirmationToken(confirmationToken);
    if (token != null) {
      MixDesign  mixDesign = mixDesignRepository.findByCode(token.getMixDesign().getCode());
      mixDesign.setApproved(true);
      mixDesignRepository.save(mixDesign);
    }
  }
}
