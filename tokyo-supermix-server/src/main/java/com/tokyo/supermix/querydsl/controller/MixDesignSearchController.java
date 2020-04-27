package com.tokyo.supermix.querydsl.controller;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.server.services.MixDesignService;


@RestController
public class MixDesignSearchController {
  @Autowired
  private MixDesignRepository mixDesignRepository;

  @Autowired
  private MixDesignService mixDesignService;

  @GetMapping("/search-mixdesign")
  public Page<MixDesign> getMixDesignInbetweenOperation(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "500") int size,
      @RequestParam(name = "code", required = false) String code,
      @RequestParam(name = "targetGrade", required = false) Double targetGrade,
      @RequestParam(name = "date", required = false) Date date,
      @RequestParam(name = "targetSlumpEqual", required = false) Double targetSlumpEqual,
      @RequestParam(name = "targetSlumpMin", required = false) Double targetSlumpMin,
      @RequestParam(name = "targetSlumpMax", required = false) Double targetSlumpMax,
      @RequestParam(name = "waterCementRatio", required = false) Double waterCementRatio,
      @RequestParam(name = "waterBinderRatio", required = false) Double waterBinderRatio,
      @RequestParam(name = "plantCode", required = false) String plantCode) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    mixDesignService.searchMixDesign(targetSlumpMin, targetSlumpMax, targetSlumpEqual, plantCode,
        booleanBuilder);
    return mixDesignRepository.findAll(booleanBuilder.getValue(),
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code")));
  }
}
