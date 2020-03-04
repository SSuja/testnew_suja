package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.ProcessSampleLoad;

public interface ProcessSampleLoadService {
  public void saveProcessSampleLoad(ProcessSampleLoad processSampleLoad);
  public List<ProcessSampleLoad> getAllProcessSampleLoads();
  public ProcessSampleLoad getProcessSampleLoadById(Long id);
  public void deleteProcessSampleLoad(Long id);
  boolean isProcessSampleLoadExist(Long id);
}
