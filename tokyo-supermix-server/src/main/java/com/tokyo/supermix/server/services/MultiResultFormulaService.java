package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MultiResultFormula;

public interface MultiResultFormulaService {

  public List<MultiResultFormula> getAllMultiResultFormulas();

  public boolean isMultiResultFormulaExist(Long id);

  public MultiResultFormula getMultiResultFormulaById(Long id);

  public void deleteMultiResultFormula(Long id);

  public void saveMultiResultFormula(MultiResultFormula multiResultFormula);

}
