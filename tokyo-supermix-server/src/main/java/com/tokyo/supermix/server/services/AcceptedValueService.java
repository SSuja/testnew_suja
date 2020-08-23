package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.AcceptedValueMainDto;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;

public interface AcceptedValueService {

  public void saveAcceptedValue(AcceptedValue acceptedValue);

  public List<AcceptedValue> getAllAcceptedValues();

  boolean isAcceptedValueExist(Long id);

  public AcceptedValue getAcceptedValueById(Long id);

  public void deleteAcceptedValue(Long id);

  public List<AcceptedValue> getAcceptedValueByTestConfigure(TestConfigure testConfigure);

  boolean isAcceptedValueByTestConfigureId(Long testConfigureId);

  boolean isAcceptedValueByTestConfigureIdAndTestEquation(Long testConfigureId,
      Long testEquationId);

  public boolean isUpdatedAcceptedValueTestConfigureIdExist(Long id, Long testConfigureId);

  public Page<AcceptedValue> searchAcceptedValue(Predicate predicate, int size, int page);

  public List<AcceptedValue> findByTestConfigure(Long testConfigureId);

  public boolean isAcceptedValueByTestConfigureIdAndTestParameter(Long testConfigureId,
      Long testParameterId);

  public boolean isCheckValidation(AcceptedValue acceptedValue);

  public AcceptedValueMainDto findByTestConfigureId(Long testConfigureId);

  public boolean existsAcceptedValueByTestConfigureId(Long testConfigureId);
}
