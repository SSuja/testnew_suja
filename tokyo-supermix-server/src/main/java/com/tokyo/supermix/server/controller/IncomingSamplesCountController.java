package com.tokyo.supermix.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.server.services.IncomingSamplesCountService;

@RestController
@CrossOrigin
public class IncomingSamplesCountController {
  @Autowired
  private IncomingSamplesCountService incomingSamplesCountService;

  @GetMapping(value = EndpointURI.MATERIAL_SUB_CATEGORY_TOTAL_COUNT)
  public Long getIncomingSampleBySubCategory(@PathVariable String materialsubcategoryname) {
    return incomingSamplesCountService.calculateMaterialSubCategoryCount(materialsubcategoryname);
  }

  @GetMapping(value = EndpointURI.MATERIAL_CATEGORY_TOTAL_COUNT)
  public Long getDateAndRawMaterialName(@PathVariable String materialCategoryName) {
    return incomingSamplesCountService
        .countByTotalMaterialCategoryIncomingSample(materialCategoryName);
  }
}
