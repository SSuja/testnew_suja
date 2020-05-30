package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.dto.PlantDto;
import com.tokyo.supermix.data.dto.report.AcceptedValueDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleReportDto;
import com.tokyo.supermix.data.dto.report.MaterialTestReportDto;
import com.tokyo.supermix.data.dto.report.ParameterResultDto;
import com.tokyo.supermix.data.dto.report.TestDetailDto;
import com.tokyo.supermix.data.dto.report.TestDetailForSampleDto;
import com.tokyo.supermix.data.dto.report.TestReportDto;
import com.tokyo.supermix.data.dto.report.TestTrialReportDto;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.EquationRepository;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;

@Service
public class TestReportServiceImpl implements TestReportService {
  @Autowired
  private Mapper mapper;
  @Autowired
  private MaterialTestTrialRepository materialTestTrialRepository;
  @Autowired
  private ParameterResultRepository parameterResultRepository;
  @Autowired
  private MaterialTestRepository materialTestRepository;
  @Autowired
  private EquationRepository equationRepository;
  @Autowired
  private AcceptedValueRepository acceptedValueRepository;
  @Autowired
  private IncomingSampleRepository incomingSampleRepository;

  @Override
  public TestReportDto getMaterialTestReport(String materialTestCode) {
    TestReportDto reportDto = new TestReportDto();
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    MaterialTestReportDto materialTestDto = mapper.map(materialTest, MaterialTestReportDto.class);
    reportDto.setMaterialTest(materialTestDto);
    reportDto.setEquation(equationRepository
        .findByTestConfigureId(materialTest.getTestConfigure().getId()).getFormula());
    reportDto.setTestName(materialTest.getTestConfigure().getTest().getName());
    reportDto
        .setIncomingsample(getIncomingSampleDetails(materialTest.getIncomingSample().getCode()));
    reportDto.setTestTrials(getMaterialTestTrialReport(materialTestCode));
    reportDto.setPlant(mapper.map(materialTest.getIncomingSample().getPlant(), PlantDto.class));
    reportDto
        .setAcceptanceCriteria(getAcceptedCriteriaDetails(materialTest.getTestConfigure().getId()));
    return reportDto;
  }

  private AcceptedValueDto getAcceptedCriteriaDetails(Long testConfigureId) {
    AcceptedValue acceptedValue = acceptedValueRepository.findByTestConfigureId(testConfigureId);
    AcceptedValueDto acceptedValueDto = mapper.map(acceptedValue, AcceptedValueDto.class);
    acceptedValueDto.setUnit(acceptedValue.getUnit().getUnit());
    return acceptedValueDto;
  }

  private IncomingSampleReportDto getIncomingSampleDetails(String incomingSampleCode) {
    IncomingSample incomingSample = incomingSampleRepository.findById(incomingSampleCode).get();
    IncomingSampleReportDto incomingSampleReportDto =
        mapper.map(incomingSample, IncomingSampleReportDto.class);
    incomingSampleReportDto
        .setMaterialSubCategory(incomingSample.getRawMaterial().getMaterialSubCategory().getName());
    incomingSampleReportDto.setMaterialCategory(
        incomingSample.getRawMaterial().getMaterialSubCategory().getMaterialCategory().getName());
    return incomingSampleReportDto;
  }


  private List<TestTrialReportDto> getMaterialTestTrialReport(String materialTestCode) {
    List<TestTrialReportDto> trailList = new ArrayList<TestTrialReportDto>();
    List<MaterialTestTrial> testTrailList =
        materialTestTrialRepository.findByMaterialTestCode(materialTestCode);
    testTrailList.forEach(trail -> {
      TestTrialReportDto dto = mapper.map(trail, TestTrialReportDto.class);
      dto.setParameterResults(getParameterResults(trail.getCode()));
      trailList.add(dto);
    });
    return trailList;
  }

  private List<ParameterResultDto> getParameterResults(String materialTestTrialCode) {
    List<ParameterResultDto> parameterResultDtoList = new ArrayList<ParameterResultDto>();
    List<ParameterResult> parameterResultList =
        parameterResultRepository.findByMaterialTestTrialCode(materialTestTrialCode);
    parameterResultList.forEach(parameterResult -> {
      ParameterResultDto dto = new ParameterResultDto();
      dto.setParameterName(parameterResult.getTestParameter().getParameter().getName());
      dto.setValue(parameterResult.getValue());
      parameterResultDtoList.add(dto);
    });
    return parameterResultDtoList;
  }

  @Override
  public TestDetailForSampleDto getTestDetails(String incomingSampleCode) {
    TestDetailForSampleDto testDetailForSampleDto = new TestDetailForSampleDto();
    List<MaterialTest> materialTestList =
        materialTestRepository.findByIncomingSampleCode(incomingSampleCode);
    List<TestDetailDto> testDetailDtoList = new ArrayList<TestDetailDto>();
    materialTestList.forEach(mTest -> {
      TestDetailDto testDetailDto = new TestDetailDto();
      testDetailDto.setTestName(mTest.getTestConfigure().getTest().getName());
      testDetailDto.setActualValue(mTest.getAverage());
      testDetailDto
          .setAcceptanceCriteria(getAcceptedCriteriaDetails(mTest.getTestConfigure().getId()));
      testDetailDto.setStatus(mTest.getStatus().toString());
      testDetailDtoList.add(testDetailDto);
    });
    testDetailForSampleDto.setTestDetails(testDetailDtoList);
    testDetailForSampleDto.setIncomingsample(getIncomingSampleDetails(incomingSampleCode));
    testDetailForSampleDto.setPlant(mapper.map(
        incomingSampleRepository.findById(incomingSampleCode).get().getPlant(), PlantDto.class));
    return testDetailForSampleDto;
  }
}
