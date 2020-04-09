package com.tokyo.supermix.server.services;

public interface IncomingSamplesCountService {
  public Long countByTotalMaterialCategoryIncomingSample(String materialCategoryName);

  public Long calculateMaterialSubCategoryCount(String materialSubCategoryName);

  public Long getMaterialSubCategoryStatusCount(String materialSubCategoryName, int status);

  public Long getMaterialCategoryStatusCount(String materialCategoryName, int status);
}
