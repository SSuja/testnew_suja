package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MixDesignParameterElement;
import com.tokyo.supermix.data.repositories.MixDesignParameterElementRepository;

@Service
public class MixDesignParameterElementServiceImpl implements MixDesignParameterElementService {
  @Autowired
  private MixDesignParameterElementRepository mixDesignParameterElementRepository;

  @Transactional
  public MixDesignParameterElement saveMixDesignParameterElement(
      MixDesignParameterElement mixDesignParameterElement) {
    return mixDesignParameterElementRepository.save(mixDesignParameterElement);
  }

  @Transactional(readOnly = true)
  public List<MixDesignParameterElement> getallMixDesignParameterElements() {
    return mixDesignParameterElementRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateEntryExists(Long mixDesignParameterId, Long rawMaterialId) {
    return mixDesignParameterElementRepository
        .existsByMixDesignParameterIdAndRawMaterialId(mixDesignParameterId, rawMaterialId);
  }

  @Transactional(readOnly = true)
  public MixDesignParameterElement getMixDesignParameterElementById(Long id) {
    return mixDesignParameterElementRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isMixDesignParameterElementExist(Long id) {
    return mixDesignParameterElementRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMixDesignParameterElement(Long id) {
    mixDesignParameterElementRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isMixDesignParameterIdExist(Long mixDesignParameterId) {
    return mixDesignParameterElementRepository.existsByMixDesignParameterId(mixDesignParameterId);
  }

  public boolean isUpdateMixDesignParameter(Long id,Long mixDesignParameterId) {
    if ((!getMixDesignParameterElementById(id).getMixDesignParameter().equals(mixDesignParameterId))
        && isMixDesignParameterIdExist(mixDesignParameterId)) {
      return true;
    }
    return false;
  }
}
