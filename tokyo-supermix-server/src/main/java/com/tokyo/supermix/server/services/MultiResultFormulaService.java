package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MultiResultFormula;

public interface MultiResultFormulaService {

  public List<MultiResultFormula> getByTestConfigureId(Long testConfigureId);

  public boolean isExistsTestConfigureId(Long testConfigureId);

  public boolean isExistById(Long id);

  public void saveMultiResultFormula(MultiResultFormula multiResultFormula);
}
