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
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;

@Service
public class MaterialTestServiceImpl implements MaterialTestService {
  @Autowired
  private EmailService emailService;
  @Autowired
  private MailConstants mailConstants;
  @Autowired
  private MaterialTestRepository materialTestRepository;
  @Autowired
  private IncomingSampleRepository incomingSampleRepository;

  @Transactional
  public void saveMaterialTest(MaterialTest materialTest) {
    IncomingSample incomingSample = incomingSampleRepository
        .findIncomingSampleByCode(materialTest.getIncomingSample().getCode());
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
    String message = "";
    List<MaterialTest> listMaterialTest =
        materialTestRepository.findByIncomingSampleCode(incomingSampleCode);
    IncomingSample incomingSample =
        incomingSampleRepository.findIncomingSampleByCode(incomingSampleCode);
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
        message = message + "<li> " + materialTest.getTest().getName() + " : "
            + materialTest.getStatus() + "</li>";
      }
    }
    incomingSample.setStatus(status);
    incomingSampleRepository.save(incomingSample);
    if (status == Status.PASS || status == Status.FAIL) {
      emailService.sendMailWithFormat(mailConstants.getMailUpdateIncomingSampleStatus(),
          Constants.SUBJECT_INCOMING_SAMPLE_RESULT,
          "<p>The Incoming Sample is " + status + " The Sample Code is <b>" + incomingSampleCode
              + "</b>. This Sample arrived on " + incomingSample.getDate()
              + ". The Sample Material is <b>" + incomingSample.getRawMaterial().getName()
              + "</b>.</p><ul>" + message + "</ul>");
    }
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
