package com.tokyo.supermix.server.services;

public interface IncomingSamplesCountService {
  public Long calculateMaterialSubCategoryCount(String materialSubcategoryname);
  public Long countByTotalMaterialCategoryIncomingSample(String materialCategoryName);
}
