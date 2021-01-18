package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.dto.MultiResultFormulaResponseDto;
import com.tokyo.supermix.data.entities.MultiResultFormula;

public interface MultiResultFormulaService {

  public MultiResultFormulaResponseDto getByTestConfigureId(Long testConfigureId);

  public boolean isExistsTestConfigureId(Long testConfigureId);

  public boolean isExistById(Long id);

  public void saveMultiResultFormula(MultiResultFormula multiResultFormula);

  public MultiResultFormula getById(Long id);

  public boolean isUpdatedTestConfigureId(Long id, Long testConfigureId);
}
