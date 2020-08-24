package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.AccepetedValueDto;
import com.tokyo.supermix.data.dto.AcceptedValueMainDto;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;

@Service
public class AcceptedValueServiceImpl implements AcceptedValueService {

  @Autowired
  private AcceptedValueRepository acceptedValueRepository;
  @Autowired
  private TestConfigureService testConfigureService;

  @Transactional
  public void saveAcceptedValue(AcceptedValue acceptedValue) {
    acceptedValueRepository.save(acceptedValue);
  }

  @Transactional(readOnly = true)
  public List<AcceptedValue> getAllAcceptedValues() {
    return acceptedValueRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isAcceptedValueExist(Long id) {
    return acceptedValueRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public AcceptedValue getAcceptedValueById(Long id) {
    return acceptedValueRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteAcceptedValue(Long id) {
    acceptedValueRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<AcceptedValue> getAcceptedValueByTestConfigure(TestConfigure testConfigure) {
    return acceptedValueRepository.findByTestConfigure(testConfigure);
  }

  @Transactional(readOnly = true)
  public boolean isAcceptedValueByTestConfigureId(Long testConfigureId) {
    return acceptedValueRepository.existsByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isAcceptedValueByTestConfigureIdAndTestEquation(Long testConfigureId,
      Long testEquationId) {
    if (acceptedValueRepository.existsByTestConfigureIdAndTestEquationId(testConfigureId,
        testEquationId)) {
      return true;
    }
    return false;
  }

  public boolean isUpdatedAcceptedValueTestConfigureIdExist(Long id, Long testConfigureId) {
    if ((!getAcceptedValueById(id).getTestConfigure().getId().equals(testConfigureId))
        && (isAcceptedValueByTestConfigureId(testConfigureId))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public Page<AcceptedValue> searchAcceptedValue(Predicate predicate, int size, int page) {
    return acceptedValueRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public List<AcceptedValue> findByTestConfigure(Long testConfigureId) {
    return acceptedValueRepository.findByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isAcceptedValueByTestConfigureIdAndTestParameter(Long testConfigureId,
      Long testParameterId) {
    return acceptedValueRepository.existsByTestConfigureIdAndTestParameterId(testConfigureId,
        testParameterId);
  }

  @Transactional(readOnly = true)
  public boolean isCheckValidation(AcceptedValue acceptedValue) {
    if (acceptedValue.getConditionRange().equals(Condition.BETWEEN)) {
      if (acceptedValue.getMaxValue() == null || acceptedValue.getMinValue() == null) {
        return true;
      }
    } else if (acceptedValue.getConditionRange().equals(Condition.GREATER_THAN)
        || acceptedValue.getConditionRange().equals(Condition.LESS_THAN)
        || acceptedValue.getConditionRange().equals(Condition.EQUAL)) {
      if (acceptedValue.getValue() == null) {
        return true;
      }
    }
    return false;
  }

  @Transactional(readOnly = true)
  public AcceptedValueMainDto findByTestConfigureId(Long testConfigureId) {
    AcceptedValueMainDto acceptedValueMainDto = new AcceptedValueMainDto();
    acceptedValueMainDto.setTestConfigureResDto(
        testConfigureService.getTestConfigureForAcceptedValue(testConfigureId));
    acceptedValueMainDto.setAccepetedValueDto(getAccepetedValueDtoList(testConfigureId));
    return acceptedValueMainDto;
  }

  private List<AccepetedValueDto> getAccepetedValueDtoList(Long testConfigureId) {
    List<AccepetedValueDto> accepetedValueDtolist = new ArrayList<>();
    acceptedValueRepository.findByTestConfigureId(testConfigureId).stream()
        .forEach(acceptedValue -> {
          AccepetedValueDto accepetedValueDto = new AccepetedValueDto();
          accepetedValueDto.setId(acceptedValue.getId());
          accepetedValueDto.setConditionRange(acceptedValue.getConditionRange().toString());
          accepetedValueDto.setMinValue(acceptedValue.getMinValue());
          accepetedValueDto.setMaxValue(acceptedValue.getMaxValue());
          accepetedValueDto.setFinalResult(acceptedValue.isFinalResult());
          accepetedValueDto.setValue(acceptedValue.getValue());
          if (acceptedValue.getTestEquation() != null) {
            accepetedValueDto
                .setTestEquationId(acceptedValue.getTestEquation().getEquation().getId());
          }
          accepetedValueDto
              .setParameterName(acceptedValue.getTestParameter().getParameter().getName());
          accepetedValueDto.setTestParameterId(acceptedValue.getTestParameter().getId());
          if (acceptedValue.getTestParameter().getName() != null) {
            accepetedValueDto.setTestParameterName(acceptedValue.getTestParameter().getName());
          }
          accepetedValueDtolist.add(accepetedValueDto);
        });
    return accepetedValueDtolist;
  }

  @Transactional(readOnly = true)
  public boolean existsAcceptedValueByTestConfigureId(Long testConfigureId) {
    return acceptedValueRepository.existsByTestConfigureId(testConfigureId);
  }
}
