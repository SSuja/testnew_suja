package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.ProcessSample;

public interface ProcessSampleService {

  public List<ProcessSample> getAllProcessSamples();

  public void saveProcessSample(ProcessSample processSample);

  public void deleteProcessSample(String code);

  public ProcessSample getProcessSampleByCode(String code);

  public boolean isProcessSampleExist(String code);

  public Page<ProcessSample> searchProcessSample(Predicate predicate, int page, int size);

  public List<ProcessSample> getByPlantCode(String plantCode);
}
