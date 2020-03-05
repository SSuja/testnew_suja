package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.ProcessSampleLoad;
import com.tokyo.supermix.data.repositories.ProcessSampleLoadRepository;

@Service
public class ProcessSampleLoadServiceImpl implements ProcessSampleLoadService {
  @Autowired
  private ProcessSampleLoadRepository processSampleLoadRepository;

  @Transactional
  public void saveProcessSampleLoad(ProcessSampleLoad processSampleLoad) {
    processSampleLoadRepository.save(processSampleLoad);
  }

  @Transactional(readOnly = true)
  public List<ProcessSampleLoad> getAllProcessSampleLoads() {
    return processSampleLoadRepository.findAll();
  }

  @Transactional(readOnly = true)
  public ProcessSampleLoad getProcessSampleLoadById(Long id) {
    return processSampleLoadRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteProcessSampleLoad(Long id) {
    processSampleLoadRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isProcessSampleLoadExist(Long id) {
    return processSampleLoadRepository.existsById(id);
  }

}
