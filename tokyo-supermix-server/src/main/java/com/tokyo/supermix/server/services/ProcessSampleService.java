package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.ProcessSample;
import com.tokyo.supermix.security.UserPrincipal;

public interface ProcessSampleService {

  public List<ProcessSample> getAllProcessSamples();

  public List<ProcessSample> getAllProcessSamplesByCurrentUser(UserPrincipal currentUser,Pageable pageable);

  public void saveProcessSample(ProcessSample processSample);

  public void deleteProcessSample(String code);

  public ProcessSample getProcessSampleByCode(String code);

  public boolean isProcessSampleExist(String code);

  public Page<ProcessSample> searchProcessSample(Predicate predicate, int page, int size);

  public List<ProcessSample> getProcessSampleByPlantCode(String plantCode);
 
  public List<ProcessSample> getAllProcessSample(Pageable pageable);

  public List<ProcessSample> getProcessSampleByPlantCode(String plantCode, Pageable pageable);

  public Long getCountProcessSample();

  public Long getCountProcessSampleByPlantCode(String plantCode);
}
