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

@Service
public class ProcessSampleServiceImpl implements ProcessSampleService {
  @Autowired
  private ProcessSampleRepository processSampleRepository;
  @Autowired
  private IncomingSampleService incomingSampleService;

  @Transactional(readOnly = true)
  public List<ProcessSample> getAllProcessSamples() {
    return processSampleRepository.findAll();
  }

  @Transactional
  public void saveProcessSample(ProcessSample processSample) {
    IncomingSample incomingSample =
        incomingSampleService.getIncomingSampleById(processSample.getIncomingSample().getCode());
    processSample.setRawMaterial(incomingSample.getRawMaterial());
    processSampleRepository.save(processSample);
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
}
