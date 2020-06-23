package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MixDesignParameterElement;

public interface MixDesignParameterElementService {
  public MixDesignParameterElement saveMixDesignParameterElement(
      MixDesignParameterElement mixDesignParameterElement);

  public List<MixDesignParameterElement> getallMixDesignParameterElements();

  public boolean isDuplicateEntryExists(Long mixDesignParameterId, Long rawMaterialId);

  public MixDesignParameterElement getMixDesignParameterElementById(Long id);

  public boolean isMixDesignParameterElementExist(Long id);

  public void deleteMixDesignParameterElement(Long id);

  public boolean isMixDesignParameterIdExist(Long mixDesignParameterId);

  public boolean isUpdateMixDesignParameter(Long id,Long mixDesignParameterId);
}
