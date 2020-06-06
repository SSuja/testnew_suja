package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.report.FinishProductSampleDto;
import com.tokyo.supermix.data.dto.report.FinishProductSampleResultReportDto;
import com.tokyo.supermix.data.dto.report.MixDesignProportionDto;
import com.tokyo.supermix.data.dto.report.SlumpTestResult;
import com.tokyo.supermix.data.dto.report.StrengthResultDto;
import com.tokyo.supermix.data.entities.ConcreteTestResult;
import com.tokyo.supermix.data.entities.ConcreteTestStatus;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.enums.ConcreteStatus;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.ConcreteTestResultRepository;
import com.tokyo.supermix.data.repositories.ConcreteTestStatusRepository;
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
  @Autowired
  private ConcreteTestStatusRepository concreteTestStatusRepository;

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
    finishProductSampleResultReportDto.setStatus(Status.PASS);
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

  public List<FinishProductSampleDto> getAllFinishProductSamples() {
    List<FinishProductSampleDto> FinishProductAllResultsDtoList =
        new ArrayList<FinishProductSampleDto>();
    List<ConcreteTestStatus> concreteTestStatusList = concreteTestStatusRepository
        .findByConcreteStatusAndConcreteTestTypeType(ConcreteStatus.COMPLETED, Constants.STRENGTH);
    for (ConcreteTestStatus concreteTestStatus : concreteTestStatusList) {
      FinishProductSampleDto finishProductSampleDto = new FinishProductSampleDto();
      finishProductSampleDto.setStatus(Status.PASS);
      MixDesign mixDesign = mixDesignRepository
          .findById(concreteTestStatus.getFinishProductSample().getMixDesign().getCode()).get();
      FinishProductSampleIssue finishProductSampleIssue = finishProductSampleIssueRepository
          .findByFinishProductSampleId(concreteTestStatus.getFinishProductSample().getId());
      finishProductSampleDto.setProjectName(finishProductSampleIssue.getProject().getName());
      finishProductSampleDto.setPourName(finishProductSampleIssue.getPour().getName());
      finishProductSampleDto.setTargetGrade(mixDesign.getTargetGrade());
      finishProductSampleDto.setTargetSlump(mixDesign.getTargetSlump());
      finishProductSampleDto
          .setFinishProductCode(concreteTestStatus.getFinishProductSample().getFinishProductCode());
      finishProductSampleDto
          .setStrengthResults(getStrengths(concreteTestStatus.getFinishProductSample().getId()));
      finishProductSampleDto.setMixDesignProportions(
          getAllMixDesignproportion(concreteTestStatus.getFinishProductSample().getId()));
      finishProductSampleDto.setSlumpTestResults(
          getAllSlumpTestResults(concreteTestStatus.getFinishProductSample().getId()));
      FinishProductAllResultsDtoList.add(finishProductSampleDto);
    }
    return FinishProductAllResultsDtoList;
  }

  public List<MixDesignProportionDto> getAllMixDesignproportion(Long finihProductSampleId) {
    List<MixDesignProportionDto> mixDesignProportionDtoList =
        new ArrayList<MixDesignProportionDto>();
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findById(finihProductSampleId).get();
    List<MixDesignProportion> mixDesignProportionList = mixDesignProportionRepository
        .findByMixDesignCode(finishProductSample.getMixDesign().getCode());
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

  public List<StrengthResultDto> getStrengths(Long finihProductSampleId) {
    List<StrengthResultDto> strengthResultDtoList = new ArrayList<StrengthResultDto>();
    List<ConcreteTestResult> concreteTestResultList =
        concreteTestResultRepository.findByFinishProductSampleId(finihProductSampleId);
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

  public List<SlumpTestResult> getAllSlumpTestResults(Long finishProductSampleId) {
    List<SlumpTestResult> slumpTestResultList = new ArrayList<SlumpTestResult>();
    List<ConcreteTestResult> concreteTestResultList =
        concreteTestResultRepository.findByFinishProductSampleId(finishProductSampleId);
    for (ConcreteTestResult concreteTestResult : concreteTestResultList) {
      SlumpTestResult slumpTestResult = new SlumpTestResult();
      if (concreteTestResult.getConcreteTest().getName().equalsIgnoreCase(Constants.SLUMP_TEST)) {
        slumpTestResult.setSlumpValue(concreteTestResult.getResult());
        slumpTestResult.setTemperature(concreteTestResult.getTemperature());
        slumpTestResult.setWaterContent(concreteTestResult.getWaterContent());
        slumpTestResultList.add(slumpTestResult);
      }
      if (concreteTestResult.getConcreteTest().getName()
          .equalsIgnoreCase(Constants.WATER_CEMENT_RATIO)) {
        slumpTestResult.setWaterCementRatio(concreteTestResult.getResult());
        slumpTestResult.setTemperature(concreteTestResult.getTemperature());
        slumpTestResult.setWaterContent(concreteTestResult.getWaterContent());
        slumpTestResultList.add(slumpTestResult);
      }
      if (concreteTestResult.getConcreteTest().getName()
          .equalsIgnoreCase(Constants.WATER_BINDER_RATIO)) {
        slumpTestResult.setWaterBinderRatio(concreteTestResult.getResult());
        slumpTestResult.setTemperature(concreteTestResult.getTemperature());
        slumpTestResult.setWaterContent(concreteTestResult.getWaterContent());
        slumpTestResultList.add(slumpTestResult);
      }
      if (concreteTestResult.getConcreteTest().getName()
          .equalsIgnoreCase(Constants.SLUMP_GRADE_RATIO)) {
        slumpTestResult.setSlumpGradeRatio(concreteTestResult.getResult());
        slumpTestResult.setTemperature(concreteTestResult.getTemperature());
        slumpTestResult.setWaterContent(concreteTestResult.getWaterContent());
        slumpTestResultList.add(slumpTestResult);
      }
    }
    return slumpTestResultList;
  }
}
