package com.tokyo.supermix.server.services;
import java.util.List;
import com.tokyo.supermix.data.entities.MixDesign;

public interface MixDesignService {
    public List<MixDesign> getAllMixDesigns();
      public MixDesign  saveMixDesign(MixDesign mixDesign);
      public void deleteMixDesign(String  code);
      public MixDesign getMixDesignByCode(String code);
      public boolean isCodeExist(String code);
 }