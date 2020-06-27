package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.dto.PlantDto;
import com.tokyo.supermix.data.dto.report.AcceptedValueDto;
import com.tokyo.supermix.data.dto.report.AdmixtureTestReportDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleDeliveryReportDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleReportDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleStatusCount;
import com.tokyo.supermix.data.dto.report.IncomingSampleTestDto;
import com.tokyo.supermix.data.dto.report.MaterialAcceptedValueDto;
import com.tokyo.supermix.data.dto.report.MaterialTestReportDto;
import com.tokyo.supermix.data.dto.report.SupplierReportDto;
import com.tokyo.supermix.data.dto.report.TestReportDetailDto;
import com.tokyo.supermix.data.dto.report.TestTrialDto;
import com.tokyo.supermix.data.dto.report.TrailValueDto;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;
import com.tokyo.supermix.data.repositories.SupplierRepository;

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
  private AcceptedValueRepository acceptedValueRepository;
  @Autowired
  private IncomingSampleRepository incomingSampleRepository;
  @Autowired
  private SupplierRepository supplierRepository;
  @Autowired
  MaterialAcceptedValueRepository materialAcceptedValueRepository;

  // Generate Test Report for Material Test Wise
  @Transactional(readOnly = true)
  public TestReportDetailDto getMaterialTestDetailReport(String materialTestCode) {
    TestReportDetailDto reportDto = new TestReportDetailDto();
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    MaterialTestReportDto materialTestDto = mapper.map(materialTest, MaterialTestReportDto.class);
    reportDto.setMaterialTest(materialTestDto);
    reportDto.setEquation(materialTest.getTestConfigure().getEquation().getFormula());
    reportDto.setTestName(materialTest.getTestConfigure().getTest().getName());
    reportDto
        .setIncomingsample(getIncomingSampleDetails(materialTest.getIncomingSample().getCode()));
    reportDto.setTestTrials(getMaterialTestTrialDtoReport(materialTestCode));
    reportDto.setPlant(mapper.map(materialTest.getIncomingSample().getPlant(), PlantDto.class));
    reportDto
        .setAcceptanceCriteria(getAcceptedCriteriaDetails(materialTest.getTestConfigure().getId()));
    reportDto.setTrailValues(getTrailValueDtoList(materialTestCode));
    return reportDto;
  }

  private List<TestTrialDto> getMaterialTestTrialDtoReport(String materialTestCode) {
    List<TestTrialDto> trailList = new ArrayList<TestTrialDto>();
    materialTestTrialRepository.findByMaterialTestCode(materialTestCode).forEach(trail -> {
      trailList.add(mapper.map(trail, TestTrialDto.class));
    });
    return trailList;
  }

  private List<TrailValueDto> getTrailValueDtoList(String materialTestCode) {
    List<TrailValueDto> trailValueDtoList = new ArrayList<TrailValueDto>();
    List<MaterialTestTrial> testTrailList =
        materialTestTrialRepository.findByMaterialTestCode(materialTestCode);
    List<ParameterResult> parameterResults =
        parameterResultRepository.findByMaterialTestTrialCode(testTrailList.get(0).getCode());
    parameterResults.forEach(paramResult -> {
      TrailValueDto trailValueDto = new TrailValueDto();
      if (paramResult.getTestParameter().getParameter() != null) {
        trailValueDto.setParameterName(paramResult.getTestParameter().getParameter().getName());
        trailValueDtoList.add(trailValueDto);
      }
    });
    for (TrailValueDto dto : trailValueDtoList) {
      List<ParameterResult> combined = new ArrayList<ParameterResult>();
      List<Double> values = new ArrayList<Double>();
      for (int i = 0; i < testTrailList.size(); i++) {
        List<ParameterResult> parameterResultss =
            parameterResultRepository.findByMaterialTestTrialCode(testTrailList.get(i).getCode());
        combined.addAll(parameterResultss);
      }
      for (ParameterResult parameterResult : combined) {
        if (parameterResult.getTestParameter().getParameter() != null) {
          if (dto.getParameterName() == parameterResult.getTestParameter().getParameter()
              .getName()) {
            values.add(parameterResult.getValue());
          }
        }
      }
      dto.setValues(values);
    }
    return trailValueDtoList;
  }

  private AcceptedValueDto getAcceptedCriteriaDetails(Long testConfigureId) {
    AcceptedValueDto acceptedValueDto = new AcceptedValueDto();
    AcceptedValue acceptedValue = acceptedValueRepository.findByTestConfigureId(testConfigureId);
    acceptedValueDto.setTestName(acceptedValue.getTestConfigure().getTest().getName());
    acceptedValueDto.setMaxValue(acceptedValue.getMaxValue());
    acceptedValueDto.setMinValue(acceptedValue.getMinValue());
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

  // Cement Test Report
  @Transactional(readOnly = true)
  public TestReportDetailDto getCementDetailReport(String materialTestCode) {
    TestReportDetailDto reportDto = new TestReportDetailDto();
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    MaterialTestReportDto materialTestDto = mapper.map(materialTest, MaterialTestReportDto.class);
    reportDto.setMaterialTest(materialTestDto);
    reportDto.setTestName(materialTest.getTestConfigure().getTest().getName());
    reportDto
        .setIncomingsample(getIncomingSampleDetails(materialTest.getIncomingSample().getCode()));
    reportDto.setTestTrials(getMaterialTestTrialDtoReport(materialTestCode));
    reportDto.setPlant(mapper.map(materialTest.getIncomingSample().getPlant(), PlantDto.class));
    reportDto
        .setAcceptanceCriteria(getAcceptedCriteriaDetails(materialTest.getTestConfigure().getId()));
    reportDto.setTrailValues(getTrailValueDtoList(materialTestCode));
    return reportDto;
  }

  // Admixture Test Report
  @Transactional(readOnly = true)
  public AdmixtureTestReportDto getAdmixtureReport(String materialTestCode) {
    AdmixtureTestReportDto admixtureTestReportDto = new AdmixtureTestReportDto();
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    MaterialTestReportDto materialTestDto = mapper.map(materialTest, MaterialTestReportDto.class);
    admixtureTestReportDto.setMaterialTest(materialTestDto);
    admixtureTestReportDto.setTestName(materialTest.getTestConfigure().getTest().getName());
    admixtureTestReportDto
        .setIncomingsample(getIncomingSampleDetails(materialTest.getIncomingSample().getCode()));
    admixtureTestReportDto.setTestTrials(getMaterialTestTrialDtoReport(materialTestCode));
    admixtureTestReportDto
        .setPlant(mapper.map(materialTest.getIncomingSample().getPlant(), PlantDto.class));
    admixtureTestReportDto.setMaterialAcceptedValueDto(
        getMaterialAcceptedValueDto(materialTest.getTestConfigure().getId()));
    admixtureTestReportDto.setTrailValues(getTrailValueDtoList(materialTestCode));
    return admixtureTestReportDto;
  }

  private MaterialAcceptedValueDto getMaterialAcceptedValueDto(Long testConfigureId) {
    MaterialAcceptedValueDto materialAcceptedValueDto = new MaterialAcceptedValueDto();
    MaterialAcceptedValue materialAcceptedValue =
        materialAcceptedValueRepository.findByTestConfigureId(testConfigureId);
    materialAcceptedValueDto
        .setTestName(materialAcceptedValue.getTestConfigure().getTest().getName());
    materialAcceptedValueDto.setMaxValue(materialAcceptedValue.getMaxValue());
    materialAcceptedValueDto.setMinValue(materialAcceptedValue.getMinValue());
    materialAcceptedValueDto.setUnit(materialAcceptedValue.getUnit().getUnit());
    materialAcceptedValueDto.setRawMaterialName(materialAcceptedValue.getRawMaterial().getName());
    return materialAcceptedValueDto;
  }

  // Incoming Sample Summary Report
  @Transactional(readOnly = true)
  public IncomingSampleDeliveryReportDto getIncomingSampleSummaryReport(String incomingSampleCode) {
    IncomingSampleDeliveryReportDto incomingSampleDeliveryReportDto =
        new IncomingSampleDeliveryReportDto();
    List<MaterialTest> materialTest =
        materialTestRepository.findByIncomingSampleCode(incomingSampleCode);
    incomingSampleDeliveryReportDto.setIncomingsample(
        getIncomingSampleDetails(materialTest.get(0).getIncomingSample().getCode()));
    incomingSampleDeliveryReportDto
        .setPlant(mapper.map(materialTest.get(0).getIncomingSample().getPlant(), PlantDto.class));
    incomingSampleDeliveryReportDto
        .setIncomingSampleTestDtos(getIncomingSampleTestDtoReport(incomingSampleCode));
    incomingSampleDeliveryReportDto
        .setIncomingSampleStatusCounts(getIncomingSampleStatusCount(incomingSampleCode));
    incomingSampleDeliveryReportDto.setSupplierReportDtos(
        getSupplierReport(materialTest.get(0).getIncomingSample().getSupplier().getId()));
    return incomingSampleDeliveryReportDto;
  }

  private List<IncomingSampleTestDto> getIncomingSampleTestDtoReport(String incomingSampleCode) {
    List<IncomingSampleTestDto> incomingSampleTestDtoList = new ArrayList<IncomingSampleTestDto>();
    materialTestRepository.findByIncomingSampleCode(incomingSampleCode).forEach(test -> {
      IncomingSampleTestDto incomingSampleTestDto = new IncomingSampleTestDto();
      incomingSampleTestDto.setTestName(test.getTestConfigure().getTest().getName());
      incomingSampleTestDto.setAverage(test.getAverage());
      incomingSampleTestDto.setStatus(test.getStatus());
      incomingSampleTestDto
          .setAcceptanceCriteria(getAcceptedCriteriaDetails(test.getTestConfigure().getId()));
      incomingSampleTestDtoList.add(incomingSampleTestDto);
    });
    return incomingSampleTestDtoList;
  }

  private List<IncomingSampleTestDto> getIncomingSampleDeliveryReport(String incomingSampleCode,
      String testName) {
    List<IncomingSampleTestDto> incomingSampleTestDtoList = new ArrayList<IncomingSampleTestDto>();
    materialTestRepository
        .findByIncomingSampleCodeAndTestConfigureTestName(incomingSampleCode, testName)
        .forEach(test -> {
          IncomingSampleTestDto incomingSampleTestDto = new IncomingSampleTestDto();
          incomingSampleTestDto.setTestName(test.getTestConfigure().getTest().getName());
          incomingSampleTestDto.setAverage(test.getAverage());
          incomingSampleTestDto.setStatus(test.getStatus());
          incomingSampleTestDto
              .setAcceptanceCriteria(getAcceptedCriteriaDetails(test.getTestConfigure().getId()));
          incomingSampleTestDtoList.add(incomingSampleTestDto);
        });
    return incomingSampleTestDtoList;
  }

  private SupplierReportDto getSupplierReport(Long supplierId) {
    Supplier supplier = supplierRepository.findById(supplierId).get();
    SupplierReportDto supplierReportDto = mapper.map(supplier, SupplierReportDto.class);
    supplierReportDto.setId(supplierId);
    supplierReportDto.setName(supplier.getName());
    supplierReportDto.setPhoneNumber(supplier.getPhoneNumber());
    supplierReportDto.setAddress(supplier.getAddress());
    supplierReportDto.setCompanyName(supplier.getCompanyName());
    supplierReportDto
        .setSupplierCategoryName(supplier.getSupplierCategories().get(0).getCategory());
    supplierReportDto.setEmail(supplier.getEmail());
    supplierReportDto.setPlantName(supplier.getPlant().getName());
    return supplierReportDto;
  }

  private List<IncomingSampleStatusCount> getIncomingSampleStatusCount(String incomingSampleCode) {
    List<IncomingSampleStatusCount> incomingSampleStatusCountList =
        new ArrayList<IncomingSampleStatusCount>();
    List<MaterialTest> materialTestList =
        materialTestRepository.findByIncomingSampleCode(incomingSampleCode);
    Status status = null;
    incomingSampleStatusCountList.add(setIncomingSampleStatusCount(
        materialTestList.get(0).getIncomingSample().getCode(), status));
    return incomingSampleStatusCountList;
  }

  private IncomingSampleStatusCount setIncomingSampleStatusCount(String incomingSampleCode,
      Status status) {
    IncomingSampleStatusCount incomingSampleStatusCount = new IncomingSampleStatusCount();
    incomingSampleStatusCount.setNewCount(materialTestRepository
        .findByIncomingSampleCodeAndStatus(incomingSampleCode, Status.NEW).size());
    incomingSampleStatusCount.setPassCount(materialTestRepository
        .findByIncomingSampleCodeAndStatus(incomingSampleCode, Status.PASS).size());
    incomingSampleStatusCount.setFailCount(materialTestRepository
        .findByIncomingSampleCodeAndStatus(incomingSampleCode, Status.FAIL).size());
    incomingSampleStatusCount.setProcessCount(materialTestRepository
        .findByIncomingSampleCodeAndStatus(incomingSampleCode, Status.PROCESS).size());
    return incomingSampleStatusCount;
  }

  // Incoming Sample Delivery Report for Moisture Test
  @Transactional(readOnly = true)
  public IncomingSampleDeliveryReportDto getIncomingSampleDeliveryReports(String incomingSampleCode,
      String testName) {
    IncomingSampleDeliveryReportDto incomingSampleDeliveryReportDto =
        new IncomingSampleDeliveryReportDto();
    List<MaterialTest> materialTest = materialTestRepository
        .findByIncomingSampleCodeAndTestConfigureTestName(incomingSampleCode, testName);
    incomingSampleDeliveryReportDto.setIncomingsample(
        getIncomingSampleDetails(materialTest.get(0).getIncomingSample().getCode()));
    incomingSampleDeliveryReportDto
        .setPlant(mapper.map(materialTest.get(0).getIncomingSample().getPlant(), PlantDto.class));
    incomingSampleDeliveryReportDto
        .setIncomingSampleTestDtos(getIncomingSampleDeliveryReport(incomingSampleCode, testName));
    incomingSampleDeliveryReportDto
        .setIncomingSampleStatusCounts(getIncomingSampleStatusCount(incomingSampleCode));
    incomingSampleDeliveryReportDto.setSupplierReportDtos(
        getSupplierReport(materialTest.get(0).getIncomingSample().getSupplier().getId()));
    return incomingSampleDeliveryReportDto;
  }
}
