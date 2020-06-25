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
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;

@Service
public class FinishProductSampleServiceImpl implements FinishProductSampleService {
  @Autowired
  FinishProductSampleRepository finishProductSampleRepository;

  @Transactional(readOnly = true)
  public boolean isFinishProductCodeExist(Long code) {
    return finishProductSampleRepository.existsByFinishProductCode(code);
  }

  @Transactional()
  public void saveFinishProductSample(FinishProductSample finishProductSample) {
    finishProductSampleRepository.save(finishProductSample);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getAllFinishProductSamples() {
    return finishProductSampleRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductSampleExist(Long id) {
    return finishProductSampleRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public FinishProductSample getFinishProductSampleById(Long id) {
    return finishProductSampleRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductSample(Long id) {
    finishProductSampleRepository.deleteById(id);
  }

  public boolean isUpdatedFinishProductCodeExist(Long id, Long code) {
    if ((!getFinishProductSampleById(id).getFinishProductCode().equals(code))
        && (isFinishProductCodeExist(code))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isMixDesignCodeExist(String code) {
    return finishProductSampleRepository.existsByMixDesignCode(code);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSampleByMixDesignCode(String mixDesignCode) {
    return finishProductSampleRepository.findByMixDesignCode(mixDesignCode);
  }

  @Transactional(readOnly = true)
  public boolean isConcreteMixerExist(Long id) {
    return finishProductSampleRepository.existsByConcreteMixerId(id);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSampleByConcreteMixerId(Long id) {
    return finishProductSampleRepository.findByConcreteMixerId(id);
  }

  @Transactional(readOnly = true)
  public Page<FinishProductSample> searchFinishProductSample(Predicate predicate, int page,
      int size) {
    return finishProductSampleRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSampleByPlantCode(String plantCode) {
    return finishProductSampleRepository.findByMixDesignPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSampleByStatus(Status status) {
    return finishProductSampleRepository.findByStatus(status);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductSampleStatusExist(Status status) {
    return finishProductSampleRepository.existsByStatus(status);
  }
}
