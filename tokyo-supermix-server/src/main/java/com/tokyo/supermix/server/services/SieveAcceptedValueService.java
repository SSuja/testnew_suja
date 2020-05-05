package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.SieveAcceptedValue;

public interface SieveAcceptedValueService {
  public void saveSieveAcceptedValue(SieveAcceptedValue sieveAcceptedValue);

  public List<SieveAcceptedValue> getAllSieveAcceptedValues();

  public boolean isSieveAcceptedValueExist(Long id);

  public SieveAcceptedValue getSieveAcceptedValueById(Long id);

  public void deleteSieveAcceptedValue(Long id);

  public boolean isSieveSizeExist(Long sieveSizeId);

  public Page<SieveAcceptedValue> searchSieveAcceptedValue(Predicate predicate, int page, int size);
}
