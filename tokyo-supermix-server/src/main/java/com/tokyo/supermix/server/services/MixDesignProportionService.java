package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.tokyo.supermix.data.entities.MixDesignProportion;

public interface MixDesignProportionService {
  public List<MixDesignProportion> getAllMixDesignProportions();

  public String saveMixDesignProportion(List<MixDesignProportion> mixDesignProportion);

  public void deleteById(Long id);

  public MixDesignProportion getMixDesignProportionById(Long id);

  public boolean isMixDesignProportionExist(Long id);

  public List<MixDesignProportion> findByMixDesignCode(String mixDesignCode);

  public boolean existsByMixDesignCode(String mixDesignCode);

  public Page<MixDesignProportion> searchMixDesignProportion(String rawMaterialName1,
      String rawMaterialName2, String rawMaterialName3, String rawMaterialName4,
      String rawMaterialName5, int page, int size, String mixDesignCode);

  public void updateMixDesignProportion(MixDesignProportion mixDesignProportion);
}
