package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.dto.PlantDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleReportDto;
import com.tokyo.supermix.data.dto.report.SieveAcceptedValueDto;
import com.tokyo.supermix.data.dto.report.SieveSizeDto;
import com.tokyo.supermix.data.dto.report.SieveTestDto;
import com.tokyo.supermix.data.dto.report.SieveTestReportDto;
import com.tokyo.supermix.data.dto.report.SieveTestTrialDto;
import com.tokyo.supermix.data.entities.SieveAcceptedValue;
import com.tokyo.supermix.data.entities.SieveSize;
import com.tokyo.supermix.data.entities.SieveTest;
import com.tokyo.supermix.data.entities.SieveTestTrial;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.SieveAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.SieveSizeRepository;
import com.tokyo.supermix.data.repositories.SieveTestRepository;
import com.tokyo.supermix.data.repositories.SieveTestTrialRepository;

@Service
public class SieveTestReportServiceImpl implements SieveTestReportService {
  @Autowired
  private Mapper mapper;
  @Autowired
  private SieveTestRepository sieveTestRepository;
  @Autowired
  private SieveSizeRepository sieveSizeRepository;
  @Autowired
  private SieveTestTrialRepository sieveTestTrialRepository;
  @Autowired
  private SieveAcceptedValueRepository sieveAcceptedValueRepository;

  @Override
  public SieveTestReportDto getSeiveTestReport(String seiveTestCode) {
    SieveTestReportDto sieveTestReportDto = new SieveTestReportDto();
    SieveTest sieveTest = sieveTestRepository.findByCode(seiveTestCode);
    SieveTestDto sieveTestDto = mapper.map(sieveTest, SieveTestDto.class);
   sieveTestReportDto.setSieveTest(sieveTestDto);
    sieveTestReportDto.setIncomingsample(getIncomingSampleDetails(sieveTest));
    sieveTestReportDto.setSieveSizes(getSieveSizeDetails(
        sieveTest.getIncomingSample().getRawMaterial().getMaterialSubCategory().getId()));
    sieveTestReportDto.setSieveTestTrials(getSeiveTestTrialReport(seiveTestCode));
    sieveTestReportDto
        .setPlant(mapper.map(sieveTest.getIncomingSample().getPlant(), PlantDto.class));
    sieveTestReportDto.setSeiveAcceptedValueDto(getSieveAcceptedValueDetails(
        sieveTest.getIncomingSample().getRawMaterial().getMaterialSubCategory().getId()));
    return sieveTestReportDto;
  }

  private IncomingSampleReportDto getIncomingSampleDetails(SieveTest sieveTest) {
    IncomingSampleReportDto incomingSampleReportDto =
        mapper.map(sieveTest.getIncomingSample(), IncomingSampleReportDto.class);
    incomingSampleReportDto.setMaterialSubCategory(
        sieveTest.getIncomingSample().getRawMaterial().getMaterialSubCategory().getName());
    incomingSampleReportDto.setMaterialCategory(sieveTest.getIncomingSample().getRawMaterial()
        .getMaterialSubCategory().getMaterialCategory().getName());
    return incomingSampleReportDto;
  }

  private SieveAcceptedValueDto getSieveAcceptedValueDetails(Long materialSubCategoryId) {
    SieveAcceptedValue sieveAcceptedValue =
        sieveAcceptedValueRepository.findBySieveSizeMaterialSubCategoryId(materialSubCategoryId);
    SieveAcceptedValueDto sieveAcceptedValueDto =
        mapper.map(sieveAcceptedValue, SieveAcceptedValueDto.class);
    sieveAcceptedValueDto.setMax(sieveAcceptedValue.getMax());
    sieveAcceptedValueDto.setMin(sieveAcceptedValue.getMin());
    return sieveAcceptedValueDto;
  }

  private List<SieveSizeDto> getSieveSizeDetails(Long materialSubCategoryId) {
    List<SieveSizeDto> sieveSizeDtoList = new ArrayList<SieveSizeDto>();
    List<SieveSize> sieveSizelist =
        sieveSizeRepository.findByMaterialSubCategoryId(materialSubCategoryId);
    for (SieveSize sieveSize : sieveSizelist) {
      SieveSizeDto sieveSizeDto = new SieveSizeDto();
      sieveSizeDto.setSize(sieveSize.getSize());
      sieveSizeDtoList.add(sieveSizeDto);
    }
    return sieveSizeDtoList;
  }

  public List<SieveTestTrialDto> getSeiveTestTrialReport(String sieveTestCode) {
    List<SieveTestTrialDto> sieveTestTrialDtoList = new ArrayList<SieveTestTrialDto>();
    List<SieveTestTrial> sieveTestTrialList =
        sieveTestTrialRepository.findBySieveTestCode(sieveTestCode);
    for (SieveTestTrial sieveTestTrial : sieveTestTrialList) {
      SieveTestTrialDto sieveTestTrialDto = new SieveTestTrialDto();
      sieveTestTrialDto.setCumulativeRetained(sieveTestTrial.getCumulativeRetained());
      sieveTestTrialDto.setPassing(sieveTestTrial.getPassing());
      sieveTestTrialDto.setPercentageRetained(sieveTestTrial.getPercentageRetained());
      sieveTestTrialDtoList.add(sieveTestTrialDto);
    }
    return sieveTestTrialDtoList;
  }
}
