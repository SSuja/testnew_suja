package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;

@Service
public class MaterialTestServiceImpl implements MaterialTestService {

  @Autowired
  private MaterialTestRepository materialTestRepository;
  @Autowired
  private IncomingSampleRepository incomingSampleRepository;
  @Transactional
  public void saveMaterialTest(MaterialTest materialTest) {
    IncomingSample incomingSample=incomingSampleRepository.findIncomingSampleByCode(materialTest.getIncomingSample().getCode());
    incomingSample.setStatus(Status.PROCESS);
    incomingSampleRepository.save(incomingSample);
    materialTest.setStatus(Status.NEW);
    materialTestRepository.save(materialTest);
  }

  @Transactional(readOnly = true)
  public MaterialTest getMaterialTestByCode(String code) {
    return materialTestRepository.findByCode(code);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getAllMaterialTests() {
    return materialTestRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMaterialTest(String code) {
    materialTestRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestExists(String code) {
    return materialTestRepository.existsByCode(code);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getMaterialTestByStatus(String status) {
    return materialTestRepository.findByStatus(status);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getMaterialTestByTest(Long testId) {
    return materialTestRepository.findByTest(testId);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestStatusExists(String status) {
    return materialTestRepository.existsByStatus(status);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestByTestExists(Long testId) {
    return materialTestRepository.existsByTest(testId);
  }

  public void updateIncomingSampleStatusByIncomingSampleCode(String incomingSampleCode) {
    List<MaterialTest> listMaterialTest =
        materialTestRepository.findByIncomingSampleCode(incomingSampleCode);
    Status status = Status.NEW;
    for (MaterialTest materialTest : listMaterialTest) {
      if (materialTest.getStatus() == Status.FAIL) {
        status = Status.FAIL;
        break;
      } else if (materialTest.getStatus() == Status.NEW) {
        status = Status.PROCESS;
        break;
      } else {
        status = Status.PASS;
      }
    }
    IncomingSample incomingSample =
        incomingSampleRepository.findIncomingSampleByCode(incomingSampleCode);
    incomingSample.setStatus(status);
    incomingSampleRepository.save(incomingSample);
  }
  public void updateIncomingSampleStatusBySeheduler() {
    List<IncomingSample> incomingSamplelist = incomingSampleRepository.findByStatus(Status.PROCESS);
    for (IncomingSample incomingSample : incomingSamplelist) {
      {
        updateIncomingSampleStatusByIncomingSampleCode(incomingSample.getCode());
      }
    }
  }
}
