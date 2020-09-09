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
import com.tokyo.supermix.data.dto.ProcessSampleResponseDto;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.ProcessSample;
import com.tokyo.supermix.data.entities.QProcessSample;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.ProcessSampleRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class ProcessSampleServiceImpl implements ProcessSampleService {
  @Autowired
  private ProcessSampleRepository processSampleRepository;
  @Autowired
  private IncomingSampleService incomingSampleService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private EmailNotification emailNotification;
  @Autowired
  private IncomingSampleRepository incomingSampleRepository;
  @Autowired
  private Mapper mapper;

  @Transactional(readOnly = true)
  public List<ProcessSample> getAllProcessSamples() {
    return processSampleRepository.findAll();
  }

  @Transactional
  public void saveProcessSample(ProcessSample processSample) {
    if (processSample.getCode() == null) {
      String plantPrefix = incomingSampleRepository
          .getOne(processSample.getIncomingSample().getCode()).getPlant().getCode();
      String codePrefix = plantPrefix + "-PRC-";
      List<ProcessSample> processSampleList =
          processSampleRepository.findByCodeContaining(codePrefix);
      if (processSampleList.size() == 0) {
        processSample.setCode(codePrefix + String.format("%04d", 1));
      } else {
        processSample
            .setCode(codePrefix + String.format("%04d", maxNumberFromCode(processSampleList) + 1));
      }
    }

    IncomingSample incomingSample =
        incomingSampleService.getIncomingSampleById(processSample.getIncomingSample().getCode());
    processSample.setRawMaterial(incomingSample.getRawMaterial());
    ProcessSample processSampleObj = processSampleRepository.save(processSample);
    if (processSampleObj != null) {
      emailNotification.sendProcessSampleCreationEmail(processSampleObj);
    }
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<ProcessSample> processSampleList) {
    List<Integer> list = new ArrayList<Integer>();
    processSampleList.forEach(obj -> {
      String code = obj.getCode();
      list.add(getNumberFromCode(code.substring(code.length() - code.indexOf("-"))));
    });
    return Collections.max(list);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteProcessSample(String code) {
    processSampleRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public boolean isProcessSampleExist(String code) {
    return processSampleRepository.existsById(code);
  }

  @Transactional(readOnly = true)
  public ProcessSample getProcessSampleByCode(String code) {
    return processSampleRepository.findProcessSampleByCode(code);
  }

  @Transactional(readOnly = true)
  public Page<ProcessSample> searchProcessSample(Predicate predicate, int page, int size) {
    return processSampleRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.Direction.ASC, "code"));
  }

  @Transactional(readOnly = true)
  public List<ProcessSample> getProcessSampleByPlantCode(String plantCode) {
    return processSampleRepository.findByIncomingSamplePlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<ProcessSample> getAllProcessSamplesByCurrentUser(UserPrincipal currentUser,
      Pageable pageable) {
    return processSampleRepository.findByIncomingSamplePlantCodeIn(currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_PROCESS_SAMPLE),
        pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<ProcessSample> getAllProcessSample(Pageable pageable) {
    return processSampleRepository.findAll(pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<ProcessSample> getProcessSampleByPlantCode(String plantCode, Pageable pageable) {
    return processSampleRepository.findAllByIncomingSamplePlantCode(plantCode, pageable).toList();
  }

  @Transactional(readOnly = true)
  public Long getCountProcessSample() {
    return processSampleRepository.count();
  }

  @Transactional(readOnly = true)
  public Long getCountProcessSampleByPlantCode(String plantCode) {
    return processSampleRepository.countByIncomingSamplePlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<ProcessSampleResponseDto> searchProcessSample(BooleanBuilder booleanBuilder,
      String incomingSampleCode, String rawMaterialName, String plantCode, Pageable pageable,
      Pagination pagination) {

    if (incomingSampleCode != null && !incomingSampleCode.isEmpty()) {
      booleanBuilder.and(QProcessSample.processSample.incomingSample.code
          .startsWithIgnoreCase(incomingSampleCode));
    }
    if (rawMaterialName != null && !rawMaterialName.isEmpty()) {
      booleanBuilder
          .and(QProcessSample.processSample.rawMaterial.name.startsWithIgnoreCase(rawMaterialName));
    }
    if (plantCode != null && !plantCode.isEmpty()
        && !(plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      booleanBuilder.and(
          QProcessSample.processSample.incomingSample.plant.code.startsWithIgnoreCase(plantCode));
    }
    pagination.setTotalRecords(
        ((Collection<ProcessSample>) processSampleRepository.findAll(booleanBuilder)).stream()
            .count());
    return mapper.map(processSampleRepository.findAll(booleanBuilder, pageable).toList(),
        ProcessSampleResponseDto.class);
  }
}
