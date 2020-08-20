package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.AbbrevationAndValueDto;
import com.tokyo.supermix.data.dto.AcceptedValueJasperDto;
import com.tokyo.supermix.data.dto.ConcreteTestReportDto;
import com.tokyo.supermix.data.dto.CubeTestReportDto;
import com.tokyo.supermix.data.dto.FinishProductTestReportDetailDto;
import com.tokyo.supermix.data.dto.IncomingSampleJasperDeliveryDto;
import com.tokyo.supermix.data.dto.IncomingSampleJasperTestDto;
import com.tokyo.supermix.data.dto.IncomingSampleResponseDto;
import com.tokyo.supermix.data.dto.MaterialTestTrialResultDto;
import com.tokyo.supermix.data.dto.PlantDto;
import com.tokyo.supermix.data.dto.report.AcceptedValueDto;
import com.tokyo.supermix.data.dto.report.AcceptedValueForSieveTest;
import com.tokyo.supermix.data.dto.report.ConcreteStrengthDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleDeliveryReportDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleReportDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleStatusCount;
import com.tokyo.supermix.data.dto.report.IncomingSampleTestDto;
import com.tokyo.supermix.data.dto.report.MaterialResult;
import com.tokyo.supermix.data.dto.report.MaterialTestReportDto;
import com.tokyo.supermix.data.dto.report.SeiveTestReportResponseDto;
import com.tokyo.supermix.data.dto.report.SieveResultAndParameter;
import com.tokyo.supermix.data.dto.report.SieveTestTrialDto;
import com.tokyo.supermix.data.dto.report.SupplierReportDto;
import com.tokyo.supermix.data.dto.report.TestAndResult;
import com.tokyo.supermix.data.dto.report.TestReportDetailDto;
import com.tokyo.supermix.data.dto.report.TestTrialDto;
import com.tokyo.supermix.data.dto.report.TrailValueDto;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.FinishProductParameterResult;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.entities.FinishProductTrial;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestResult;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.data.entities.TestEquation;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.ReportFormat;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.FinishProductParameterResultRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleIssueRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.FinishProductTestRepository;
import com.tokyo.supermix.data.repositories.FinishProductTrialRepository;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestResultRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;
import com.tokyo.supermix.data.repositories.SupplierRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.data.repositories.TestEquationRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;

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
  @Autowired
  FinishProductSampleIssueRepository finishProductSampleIssueRepository;
  @Autowired
  FinishProductTestRepository finishProductTestRepository;
  @Autowired
  FinishProductTrialRepository finishProductTrialRepository;
  @Autowired
  TestConfigureRepository testConfigureRepository;
  @Autowired
  FinishProductSampleRepository finishProductSampleRepository;
  @Autowired
  private MaterialTestResultRepository materialTestResultRepository;
  @Autowired
  private TestParameterService testParameterService;
  @Autowired
  private FinishProductParameterResultRepository finishProductParameterResultRepository;
  @Autowired
  private TestParameterRepository testParameterRepository;
  @Autowired
  private TestEquationRepository testEquationRepository;

  // Generate Test Report for Material Test Wise
  @Transactional(readOnly = true)
  public TestReportDetailDto getMaterialTestDetailReportPlantWise(String materialTestCode,
      String plantCode) {
    TestReportDetailDto reportDto = new TestReportDetailDto();
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    reportDto.setMaterialTest(getMaterialTestReport(materialTestCode));
    reportDto.setTestName(materialTest.getTestConfigure().getTest().getName());
    reportDto
        .setIncomingsample(getIncomingSampleDetails(materialTest.getIncomingSample().getCode()));
    reportDto.setTestTrials(getMaterialTestTrialDtoReport(materialTestCode));
    reportDto.setPlant(mapper.map(materialTest.getIncomingSample().getPlant(), PlantDto.class));
    if ((materialTest.getTestConfigure().getAcceptedType().equals(AcceptedType.MATERIAL))) {
      if (materialTest.getTestConfigure().getRawMaterial() != null) {
        reportDto.setAcceptanceCriterias(
            getMaterialAcceptedValueDto(materialTest.getTestConfigure().getId(),
                materialTest.getIncomingSample().getRawMaterial().getId()));
      } else {
        reportDto.setAcceptanceCriterias(
            getMaterialValueIsNull(materialTest.getTestConfigure().getId()));
      }
    } else {
      reportDto.setAcceptanceCriterias(
          getAcceptedCriteriaDetails(materialTest.getTestConfigure().getId()));
    }
    reportDto.setTrailValues(getTrailValueDtoList(materialTestCode));
    return reportDto;
  }

  @Transactional(readOnly = true)
  public TestReportDetailDto getMaterialTestDetailReport(String materialTestCode) {
    TestReportDetailDto reportDto = new TestReportDetailDto();
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    reportDto.setMaterialTest(getMaterialTestReport(materialTestCode));
    reportDto.setTestName(materialTest.getTestConfigure().getTest().getName());
    reportDto
        .setIncomingsample(getIncomingSampleDetails(materialTest.getIncomingSample().getCode()));
    reportDto.setTestTrials(getMaterialTestTrialDtoReport(materialTestCode));
    reportDto.setPlant(mapper.map(materialTest.getIncomingSample().getPlant(), PlantDto.class));
    if ((materialTest.getTestConfigure().getAcceptedType().equals(AcceptedType.MATERIAL))) {
      if (materialTest.getTestConfigure().getRawMaterial() != null) {
        reportDto.setAcceptanceCriterias(
            getMaterialAcceptedValueDto(materialTest.getTestConfigure().getId(),
                materialTest.getIncomingSample().getRawMaterial().getId()));
      } else {
        reportDto.setAcceptanceCriterias(
            getMaterialValueIsNull(materialTest.getTestConfigure().getId()));
      }
    } else {
      reportDto.setAcceptanceCriterias(
          getAcceptedCriteriaDetails(materialTest.getTestConfigure().getId()));
    }
    reportDto.setTrailValues(getTrailValueDtoList(materialTestCode));
    return reportDto;
  }

  private MaterialTestReportDto getMaterialTestReport(String materialTestCode) {
    MaterialTestReportDto materialTestReportDto = new MaterialTestReportDto();
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    materialTestReportDto.setCode(materialTest.getCode());
    materialTestReportDto.setNoOfTrial(materialTest.getNoOfTrial());
    materialTestReportDto.setStatus(materialTest.getStatus());
    materialTestReportDto.setMaterialResults(getResults(materialTestCode));
    materialTestReportDto.setDate(materialTest.getCreatedAt());
    materialTestReportDto.setCommment(materialTest.getComment());
    return materialTestReportDto;
  }

  private List<TestTrialDto> getMaterialTestTrialDtoReport(String materialTestCode) {
    List<TestTrialDto> trailList = new ArrayList<TestTrialDto>();
    materialTestTrialRepository.findByMaterialTestCode(materialTestCode).forEach(trail -> {
      trailList.add(mapper.map(trail, TestTrialDto.class));
    });
    return trailList;
  }

  private List<MaterialResult> getResults(String materialTestCode) {
    List<MaterialResult> materialResults = new ArrayList<MaterialResult>();
    List<MaterialTestResult> materialTestResults =
        materialTestResultRepository.findByMaterialTestCode(materialTestCode);
    materialTestResults.forEach(results -> {
      MaterialResult materialResult = new MaterialResult();
      if (results.getTestEquation() != null) {
        materialResult.setTestParameterName(
            results.getTestEquation().getTestParameter().getParameter().getName());
        materialResult.setAverage(results.getResult());
        materialResults.add(materialResult);
      } else {
        materialResult
            .setTestName(results.getMaterialTest().getTestConfigure().getTest().getName());
        materialResult.setAverage(results.getResult());
        materialResults.add(materialResult);
      }
    });
    return materialResults;
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
        trailValueDto.setAbbreviation(paramResult.getTestParameter().getAbbreviation());
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

  private List<AcceptedValueDto> getAcceptedCriteriaDetails(Long testConfigureId) {
    List<AcceptedValueDto> acceptedValueDtoList = new ArrayList<AcceptedValueDto>();
    List<AcceptedValue> acceptedValueList =
        acceptedValueRepository.findByTestConfigureId(testConfigureId);
    acceptedValueList.forEach(values -> {
      AcceptedValueDto acceptedValueDtos = new AcceptedValueDto();
      if (values.getConditionRange() == Condition.BETWEEN) {
        acceptedValueDtos.setCondition(values.getConditionRange());
        acceptedValueDtos.setMaxValue(values.getMaxValue());
        acceptedValueDtos.setMinValue(values.getMinValue());
      } else if (values.getConditionRange() == Condition.EQUAL
          || values.getConditionRange() == Condition.GREATER_THAN
          || values.getConditionRange() == Condition.LESS_THAN) {
        acceptedValueDtos.setValue(values.getValue());
        acceptedValueDtos.setCondition(values.getConditionRange());
      }
      acceptedValueDtoList.add(acceptedValueDtos);
    });
    return acceptedValueDtoList;
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

  private List<AcceptedValueDto> getMaterialAcceptedValueDto(Long testConfigureId,
      Long rawMaterialId) {
    List<AcceptedValueDto> acceptedValueDtoList = new ArrayList<AcceptedValueDto>();
    List<MaterialAcceptedValue> materialAcceptedValues = materialAcceptedValueRepository
        .findByTestConfigureIdAndTestConfigureRawMaterialId(testConfigureId, rawMaterialId);
    materialAcceptedValues.forEach(materialAccepted -> {
      AcceptedValueDto acceptedValueDto = new AcceptedValueDto();
      if (materialAccepted.getConditionRange() == Condition.BETWEEN) {
        acceptedValueDto.setCondition(materialAccepted.getConditionRange());
        acceptedValueDto.setMaxValue(materialAccepted.getMaxValue());
        acceptedValueDto.setMinValue(materialAccepted.getMinValue());
      } else if (materialAccepted.getConditionRange() == Condition.EQUAL
          || materialAccepted.getConditionRange() == Condition.GREATER_THAN
          || materialAccepted.getConditionRange() == Condition.LESS_THAN) {
        acceptedValueDto.setCondition(materialAccepted.getConditionRange());
        acceptedValueDto.setValue(materialAccepted.getValue());
      }
      acceptedValueDtoList.add(acceptedValueDto);
    });
    return acceptedValueDtoList;
  }

  private List<AcceptedValueDto> getMaterialValueIsNull(Long testConfigureId) {
    List<AcceptedValueDto> acceptedValueDtoList = new ArrayList<AcceptedValueDto>();
    List<MaterialAcceptedValue> materialAcceptedValues =
        materialAcceptedValueRepository.findByTestConfigureId(testConfigureId);
    List<MaterialTest> materialTestList =
        materialTestRepository.findByTestConfigureId(testConfigureId);
    materialAcceptedValues.forEach(materialAccepted -> {
      if (materialTestList.get(0).getIncomingSample().getRawMaterial().getId() == materialAccepted
          .getRawMaterial().getId()) {
        AcceptedValueDto acceptedValueDto = new AcceptedValueDto();
        if (materialAccepted.getConditionRange() == Condition.BETWEEN) {
          acceptedValueDto.setCondition(materialAccepted.getConditionRange());
          acceptedValueDto.setMaxValue(materialAccepted.getMaxValue());
          acceptedValueDto.setMinValue(materialAccepted.getMinValue());
          acceptedValueDto.setMaterial(materialAccepted.getRawMaterial().getName());
        } else if (materialAccepted.getConditionRange() == Condition.EQUAL
            || materialAccepted.getConditionRange() == Condition.GREATER_THAN
            || materialAccepted.getConditionRange() == Condition.LESS_THAN) {
          acceptedValueDto.setCondition(materialAccepted.getConditionRange());
          acceptedValueDto.setValue(materialAccepted.getValue());
          acceptedValueDto.setMaterial(materialAccepted.getRawMaterial().getName());
        }
        acceptedValueDtoList.add(acceptedValueDto);
      }

    });
    return acceptedValueDtoList;
  }

  // Incoming Sample Summary Report
  @Transactional(readOnly = true)
  public IncomingSampleDeliveryReportDto getIncomingSampleSummaryReportPlantWise(
      String incomingSampleCode, String plantCode) {
    IncomingSampleDeliveryReportDto incomingSampleDeliveryReportDto =
        new IncomingSampleDeliveryReportDto();
    List<MaterialTest> materialTest = materialTestRepository
        .findByIncomingSampleCodeAndIncomingSamplePlantCode(incomingSampleCode, plantCode);
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
      List<MaterialTestResult> materialTestResult =
          materialTestResultRepository.findByMaterialTestCode(test.getCode());
      incomingSampleTestDto.setAverage(materialTestResult.get(0).getResult());
      incomingSampleTestDto.setStatus(test.getStatus().name());
      incomingSampleTestDto.setDate(new java.sql.Date(test.getCreatedAt().getTime()));
      if ((test.getTestConfigure().getAcceptedType().equals(AcceptedType.MATERIAL))) {
        if (test.getTestConfigure().getRawMaterial() != null) {
          incomingSampleTestDto.setAcceptanceCriteria(getMaterialAcceptedValueDto(
              test.getTestConfigure().getId(), test.getIncomingSample().getRawMaterial().getId()));
        } else {
          incomingSampleTestDto
              .setAcceptanceCriteria(getMaterialValueIsNull(test.getTestConfigure().getId()));
        }
      } else {
        incomingSampleTestDto
            .setAcceptanceCriteria(getAcceptedCriteriaDetails(test.getTestConfigure().getId()));
      }
      incomingSampleTestDtoList.add(incomingSampleTestDto);
    });
    return incomingSampleTestDtoList;
  }

  private List<IncomingSampleTestDto> getIncomingSampleDeliveryReport(String incomingSampleCode,
      ReportFormat reportFormat) {
    List<IncomingSampleTestDto> incomingSampleTestDtoList = new ArrayList<IncomingSampleTestDto>();
    materialTestRepository
        .findByIncomingSampleCodeAndTestConfigureReportFormat(incomingSampleCode, reportFormat)
        .forEach(test -> {
          IncomingSampleTestDto incomingSampleTestDto = new IncomingSampleTestDto();
          incomingSampleTestDto.setTestName(test.getTestConfigure().getTest().getName());
          List<MaterialTestResult> materialTestResult =
              materialTestResultRepository.findByMaterialTestCode(test.getCode());
          incomingSampleTestDto.setAverage(materialTestResult.get(0).getResult());
          incomingSampleTestDto.setStatus(test.getStatus().name());
          incomingSampleTestDto.setDate(new java.sql.Date(test.getCreatedAt().getTime()));
          if ((test.getTestConfigure().getAcceptedType().equals(AcceptedType.MATERIAL))) {
            if (test.getTestConfigure().getRawMaterial() != null) {
              incomingSampleTestDto.setAcceptanceCriteria(
                  getMaterialAcceptedValueDto(test.getTestConfigure().getId(),
                      test.getIncomingSample().getRawMaterial().getId()));
            } else {
              incomingSampleTestDto
                  .setAcceptanceCriteria(getMaterialValueIsNull(test.getTestConfigure().getId()));
            }
          } else {
            incomingSampleTestDto
                .setAcceptanceCriteria(getAcceptedCriteriaDetails(test.getTestConfigure().getId()));
          }
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
  public IncomingSampleDeliveryReportDto getIncomingSampleDeliveryReportPlantWise(
      String incomingSampleCode, ReportFormat reportFormat, String plantCode) {
    IncomingSampleDeliveryReportDto incomingSampleDeliveryReportDto =
        new IncomingSampleDeliveryReportDto();
    List<MaterialTest> materialTest = materialTestRepository
        .findByIncomingSampleCodeAndTestConfigureReportFormatAndIncomingSamplePlantCode(
            incomingSampleCode, reportFormat, plantCode);
    incomingSampleDeliveryReportDto.setIncomingsample(
        getIncomingSampleDetails(materialTest.get(0).getIncomingSample().getCode()));
    incomingSampleDeliveryReportDto
        .setPlant(mapper.map(materialTest.get(0).getIncomingSample().getPlant(), PlantDto.class));
    incomingSampleDeliveryReportDto.setIncomingSampleTestDtos(
        getIncomingSampleDeliveryReport(incomingSampleCode, reportFormat));
    incomingSampleDeliveryReportDto
        .setIncomingSampleStatusCounts(getIncomingSampleStatusCount(incomingSampleCode));
    incomingSampleDeliveryReportDto.setSupplierReportDtos(
        getSupplierReport(materialTest.get(0).getIncomingSample().getSupplier().getId()));
    return incomingSampleDeliveryReportDto;
  }

  // Incoming Sample Delivery Report for Moisture Test
  @Transactional(readOnly = true)
  public IncomingSampleDeliveryReportDto getIncomingSampleDeliveryReports(String incomingSampleCode,
      ReportFormat reportFormat) {
    IncomingSampleDeliveryReportDto incomingSampleDeliveryReportDto =
        new IncomingSampleDeliveryReportDto();
    List<MaterialTest> materialTest = materialTestRepository
        .findByIncomingSampleCodeAndTestConfigureReportFormat(incomingSampleCode, reportFormat);
    incomingSampleDeliveryReportDto.setIncomingsample(
        getIncomingSampleDetails(materialTest.get(0).getIncomingSample().getCode()));
    incomingSampleDeliveryReportDto
        .setPlant(mapper.map(materialTest.get(0).getIncomingSample().getPlant(), PlantDto.class));
    incomingSampleDeliveryReportDto.setIncomingSampleTestDtos(
        getIncomingSampleDeliveryReport(incomingSampleCode, reportFormat));
    incomingSampleDeliveryReportDto
        .setIncomingSampleStatusCounts(getIncomingSampleStatusCount(incomingSampleCode));
    incomingSampleDeliveryReportDto.setSupplierReportDtos(
        getSupplierReport(materialTest.get(0).getIncomingSample().getSupplier().getId()));
    return incomingSampleDeliveryReportDto;
  }

  // Concrete test report
  public ConcreteTestReportDto getConcreteTestReportByPlant(String finishProductTestCode,
      String plantCode) {
    ConcreteTestReportDto concreteTestReportDto = new ConcreteTestReportDto();
    if (finishProductTestRepository.existsByFinishProductSampleMixDesignPlantCode(plantCode)
        && finishProductTestRepository.existsByCode(finishProductTestCode)) {
      FinishProductTest finishProductTest = finishProductTestRepository
          .findByCodeAndFinishProductSampleMixDesignPlantCode(finishProductTestCode, plantCode);
      List<TestParameter> testParamterList =
          testParameterRepository.findByTestConfigureIdAndInputMethods(
              finishProductTest.getTestConfigure().getId(), InputMethod.OBSERVE);
      concreteTestReportDto.setAddress(
          finishProductTest.getFinishProductSample().getMixDesign().getPlant().getAddress());
      concreteTestReportDto.setPlantName(
          finishProductTest.getFinishProductSample().getMixDesign().getPlant().getName());
      concreteTestReportDto.setFaxNumber(
          finishProductTest.getFinishProductSample().getMixDesign().getPlant().getFaxNumber());
      concreteTestReportDto
          .setReportNo(finishProductTest.getFinishProductSample().getFinishProductCode());
      if (finishProductTest.getFinishProductSample().getProject() != null) {
        concreteTestReportDto.setCustomerName(
            finishProductTest.getFinishProductSample().getProject().getCustomer().getName());
        concreteTestReportDto
            .setProjectName(finishProductTest.getFinishProductSample().getProject().getName());
      }
      concreteTestReportDto.setTargetGrade(
          finishProductTest.getFinishProductSample().getMixDesign().getTargetGrade());
      concreteTestReportDto.setTargetSlump(
          finishProductTest.getFinishProductSample().getMixDesign().getTargetSlump());
      concreteTestReportDto
          .setDateOfCasting(finishProductTest.getFinishProductSample().getCreatedAt().toString());
      concreteTestReportDto
          .setDateOfTesting(finishProductTest.getFinishProductSample().getUpdatedAt().toString());
      concreteTestReportDto
          .setAgeOfCubeTest(finishProductTest.getTestConfigure().getTest().getName());
      concreteTestReportDto.setCubeTestReports(getCubeTestRepots(finishProductTestCode));
      for (TestParameter testParamter : testParamterList) {
        if (testParamter.getType().equals(TestParameterType.INPUT)
            && testParamter.getMixDesignField() == null) {
          FinishProductParameterResult finishProductResult =
              finishProductParameterResultRepository.findByTestParameterIdAndFinishProductTestCode(
                  testParamter.getId(), finishProductTestCode);
          concreteTestReportDto.setAverageStrength(finishProductResult.getResult());
        }
      }
    }
    return concreteTestReportDto;
  }

  public ConcreteTestReportDto getConcreteTestReport(String finishProductTestCode) {
    ConcreteTestReportDto concreteTestReportDto = new ConcreteTestReportDto();
    FinishProductTest finishProductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    List<TestParameter> testParamterList =
        testParameterRepository.findByTestConfigureIdAndInputMethods(
            finishProductTest.getTestConfigure().getId(), InputMethod.OBSERVE);
    concreteTestReportDto.setAddress(
        finishProductTest.getFinishProductSample().getMixDesign().getPlant().getAddress());
    concreteTestReportDto.setPlantName(
        finishProductTest.getFinishProductSample().getMixDesign().getPlant().getName());
    concreteTestReportDto.setFaxNumber(
        finishProductTest.getFinishProductSample().getMixDesign().getPlant().getFaxNumber());
    concreteTestReportDto
        .setReportNo(finishProductTest.getFinishProductSample().getFinishProductCode());
    if (finishProductTest.getFinishProductSample().getProject() != null) {
      concreteTestReportDto.setCustomerName(
          finishProductTest.getFinishProductSample().getProject().getCustomer().getName());
      concreteTestReportDto
          .setProjectName(finishProductTest.getFinishProductSample().getProject().getName());
    }
    concreteTestReportDto
        .setTargetGrade(finishProductTest.getFinishProductSample().getMixDesign().getTargetGrade());
    concreteTestReportDto
        .setTargetSlump(finishProductTest.getFinishProductSample().getMixDesign().getTargetSlump());
    concreteTestReportDto
        .setDateOfCasting(finishProductTest.getFinishProductSample().getCreatedAt().toString());
    concreteTestReportDto
        .setDateOfTesting(finishProductTest.getFinishProductSample().getUpdatedAt().toString());
    concreteTestReportDto
        .setAgeOfCubeTest(finishProductTest.getTestConfigure().getTest().getName());
    concreteTestReportDto.setCubeTestReports(getCubeTestRepots(finishProductTestCode));
    for (TestParameter testParamter : testParamterList) {
      if (testParamter.getType().equals(TestParameterType.INPUT)
          && testParamter.getMixDesignField() == null) {
        FinishProductParameterResult finishProductResult =
            finishProductParameterResultRepository.findByTestParameterIdAndFinishProductTestCode(
                testParamter.getId(), finishProductTestCode);
        concreteTestReportDto.setAverageStrength(finishProductResult.getResult());
      }
    }
    return concreteTestReportDto;
  }

  public List<CubeTestReportDto> getCubeTestRepots(String finishProductTestCode) {
    List<FinishProductTrial> finishProductTrialList =
        finishProductTrialRepository.findByFinishProductTestCode(finishProductTestCode);
    ArrayList<CubeTestReportDto> cubeTestReportDtoList = new ArrayList<CubeTestReportDto>();
    for (FinishProductTrial finishProductTrial : finishProductTrialList) {
      CubeTestReportDto cubeTestReportDto = new CubeTestReportDto();
      if (finishProductTrial.getTestParameter().getInputMethods().equals(InputMethod.OBSERVE)
          && finishProductTrial.getTestParameter().getType().equals(TestParameterType.INPUT)
          && finishProductTrial.getTestParameter().getMixDesignField() == null) {
        cubeTestReportDto.setCubeNo(finishProductTrial.getTrialNo());
        cubeTestReportDto.setStrengthValue(finishProductTrial.getValue());
        cubeTestReportDtoList.add(cubeTestReportDto);
      }
    }
    return cubeTestReportDtoList;
  }

  @Transactional(readOnly = true)
  public List<MaterialTestTrialResultDto> getMaterialTestTrailByMaterialTestCode(
      String materialTestCode) {
    List<MaterialTestTrial> materialTestTrialList =
        materialTestTrialRepository.findByMaterialTestCode(materialTestCode);
    List<ParameterResult> parameterResultList = parameterResultRepository
        .findByMaterialTestTrialCode(materialTestTrialList.get(0).getCode());
    ArrayList<MaterialTestTrialResultDto> materialTestTrialResultDtoList =
        new ArrayList<MaterialTestTrialResultDto>();
    for (ParameterResult parameterResult : parameterResultList) {
      MaterialTestTrialResultDto materialTestTrialResultDto = new MaterialTestTrialResultDto();
      materialTestTrialResultDto
          .setAbbrevation(parameterResult.getTestParameter().getAbbreviation());
      materialTestTrialResultDto.setAbbrevationAndValues(getAbbAndValueByMaterialTestCode(
          parameterResult.getMaterialTest().getCode(), parameterResult.getTestParameter().getId()));
      materialTestTrialResultDtoList.add(materialTestTrialResultDto);
    }
    return materialTestTrialResultDtoList;
  }

  public List<AbbrevationAndValueDto> getAbbAndValueByMaterialTestCode(String materialTestCode,
      Long testParameterId) {
    List<MaterialTestTrial> materialTestTrialList =
        materialTestTrialRepository.findByMaterialTestCode(materialTestCode);
    ArrayList<AbbrevationAndValueDto> abbrevationAndValueDtoList =
        new ArrayList<AbbrevationAndValueDto>();
    for (MaterialTestTrial materialTestTrial : materialTestTrialList) {
      AbbrevationAndValueDto abbrevationAndValueDto = new AbbrevationAndValueDto();
      abbrevationAndValueDto.setMaterialTrialCode(materialTestTrial.getCode());
      ParameterResult parameterResult =
          parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(testParameterId,
              materialTestTrial.getCode());
      if (parameterResult.getValue() != null) {
        abbrevationAndValueDto.setValue(parameterResult.getValue());
      }
      abbrevationAndValueDtoList.add(abbrevationAndValueDto);
    }
    return abbrevationAndValueDtoList;
  }

  public boolean isFinishProductSampleExist(String finishProductSampleCode) {
    return finishProductTestRepository.existsByFinishProductSampleCode(finishProductSampleCode);
  }

  public List<ConcreteStrengthDto> getConcreteStrengthsByPlant(String plantCode) {
    ArrayList<ConcreteStrengthDto> averageStrengthList = new ArrayList<ConcreteStrengthDto>();
    List<FinishProductSample> finishProductSampleList =
        finishProductSampleRepository.findByMixDesignPlantCode(plantCode);
    for (FinishProductSample finishProductSample : finishProductSampleList) {
      ConcreteStrengthDto averageStrength = new ConcreteStrengthDto();
      if (isFinishProductSampleExist(finishProductSample.getCode())) {
        if (finishProductSample.getStatus().equals(Status.PASS)) {
          averageStrength.setCubeCode(finishProductSample.getFinishProductCode());
          averageStrength.setTestAndResult(getTestResults(finishProductSample.getCode()));
          averageStrengthList.add(averageStrength);
        }
      }
    }
    return averageStrengthList;
  }

  public List<ConcreteStrengthDto> getConcreteStrengths() {
    ArrayList<ConcreteStrengthDto> averageStrengthList = new ArrayList<ConcreteStrengthDto>();
    List<FinishProductSample> finishProductSampleList = finishProductSampleRepository.findAll();
    for (FinishProductSample finishProductSample : finishProductSampleList) {
      ConcreteStrengthDto averageStrength = new ConcreteStrengthDto();

      if (isFinishProductSampleExist(finishProductSample.getCode())) {
        FinishProductTest finishProductTest = finishProductTestRepository
            .findByFinishProductSampleCode(finishProductSample.getCode()).get(0);
        if (!(!finishProductTest.getTestConfigure().isCoreTest()
            && finishProductTest.getTestConfigure().isName())) {
          averageStrength.setCubeCode(finishProductSample.getFinishProductCode());
        }
        averageStrength.setTestAndResult(getTestResults(finishProductSample.getCode()));
        averageStrengthList.add(averageStrength);
      }
    }
    return averageStrengthList;
  }

  public List<TestAndResult> getTestResults(String finishProductSampleCode) {
    ArrayList<TestAndResult> testAndResultList = new ArrayList<TestAndResult>();
    List<FinishProductParameterResult> finishProductParameterResultList =
        finishProductParameterResultRepository
            .findByFinishProductTestFinishProductSampleCode(finishProductSampleCode);
    for (FinishProductParameterResult finishProductParameterResult : finishProductParameterResultList) {
      TestAndResult testAndResult = new TestAndResult();
      if (finishProductParameterResult.getTestParameter().getType() != null) {
        if (finishProductParameterResult.getTestParameter().getInputMethods()
            .equals(InputMethod.OBSERVE)
            && finishProductParameterResult.getTestParameter().getType()
                .equals(TestParameterType.INPUT)
            && finishProductParameterResult.getTestParameter().getMixDesignField() == null) {
          if (!(!finishProductParameterResult.getTestParameter().getTestConfigure().isCoreTest()
              && finishProductParameterResult.getTestParameter().getTestConfigure().isName())) {
            testAndResult.setTestName(finishProductParameterResult.getFinishProductTest()
                .getTestConfigure().getTest().getName());
            testAndResult.setResult(finishProductParameterResult.getResult());
            testAndResultList.add(testAndResult);
          }
        }
      }
    }
    return testAndResultList;
  }

  public SeiveTestReportResponseDto getSieveTestReportByPlant(String materialTestCode,
      String plantCode) {
    SeiveTestReportResponseDto seiveTestReportResponseDto = new SeiveTestReportResponseDto();
    if (materialTestRepository.existsByCode(materialTestCode)
        && materialTestRepository.existsByIncomingSamplePlantCode(plantCode)) {
      MaterialTest materialTest =
          materialTestRepository.findByCodeAndIncomingSamplePlantCode(materialTestCode, plantCode);
      seiveTestReportResponseDto
          .setPlant(mapper.map(materialTest.getIncomingSample().getPlant(), PlantDto.class));
      seiveTestReportResponseDto.setIncomingSample(
          mapper.map(materialTest.getIncomingSample(), IncomingSampleResponseDto.class));
      seiveTestReportResponseDto.setSieveTestTrial(getTrialResult(materialTestCode));
    }
    return seiveTestReportResponseDto;
  }

  public SeiveTestReportResponseDto getSieveTestReport(String materialTestCode) {
    SeiveTestReportResponseDto seiveTestReportResponseDto = new SeiveTestReportResponseDto();
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    seiveTestReportResponseDto
        .setPlant(mapper.map(materialTest.getIncomingSample().getPlant(), PlantDto.class));
    seiveTestReportResponseDto.setIncomingSample(
        mapper.map(materialTest.getIncomingSample(), IncomingSampleResponseDto.class));
    seiveTestReportResponseDto.setSieveTestTrial(getTrialResult(materialTestCode));
    return seiveTestReportResponseDto;
  }

  public List<SieveTestTrialDto> getTrialResult(String materialTestCode) {
    ArrayList<SieveTestTrialDto> sieveTestTrialDtoList = new ArrayList<SieveTestTrialDto>();
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    for (String level : testParameterService
        .getAllOriLevel(materialTest.getTestConfigure().getId())) {
      SieveTestTrialDto sieveTestTrialDto = new SieveTestTrialDto();

      sieveTestTrialDto.setSize(level);
      sieveTestTrialDto.setSieveResultAndParameter(
          getParaAndResult(level, materialTest.getTestConfigure().getId(), materialTestCode));
      sieveTestTrialDtoList.add(sieveTestTrialDto);
    }
    return sieveTestTrialDtoList;
  }

  public List<SieveResultAndParameter> getParaAndResult(String level, Long testConfigId,
      String materialTestCode) {
    ArrayList<SieveResultAndParameter> sieveResultAndParameterList =
        new ArrayList<SieveResultAndParameter>();
    List<ParameterResult> parameterResultList = parameterResultRepository
        .findByTestParameterLevelAndTestParameterTestConfigureIdAndMaterialTestCode(level,
            testConfigId, materialTestCode);
    for (ParameterResult parameterResult : parameterResultList) {
      SieveResultAndParameter sieveResultAndParameter = new SieveResultAndParameter();
      if (parameterResult.getTestParameter().getName() != null) {
        String[] parts = parameterResult.getTestParameter().getName().split("_");
        if (parameterResult.getTestParameter().getInputMethods().equals(InputMethod.MEASUREMENT)
            && parameterResult.getTestParameter().getType().equals(TestParameterType.RESULT)) {
          AcceptedValue acceptedValue = acceptedValueRepository
              .findByTestParameterId(parameterResult.getTestParameter().getId());
          sieveResultAndParameter.setParameter(parts[0].toString());
          sieveResultAndParameter.setVale(acceptedValue.getMaxValue().toString() + "  -  "
              + acceptedValue.getMinValue().toString());
        } else {
          sieveResultAndParameter.setParameter(parts[0].toString());
          sieveResultAndParameter.setVale(parameterResult.getValue().toString());
        }

      } else {
        if (parameterResult.getTestParameter().getInputMethods().equals(InputMethod.CALCULATION)
            && parameterResult.getTestParameter().getType().equals(TestParameterType.RESULT)) {
          TestEquation testEquation = testEquationRepository
              .findByTestParameterId(parameterResult.getTestParameter().getId());
          sieveResultAndParameter.setAcceptedValueForSieveTest(getAcceptedValueForSieveTestResult(
              testConfigId, parameterResult.getTestParameter().getId(), testEquation.getId()));
        }
        sieveResultAndParameter
            .setParameter(parameterResult.getTestParameter().getParameter().getName());
        sieveResultAndParameter.setVale(parameterResult.getValue().toString());
      }
      sieveResultAndParameterList.add(sieveResultAndParameter);
    }
    return sieveResultAndParameterList;
  }

  public AcceptedValueForSieveTest getAcceptedValueForSieveTest(Long testParameterId) {
    AcceptedValueForSieveTest acceptedValueForSieveTest = new AcceptedValueForSieveTest();
    AcceptedValue acceptedValue = acceptedValueRepository.findByTestParameterId(testParameterId);
    acceptedValueForSieveTest.setConditionRange(acceptedValue.getConditionRange());
    acceptedValueForSieveTest.setMaxValue(acceptedValue.getMaxValue());
    acceptedValueForSieveTest.setMinValue(acceptedValue.getMinValue());
    return acceptedValueForSieveTest;
  }

  public AcceptedValueForSieveTest getAcceptedValueForSieveTestResult(Long testConfigId,
      Long testParaId, Long testEquationId) {
    AcceptedValueForSieveTest acceptedValueForSieveTest = new AcceptedValueForSieveTest();
    AcceptedValue acceptedValue = acceptedValueRepository
        .findByTestConfigureIdAndTestEquationId(testConfigId, testEquationId);
    if (acceptedValue != null) {
      acceptedValueForSieveTest.setConditionRange(acceptedValue.getConditionRange());
      acceptedValueForSieveTest.setMaxValue(acceptedValue.getMaxValue());
      acceptedValueForSieveTest.setMinValue(acceptedValue.getMinValue());
    }
    return acceptedValueForSieveTest;
  }

  public FinishProductTestReportDetailDto getFinishProductTestDetailReport(
      String finishProductTestCode) {
    return null;
  }

  public List<SieveTestTrialDto> getTrialResultGraph(String materialTestCode) {
    ArrayList<SieveTestTrialDto> sieveTestTrialDtoList = new ArrayList<SieveTestTrialDto>();
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    for (String level : testParameterService
        .getAllOriLevel(materialTest.getTestConfigure().getId())) {
      SieveTestTrialDto sieveTestTrialDto = new SieveTestTrialDto();
      sieveTestTrialDto.setSize(level);
      sieveTestTrialDto.setSieveResultAndParameter(
          getParaAndResultGraph(level, materialTest.getTestConfigure().getId(), materialTestCode));
      sieveTestTrialDtoList.add(sieveTestTrialDto);
    }
    return sieveTestTrialDtoList;
  }

  public List<SieveResultAndParameter> getParaAndResultGraph(String level, Long testConfigId,
      String materialTestCode) {
    ArrayList<SieveResultAndParameter> sieveResultAndParameterList =
        new ArrayList<SieveResultAndParameter>();
    List<ParameterResult> parameterResultList = parameterResultRepository
        .findByTestParameterLevelAndTestParameterTestConfigureIdAndMaterialTestCode(level,
            testConfigId, materialTestCode);
    for (ParameterResult parameterResult : parameterResultList) {
      SieveResultAndParameter sieveResultAndParameter = new SieveResultAndParameter();
      if (parameterResult.getTestParameter().getName() != null) {
        String[] parts = parameterResult.getTestParameter().getName().split("_");
        if (parameterResult.getTestParameter().getInputMethods().equals(InputMethod.MEASUREMENT)
            && parameterResult.getTestParameter().getType().equals(TestParameterType.RESULT)) {
          sieveResultAndParameter.setParameter(parts[0].toString());
          sieveResultAndParameter.setAcceptedValueForSieveTest(
              getAcceptedValueForSieveTestGraph(parameterResult.getTestParameter().getId()));
        }
        if (parameterResult.getTestParameter().getInputMethods().equals(InputMethod.CALCULATION)
            && parameterResult.getTestParameter().getType().equals(TestParameterType.INPUT)) {
          sieveResultAndParameter.setParameter(parts[0].toString());
          sieveResultAndParameter.setVale(parameterResult.getValue().toString());
        }
      } else {
        if (parameterResult.getTestParameter().getInputMethods().equals(InputMethod.CALCULATION)
            && parameterResult.getTestParameter().getType().equals(TestParameterType.RESULT)) {
          TestEquation testEquation = testEquationRepository
              .findByTestParameterId(parameterResult.getTestParameter().getId());
          getAcceptedValueForSieveTestResult(testConfigId,
              parameterResult.getTestParameter().getId(), testEquation.getId());
        }
        sieveResultAndParameter
            .setParameter(parameterResult.getTestParameter().getParameter().getName());
        sieveResultAndParameter.setVale(parameterResult.getValue().toString());
      }
      sieveResultAndParameterList.add(sieveResultAndParameter);
    }
    return sieveResultAndParameterList;
  }

  public AcceptedValueForSieveTest getAcceptedValueForSieveTestGraph(Long testParameterId) {
    AcceptedValueForSieveTest acceptedValueForSieveTest = new AcceptedValueForSieveTest();
    AcceptedValue acceptedValue = acceptedValueRepository.findByTestParameterId(testParameterId);
    acceptedValueForSieveTest.setConditionRange(acceptedValue.getConditionRange());
    acceptedValueForSieveTest.setMaxValue(acceptedValue.getMaxValue());
    acceptedValueForSieveTest.setMinValue(acceptedValue.getMinValue());
    return acceptedValueForSieveTest;
  }

  @Transactional(readOnly = true)
  public IncomingSampleJasperDeliveryDto getIncomingSampleDeliveryReports1(
      String incomingSampleCode, ReportFormat reportFormat) {
    IncomingSampleJasperDeliveryDto incomingSampleJasperDeliveryDto =
        new IncomingSampleJasperDeliveryDto();
    List<MaterialTest> materialTest = materialTestRepository
        .findByIncomingSampleCodeAndTestConfigureReportFormat(incomingSampleCode, reportFormat);
    incomingSampleJasperDeliveryDto.setIncomingsample(
        getIncomingSampleDetails(materialTest.get(0).getIncomingSample().getCode()));
    incomingSampleJasperDeliveryDto
        .setPlant(mapper.map(materialTest.get(0).getIncomingSample().getPlant(), PlantDto.class));
    incomingSampleJasperDeliveryDto.setIncomingSampleTestDtos(
        getIncomingSampleDeliveryReport1(incomingSampleCode, reportFormat));
    incomingSampleJasperDeliveryDto.setSupplierReportDtos(
        getSupplierReport(materialTest.get(0).getIncomingSample().getSupplier().getId()));
    return incomingSampleJasperDeliveryDto;
  }

  private List<IncomingSampleJasperTestDto> getIncomingSampleDeliveryReport1(
      String incomingSampleCode, ReportFormat reportFormat) {
    List<IncomingSampleJasperTestDto> incomingSampleTestDtoList =
        new ArrayList<IncomingSampleJasperTestDto>();
    materialTestRepository
        .findByIncomingSampleCodeAndTestConfigureReportFormat(incomingSampleCode, reportFormat)
        .forEach(test -> {
          IncomingSampleJasperTestDto incomingSampleJasperTestDto =
              new IncomingSampleJasperTestDto();
          incomingSampleJasperTestDto.setTestName(test.getTestConfigure().getTest().getName());
          List<MaterialTestResult> materialTestResult =
              materialTestResultRepository.findByMaterialTestCode(test.getCode());
          incomingSampleJasperTestDto.setAverage(materialTestResult.get(0).getResult());
          incomingSampleJasperTestDto.setStatus(test.getStatus().name());
          incomingSampleJasperTestDto.setDate(new java.sql.Date(test.getCreatedAt().getTime()));
          if ((test.getTestConfigure().getAcceptedType().equals(AcceptedType.MATERIAL))) {
            if (test.getTestConfigure().getRawMaterial() != null) {
              incomingSampleJasperTestDto.setAcceptanceCriteria(
                  getMaterialAcceptedValueDtoNEW(test.getTestConfigure().getId(),
                      test.getIncomingSample().getRawMaterial().getId()));
            } else {
              incomingSampleJasperTestDto.setAcceptanceCriteria(
                  getMaterialValueIsNullNew(test.getTestConfigure().getId()));
            }
          } else {
            incomingSampleJasperTestDto.setAcceptanceCriteria(
                getAcceptedCriteriaDetailsNew(test.getTestConfigure().getId()));
          }
          incomingSampleTestDtoList.add(incomingSampleJasperTestDto);
        });
    return incomingSampleTestDtoList;
  }

  @Transactional(readOnly = true)
  public IncomingSampleJasperDeliveryDto getIncomingSampleJasperSummaryReport1(
      String incomingSampleCode) {
    IncomingSampleJasperDeliveryDto incomingSampleJasperDeliveryDto =
        new IncomingSampleJasperDeliveryDto();
    List<MaterialTest> materialTest =
        materialTestRepository.findByIncomingSampleCode(incomingSampleCode);
    incomingSampleJasperDeliveryDto.setIncomingsample(
        getIncomingSampleDetails(materialTest.get(0).getIncomingSample().getCode()));
    incomingSampleJasperDeliveryDto
        .setPlant(mapper.map(materialTest.get(0).getIncomingSample().getPlant(), PlantDto.class));
    incomingSampleJasperDeliveryDto
        .setIncomingSampleTestDtos(getIncomingSampleTestDtoReport1(incomingSampleCode));
    incomingSampleJasperDeliveryDto.setSupplierReportDtos(
        getSupplierReport(materialTest.get(0).getIncomingSample().getSupplier().getId()));
    return incomingSampleJasperDeliveryDto;
  }

  private List<IncomingSampleJasperTestDto> getIncomingSampleTestDtoReport1(
      String incomingSampleCode) {
    List<IncomingSampleJasperTestDto> incomingSampleJasperTestDtoList =
        new ArrayList<IncomingSampleJasperTestDto>();
    materialTestRepository.findByIncomingSampleCode(incomingSampleCode).forEach(test -> {
      IncomingSampleJasperTestDto incomingSampleJasperTestDto = new IncomingSampleJasperTestDto();
      incomingSampleJasperTestDto.setTestName(test.getTestConfigure().getTest().getName());
      List<MaterialTestResult> materialTestResult =
          materialTestResultRepository.findByMaterialTestCode(test.getCode());
      incomingSampleJasperTestDto.setAverage(materialTestResult.get(0).getResult());
      incomingSampleJasperTestDto.setStatus(test.getStatus().name());
      incomingSampleJasperTestDto.setDate(new java.sql.Date(test.getCreatedAt().getTime()));
      if ((test.getTestConfigure().getAcceptedType().equals(AcceptedType.MATERIAL))) {
        if (test.getTestConfigure().getRawMaterial() != null) {
          incomingSampleJasperTestDto.setAcceptanceCriteria(getMaterialAcceptedValueDtoNEW(
              test.getTestConfigure().getId(), test.getIncomingSample().getRawMaterial().getId()));
        } else {
          incomingSampleJasperTestDto
              .setAcceptanceCriteria(getMaterialValueIsNullNew(test.getTestConfigure().getId()));
        }
      } else {
        incomingSampleJasperTestDto
            .setAcceptanceCriteria(getAcceptedCriteriaDetailsNew(test.getTestConfigure().getId()));
      }
      incomingSampleJasperTestDtoList.add(incomingSampleJasperTestDto);
    });
    return incomingSampleJasperTestDtoList;
  }

  private AcceptedValueJasperDto getAcceptedCriteriaDetailsNew(Long testConfigureId) {
      List<AcceptedValue> acceptedValueList =
        acceptedValueRepository.findByTestConfigureId(testConfigureId);
    AcceptedValueJasperDto acceptedValueDtos = new AcceptedValueJasperDto();
    acceptedValueList.forEach(values -> {
      if (values.getConditionRange() == Condition.BETWEEN) {
        acceptedValueDtos.setCondition(values.getConditionRange());
        acceptedValueDtos.setMaxValue(values.getMaxValue().toString());
        acceptedValueDtos.setMinValue(values.getMinValue().toString());
      } else if (values.getConditionRange() == Condition.EQUAL) {
        acceptedValueDtos.setMinValue("Equal to");
        acceptedValueDtos.setMaxValue(values.getValue().toString());
        acceptedValueDtos.setCondition(values.getConditionRange());
          }
      else if ( values.getConditionRange() == Condition.GREATER_THAN) {
        acceptedValueDtos.setMinValue("Greater than");
        acceptedValueDtos.setMaxValue(values.getValue().toString());
        acceptedValueDtos.setCondition(values.getConditionRange());
          }
      else if ( values.getConditionRange() == Condition.LESS_THAN) {
        acceptedValueDtos.setMinValue("Less than");
        acceptedValueDtos.setMaxValue(values.getValue().toString());
        acceptedValueDtos.setCondition(values.getConditionRange());
          }       
    });return acceptedValueDtos;

  }

  private AcceptedValueJasperDto getMaterialValueIsNullNew(Long testConfigureId) {
        List<MaterialAcceptedValue> materialAcceptedValues =
        materialAcceptedValueRepository.findByTestConfigureId(testConfigureId);
    List<MaterialTest> materialTestList =
        materialTestRepository.findByTestConfigureId(testConfigureId);
    AcceptedValueJasperDto acceptedValueDtos = new AcceptedValueJasperDto();
    materialAcceptedValues.forEach(values -> {
      if (materialTestList.get(0).getIncomingSample().getRawMaterial().getId() == values
          .getRawMaterial().getId()) {
        if (values.getConditionRange() == Condition.BETWEEN) {
          acceptedValueDtos.setCondition(values.getConditionRange());
          acceptedValueDtos.setMaxValue(values.getMaxValue().toString());
          acceptedValueDtos.setMinValue(values.getMinValue().toString());
          acceptedValueDtos.setMaterial(values.getRawMaterial().getName());
        }  else if (values.getConditionRange() == Condition.EQUAL) {
          acceptedValueDtos.setMinValue("Equal to");
          acceptedValueDtos.setMaxValue(values.getValue().toString());
          acceptedValueDtos.setCondition(values.getConditionRange());
            }
        else if ( values.getConditionRange() == Condition.GREATER_THAN) {
          acceptedValueDtos.setMinValue("Greater than");
          acceptedValueDtos.setMaxValue(values.getValue().toString());
          acceptedValueDtos.setCondition(values.getConditionRange());
            }
        else if ( values.getConditionRange() == Condition.LESS_THAN) {
          acceptedValueDtos.setMinValue("Less than");
          acceptedValueDtos.setMaxValue(values.getValue().toString());
          acceptedValueDtos.setCondition(values.getConditionRange());
            }   
      }
    });
    return acceptedValueDtos;
  }

  private AcceptedValueJasperDto getMaterialAcceptedValueDtoNEW(Long testConfigureId,
      Long rawMaterialId) {
       List<MaterialAcceptedValue> materialAcceptedValues = materialAcceptedValueRepository
        .findByTestConfigureIdAndTestConfigureRawMaterialId(testConfigureId, rawMaterialId);
    AcceptedValueJasperDto acceptedValueDtos = new AcceptedValueJasperDto();
    materialAcceptedValues.forEach(values -> {
      if (values.getConditionRange() == Condition.BETWEEN) {
        acceptedValueDtos.setCondition(values.getConditionRange());
        acceptedValueDtos.setMaxValue(values.getMaxValue().toString());
        acceptedValueDtos.setMinValue(values.getMinValue().toString());
      }  else if (values.getConditionRange() == Condition.EQUAL) {
        acceptedValueDtos.setMinValue("Equal to");
        acceptedValueDtos.setMaxValue(values.getValue().toString());
        acceptedValueDtos.setCondition(values.getConditionRange());
          }
      else if ( values.getConditionRange() == Condition.GREATER_THAN) {
        acceptedValueDtos.setMinValue("Greater than");
        acceptedValueDtos.setMaxValue(values.getValue().toString());
        acceptedValueDtos.setCondition(values.getConditionRange());
          }
      else if ( values.getConditionRange() == Condition.LESS_THAN) {
        acceptedValueDtos.setMinValue("Less than");
        acceptedValueDtos.setMaxValue(values.getValue().toString());
        acceptedValueDtos.setCondition(values.getConditionRange());
          }   
    });
    return acceptedValueDtos;
  }
}
