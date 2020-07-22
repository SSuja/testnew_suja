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
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.ProcessSample;
import com.tokyo.supermix.data.repositories.ProcessSampleRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
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

  @Transactional(readOnly = true)
  public List<ProcessSample> getAllProcessSamples() {
    return processSampleRepository.findAll();
  }
  
  @Transactional
  public void saveProcessSample(ProcessSample processSample) {
    IncomingSample incomingSample =
        incomingSampleService.getIncomingSampleById(processSample.getIncomingSample().getCode());
    processSample.setRawMaterial(incomingSample.getRawMaterial());
    ProcessSample processSampleObj = processSampleRepository.save(processSample);
    if (processSampleObj != null) {
      emailNotification.sendProcessSampleCreationEmail(processSampleObj);
    }
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
  public List<ProcessSample> getAllProcessSamplesByCurrentUser(UserPrincipal currentUser) {
    return processSampleRepository.findByIncomingSamplePlantCodeIn(currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_PROCESS_SAMPLE));

  }
}
