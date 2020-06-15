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
import com.tokyo.supermix.data.entities.SieveAcceptedValue;
import com.tokyo.supermix.data.repositories.SieveAcceptedValueRepository;

@Service
public class SieveAcceptedValueServiceImpl implements SieveAcceptedValueService {
  @Autowired
  private SieveAcceptedValueRepository sieveAcceptedValueRepository;

  @Transactional
  public void saveSieveAcceptedValue(SieveAcceptedValue sieveAcceptedValue) {
    sieveAcceptedValueRepository.save(sieveAcceptedValue);
  }

  @Transactional(readOnly = true)
  public List<SieveAcceptedValue> getAllSieveAcceptedValues() {
    return sieveAcceptedValueRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isSieveAcceptedValueExist(Long id) {
    return sieveAcceptedValueRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public SieveAcceptedValue getSieveAcceptedValueById(Long id) {
    return sieveAcceptedValueRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteSieveAcceptedValue(Long id) {
    sieveAcceptedValueRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isSieveSizeExist(Long sieveSizeId) {
    return sieveAcceptedValueRepository.existsBySieveSizeId(sieveSizeId);
  }

  @Transactional(readOnly = true)
  public Page<SieveAcceptedValue> searchSieveAcceptedValue(Predicate predicate, int page,
      int size) {
    return sieveAcceptedValueRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }
  
  @Transactional(readOnly = true)
  public boolean isUpdatedSieveSizeExist(Long id, Long sieveSizeId) {
    if ((!getSieveAcceptedValueById(id).getSieveSize().getId().equals(sieveSizeId))
        && (isSieveSizeExist(sieveSizeId))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public SieveAcceptedValue getSieveAcceptedValueBySieveSize(Long sieveSizeId) {
    return sieveAcceptedValueRepository.findBySieveSizeId(sieveSizeId);
  }
}
