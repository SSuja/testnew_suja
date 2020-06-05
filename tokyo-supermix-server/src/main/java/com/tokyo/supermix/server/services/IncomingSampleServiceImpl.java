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
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;

@Service
public class IncomingSampleServiceImpl implements IncomingSampleService {
  @Autowired
  private IncomingSampleRepository incomingSampleRepository;

  @Transactional
  public void createIncomingSample(IncomingSample incomingSample) {
    if (incomingSample.getCode() == null) {
      String codePrefix = incomingSample.getPlant().getCode() + "-INC-";
      List<IncomingSample> incomingSampleList =
          incomingSampleRepository.findByCodeContaining(codePrefix);
      if (incomingSampleList.size() == 0) {
        incomingSample.setCode(codePrefix + String.format("%04d", 1));
      } else {
        incomingSample
            .setCode(codePrefix + String.format("%04d", maxNumberFromCode(incomingSampleList) + 1));
      }
    }
    incomingSample.setStatus(Status.NEW);
    incomingSampleRepository.save(incomingSample);
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<IncomingSample> incomingSampleList) {
    List<Integer> list = new ArrayList<Integer>();
    incomingSampleList.forEach(obj -> {
      list.add(getNumberFromCode(obj.getCode()));
    });
    return Collections.max(list);
  }

  @Transactional
  public void updateIncomingSample(IncomingSample incomingSample) {
    incomingSampleRepository.save(incomingSample);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteIncomingSample(String code) {
    incomingSampleRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public IncomingSample getIncomingSampleById(String code) {
    return incomingSampleRepository.findById(code).get();
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> getAllIncomingSamples() {
    return incomingSampleRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isIncomingSampleExist(String code) {
    return incomingSampleRepository.existsByCode(code);
  }

  @Transactional
  public void updateStatusForIncomingSample(String code, Status status) {
    IncomingSample incomingSample = incomingSampleRepository.findById(code).get();
    incomingSample.setStatus(status);
    incomingSampleRepository.save(incomingSample);
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> getIncomingSampleByStatus(Status status) {
    return incomingSampleRepository.findIncomingSampleByStatus(status);
  }

  @Transactional(readOnly = true)
  public boolean isIncomingSampleStatusExist(Status status) {
    return incomingSampleRepository.existsByStatus(status);
  }

  @Transactional(readOnly = true)
  public Page<IncomingSample> searchIncomingSample(Predicate predicate, int page, int size) {
    return incomingSampleRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code")));
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> getIncomingSampleByPlantCode(String plantCode) {
    return incomingSampleRepository.findByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> getIncomingSampleByMaterialCategory(String materialCategoryName) {
    return incomingSampleRepository
        .findByRawMaterialMaterialSubCategoryMaterialCategoryName(materialCategoryName);
  }

}
