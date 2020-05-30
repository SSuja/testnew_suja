package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.PlantDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleReportDto;
import com.tokyo.supermix.data.dto.report.SieveTestDto;
import com.tokyo.supermix.data.dto.report.SieveTestReportDto;
import com.tokyo.supermix.data.dto.report.SieveTestTrialDto;
import com.tokyo.supermix.data.entities.SieveAcceptedValue;
import com.tokyo.supermix.data.entities.SieveTest;
import com.tokyo.supermix.data.entities.SieveTestTrial;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.SieveAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.SieveTestRepository;
import com.tokyo.supermix.data.repositories.SieveTestTrialRepository;

@Service
public class SieveTestReportServiceImpl implements SieveTestReportService {
  @Autowired
  private Mapper mapper;
  @Autowired
  private SieveTestRepository sieveTestRepository;
  @Autowired
  private SieveTestTrialRepository sieveTestTrialRepository;
  @Autowired
  private SieveAcceptedValueRepository sieveAcceptedValueRepository;

  @Transactional(readOnly = true)
  public SieveTestReportDto getSeiveTestReport(String seiveTestCode) {
    SieveTestReportDto sieveTestReportDto = new SieveTestReportDto();
    SieveTest sieveTest = sieveTestRepository.findByCode(seiveTestCode);
    SieveTestDto sieveTestDto = mapper.map(sieveTest, SieveTestDto.class);
    sieveTestReportDto.setSieveTest(sieveTestDto);
    sieveTestReportDto.setIncomingsample(getIncomingSampleDetails(sieveTest));
    sieveTestReportDto.setSieveTestTrials(getSeiveTestTrialReport(sieveTest));
    sieveTestReportDto
        .setPlant(mapper.map(sieveTest.getIncomingSample().getPlant(), PlantDto.class));
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

  public List<SieveTestTrialDto> getSeiveTestTrialReport(SieveTest sieveTest) {
    List<SieveTestTrialDto> sieveTestTrialDtoList = new ArrayList<SieveTestTrialDto>();
    List<SieveTestTrial> sieveTestTrialList =
        sieveTestTrialRepository.findBySieveTestCode(sieveTest.getCode());
    for (SieveTestTrial sieveTestTrial : sieveTestTrialList) {
      SieveTestTrialDto sieveTestTrialDto = new SieveTestTrialDto();
      sieveTestTrialDto.setCumulativeRetained(sieveTestTrial.getCumulativeRetained());
      sieveTestTrialDto.setPassing(sieveTestTrial.getPassing());
      sieveTestTrialDto.setPercentageRetained(sieveTestTrial.getPercentageRetained());
      sieveTestTrialDto.setSize(sieveTestTrial.getSieveSize().getSize());
      SieveAcceptedValue sieveAcceptedValue =
          sieveAcceptedValueRepository.findBySieveSizeId(sieveTestTrial.getSieveSize().getId());
      sieveTestTrialDto.setMax(sieveAcceptedValue.getMax());
      sieveTestTrialDto.setMin(sieveAcceptedValue.getMin());
      sieveTestTrialDtoList.add(sieveTestTrialDto);
    }
    return sieveTestTrialDtoList;
  }
}
