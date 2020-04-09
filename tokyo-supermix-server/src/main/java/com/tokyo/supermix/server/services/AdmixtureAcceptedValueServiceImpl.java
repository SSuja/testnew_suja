package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.AdmixtureAcceptedValue;
import com.tokyo.supermix.data.repositories.AdmixtureAcceptedValueRepository;

@Service
public class AdmixtureAcceptedValueServiceImpl implements AdmixtureAcceptedValueService {
  @Autowired
  private AdmixtureAcceptedValueRepository admixtureAcceptedValueRepository;

  @Transactional
  public void saveAdmixtureAcceptedValue(AdmixtureAcceptedValue admixtureAcceptedValue) {
    admixtureAcceptedValueRepository.save(admixtureAcceptedValue);
  }

  @Transactional(readOnly = true)
  public List<AdmixtureAcceptedValue> getAllAdmixtureAcceptedValues() {
    return admixtureAcceptedValueRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isAdmixtureAcceptedValueExist(Long id) {
    return admixtureAcceptedValueRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public AdmixtureAcceptedValue getAdmixtureAcceptedValueById(Long id) {
    return admixtureAcceptedValueRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteAdmixtureAcceptedValue(Long id) {
    admixtureAcceptedValueRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isAdmixtureAcceptedValueExistsByTestConfigureId(Long id) {
    return admixtureAcceptedValueRepository.existsByTestConfigureId(id);
  }

  public boolean isUpdatedTestConfigureIdExist(Long id, Long testConfigureId) {
    if ((!getAdmixtureAcceptedValueById(id).getTestConfigure().getId().equals(testConfigureId))
        && (admixtureAcceptedValueRepository.existsByTestConfigureId(testConfigureId))) {
      return true;
    }
    return false;
  }

  @Transactional
  public AdmixtureAcceptedValue getAdmixtureAcceptedValueByTestConfigureId(Long testConfigureId) {
    return admixtureAcceptedValueRepository.findByTestConfigureId(testConfigureId);
  }
}
