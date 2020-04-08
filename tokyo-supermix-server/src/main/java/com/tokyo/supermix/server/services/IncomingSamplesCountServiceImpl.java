package com.tokyo.supermix.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;

@Service
public class IncomingSamplesCountServiceImpl implements IncomingSamplesCountService {
  @Autowired
  IncomingSampleRepository incomingSampleRepository;

  @Transactional(readOnly = true)
  public Long calculateMaterialSubCategoryCount(String materialSubcategoryname) {
    return incomingSampleRepository.calculateMaterialSubCategoryCount(materialSubcategoryname);
  }

}
