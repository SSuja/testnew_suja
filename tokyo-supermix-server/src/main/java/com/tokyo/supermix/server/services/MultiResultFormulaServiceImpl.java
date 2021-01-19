package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.MultiResultFormulaParameterDto;
import com.tokyo.supermix.data.dto.MultiResultFormulaResponseDto;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.MultiResultFormula;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MultiResultFormulaRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;

@Service
public class MultiResultFormulaServiceImpl implements MultiResultFormulaService {
  @Autowired
  private MultiResultFormulaRepository multiResultFormulaRepository;
  @Autowired
  private AcceptedValueRepository acceptedValueRepository;
  @Autowired
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;
  @Autowired
  private TestParameterRepository testParameterRepository;

  @Transactional(readOnly = true)
  public MultiResultFormulaResponseDto getByTestConfigureId(Long testConfigureId) {
    MultiResultFormulaResponseDto multiResultFormulaResponseDto =
        new MultiResultFormulaResponseDto();
    MultiResultFormula multiResultFormula =
        multiResultFormulaRepository.findByTestConfigureId(testConfigureId);
    multiResultFormulaResponseDto.setId(multiResultFormula.getId());
    multiResultFormulaResponseDto.setLogicalFormula(multiResultFormula.getLogicalFormula());
    multiResultFormulaResponseDto.setTestConfigureId(multiResultFormula.getTestConfigure().getId());
    if (multiResultFormula.getTestConfigure().getAcceptedType() == AcceptedType.TEST) {
      multiResultFormulaResponseDto.setMultiResultFormulaParameterDtos(
          getResultFormulaParameterForAcceptedValue(testConfigureId));
    } else {
      multiResultFormulaResponseDto.setMultiResultFormulaParameterDtos(
          getResultFormulaParameterForMaterialAcceptedValue(testConfigureId));
    }
    return multiResultFormulaResponseDto;
  }

  @Transactional(readOnly = true)
  private List<MultiResultFormulaParameterDto> getResultFormulaParameterForAcceptedValue(
      Long testConfigureId) {
    List<MultiResultFormulaParameterDto> multiResultFormulaParameterDtos =
        new ArrayList<MultiResultFormulaParameterDto>();
    List<AcceptedValue> acceptedValues =
        acceptedValueRepository.findByTestConfigureIdAndFinalResultTrue(testConfigureId);
    acceptedValues.forEach(parameters -> {
      MultiResultFormulaParameterDto multiResultFormulaParameterDto =
          new MultiResultFormulaParameterDto();
      multiResultFormulaParameterDto.setTestParameterId(parameters.getTestParameter().getId());
      multiResultFormulaParameterDto
          .setTestParameterName(parameters.getTestParameter().getParameter().getName());
      multiResultFormulaParameterDto
          .setAbbreviation(parameters.getTestParameter().getAbbreviation());
      multiResultFormulaParameterDtos.add(multiResultFormulaParameterDto);
    });
    return multiResultFormulaParameterDtos;
  }

  @Transactional(readOnly = true)
  private List<MultiResultFormulaParameterDto> getResultFormulaParameterForMaterialAcceptedValue(
      Long testConfigureId) {
    List<MultiResultFormulaParameterDto> multiResultFormulaParameterDtos =
        new ArrayList<MultiResultFormulaParameterDto>();
    List<MaterialAcceptedValue> materialAcceptedValues =
        materialAcceptedValueRepository.findByTestConfigureIdAndFinalResultTrue(testConfigureId);
    if (!materialAcceptedValues.isEmpty()) {
      List<Long> ids = new ArrayList<Long>();
      List<Long> orinalIds = new ArrayList<Long>();
      for (MaterialAcceptedValue materialAcceptedValue : materialAcceptedValues) {
        ids.add(materialAcceptedValue.getTestParameter().getId());
      }
      orinalIds.addAll(ids.stream().distinct().collect(Collectors.toList()));
      for (int i = 0; i < orinalIds.size(); i++) {
        MultiResultFormulaParameterDto multiResultFormulaParameterDto =
            new MultiResultFormulaParameterDto();
        TestParameter testParameter = testParameterRepository.findById(orinalIds.get(i)).get();
        multiResultFormulaParameterDto.setTestParameterId(testParameter.getId());
        multiResultFormulaParameterDto.setTestParameterName(testParameter.getParameter().getName());
        multiResultFormulaParameterDto.setAbbreviation(testParameter.getAbbreviation());
        multiResultFormulaParameterDtos.add(multiResultFormulaParameterDto);
      }
    }
    return multiResultFormulaParameterDtos;
  }

  @Transactional(readOnly = true)
  public boolean isExistsTestConfigureId(Long testConfigureId) {
    return multiResultFormulaRepository.existsByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isExistById(Long id) {
    return multiResultFormulaRepository.existsById(id);
  }

  @Transactional
  public void saveMultiResultFormula(MultiResultFormula multiResultFormula) {
    multiResultFormulaRepository.save(multiResultFormula);
  }

  @Transactional(readOnly = true)
  public MultiResultFormula getById(Long id) {
    return multiResultFormulaRepository.findById(id).get();
  }

  public boolean isUpdatedTestConfigureId(Long id, Long testConfigureId) {
    if ((!getById(id).getTestConfigure().getId().equals(id))
        && (isExistsTestConfigureId(testConfigureId))) {
      return true;
    }
    return false;
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMultiResultFormula(Long id) {
    multiResultFormulaRepository.deleteById(id);
  }
}
