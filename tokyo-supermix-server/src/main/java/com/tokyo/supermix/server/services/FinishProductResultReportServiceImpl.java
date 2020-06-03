package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.report.FinishProductSampleResultReportDto;
import com.tokyo.supermix.data.dto.report.MixDesignProportionDto;
import com.tokyo.supermix.data.dto.report.StrengthResultDto;
import com.tokyo.supermix.data.entities.ConcreteTestResult;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.repositories.ConcreteTestResultRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleIssueRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.MixDesignProportionRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class FinishProductResultReportServiceImpl implements FinishProductResultReportService {

  @Autowired
  private FinishProductSampleRepository finishProductSampleRepository;
  @Autowired
  private FinishProductSampleIssueRepository finishProductSampleIssueRepository;
  @Autowired
  private ConcreteTestResultRepository concreteTestResultRepository;
  @Autowired
  private MixDesignRepository mixDesignRepository;
  @Autowired
  private MixDesignProportionRepository mixDesignProportionRepository;

  @Transactional(readOnly = true)
  public FinishProductSampleResultReportDto getFinishProductSampleResultReportByFinishProductSampleId(
      Long finishProductSampleId) {
    FinishProductSampleResultReportDto finishProductSampleResultReportDto =
        new FinishProductSampleResultReportDto();
    FinishProductSampleIssue finishProductSampleIssue =
        finishProductSampleIssueRepository.findByFinishProductSampleId(finishProductSampleId);
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findById(finishProductSampleId).get();
    List<ConcreteTestResult> concreteTestResultList =
        concreteTestResultRepository.findByFinishProductSampleId(finishProductSampleId);
    finishProductSampleResultReportDto
        .setProjectName(finishProductSampleIssue.getProject().getName());
    MixDesign mixDesign =
        mixDesignRepository.findById(finishProductSample.getMixDesign().getCode()).get();
    finishProductSampleResultReportDto.setTargetGrade(mixDesign.getTargetGrade());
    finishProductSampleResultReportDto.setTargetSlump(mixDesign.getTargetSlump());
    finishProductSampleResultReportDto
        .setMixDesignProportions(getMixDesignProprtion(mixDesign.getCode()));
    for (ConcreteTestResult concreteTestResult : concreteTestResultList) {

      if (concreteTestResult.getConcreteTest().getName().equalsIgnoreCase(Constants.SLUMP_TEST)) {
        finishProductSampleResultReportDto.setSlumpValue(concreteTestResult.getResult());
        finishProductSampleResultReportDto.setTemperature(concreteTestResult.getTemperature());
        finishProductSampleResultReportDto.setWaterContent(concreteTestResult.getWaterContent());
      }
      if (concreteTestResult.getConcreteTest().getName()
          .equalsIgnoreCase(Constants.WATER_CEMENT_RATIO)) {
        finishProductSampleResultReportDto.setWaterCementRatio(concreteTestResult.getResult());
      }
      if (concreteTestResult.getConcreteTest().getName()
          .equalsIgnoreCase(Constants.WATER_BINDER_RATIO)) {
        finishProductSampleResultReportDto.setWaterBinderRatio(concreteTestResult.getResult());
      }
      if (concreteTestResult.getConcreteTest().getName()
          .equalsIgnoreCase(Constants.SLUMP_GRADE_RATIO)) {
        finishProductSampleResultReportDto.setSlumpGradeRatio(concreteTestResult.getResult());
      }
    }
    finishProductSampleResultReportDto
        .setStrengthResults(getStrengthResultReportByFinishProductSampleId(finishProductSampleId));
    return finishProductSampleResultReportDto;
  }

  public List<StrengthResultDto> getStrengthResultReportByFinishProductSampleId(
      Long finishProductSampleId) {
    List<StrengthResultDto> strengthResultDtoList = new ArrayList<StrengthResultDto>();
    List<ConcreteTestResult> concreteTestResultList =
        concreteTestResultRepository.findByFinishProductSampleId(finishProductSampleId);
    for (ConcreteTestResult concreteTestResult : concreteTestResultList) {
      StrengthResultDto strengthResultDto = new StrengthResultDto();
      if (concreteTestResult.getConcreteTest().getName()
          .equalsIgnoreCase(Constants.STRENGTH_TEST)) {
        strengthResultDto.setAge(concreteTestResult.getAge());
        strengthResultDto.setStrengthAverage(concreteTestResult.getResult());
        strengthResultDtoList.add(strengthResultDto);
      }
      if (concreteTestResult.getConcreteTest().getName()
          .equalsIgnoreCase(Constants.STRENGTH_GRADE_RATIO)) {
        strengthResultDto.setAge(concreteTestResult.getAge());
        strengthResultDto.setStrengthGradeRatio(concreteTestResult.getResult());
        strengthResultDtoList.add(strengthResultDto);
      }
    }
    return strengthResultDtoList;
  }

  public List<MixDesignProportionDto> getMixDesignProprtion(String mixDesignCode) {
    List<MixDesignProportionDto> mixDesignProportionDtoList =
        new ArrayList<MixDesignProportionDto>();
    List<MixDesignProportion> mixDesignProportionList =
        mixDesignProportionRepository.findByMixDesignCode(mixDesignCode);
    for (MixDesignProportion mixDesignProportion : mixDesignProportionList) {
      MixDesignProportionDto mixDesignProportionDto = new MixDesignProportionDto();
      if (mixDesignProportion.getRawMaterial().getName()
          .contentEquals(Constants.RAW_MATERIAL_CEMENT)) {
        mixDesignProportionDto.setMaterialName(Constants.RAW_MATERIAL_CEMENT);
        mixDesignProportionDto.setQuantity(mixDesignProportion.getQuantity());
        mixDesignProportionDtoList.add(mixDesignProportionDto);
      }
      if (mixDesignProportion.getRawMaterial().getName()
          .contentEquals(Constants.RAW_MATERIAL_FLYASH)) {
        mixDesignProportionDto.setMaterialName(Constants.RAW_MATERIAL_FLYASH);
        mixDesignProportionDto.setQuantity(mixDesignProportion.getQuantity());
        mixDesignProportionDtoList.add(mixDesignProportionDto);
      }
    }
    return mixDesignProportionDtoList;
  }


}
