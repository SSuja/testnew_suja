package com.tokyo.supermix.server.services;

public interface IncomingSamplesCountService {
  public Long countByTotalMaterialCategoryIncomingSample(String materialCategoryName);
  public Long calculateMaterialSubCategoryCount(String materialSubCategoryName);
}
