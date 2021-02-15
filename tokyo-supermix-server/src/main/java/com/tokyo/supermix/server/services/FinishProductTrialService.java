package com.tokyo.supermix.server.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.FinishProductTrial;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.security.UserPrincipal;

public interface FinishProductTrialService {
  public List<FinishProductTrial> getAllFinishProductTrials();

  public List<FinishProductTrial> getAllFinishProductTrialsByPlant(UserPrincipal currentUser);

  public FinishProductTrial getFinishProductTrialByCode(Long id);

  public void saveFinishProductTrial(List<FinishProductTrial> finishProductTrialList);

  public void deleteFinishProductTrial(Long id);

  public boolean isFinishProductTrialExists(Long id);

  public List<FinishProductTrial> getFinishProductTrialsByFinishProductTestCode(
      String finishProductTestCode);

  public boolean isFinishProductTestExists(String finishProductTestCode);

  public void saveFinishproductResult(String finishProductTestCode, HttpServletRequest request);

  public void updateFinishProductTestTrial(FinishProductTrial finishProductTrial);

  public List<FinishProductTrial> getAllFinishProductTrialsByPlantCode(String plantCode);

  public void saveAverageCalculationResult(String finishProductCode);


  public void saveAverageCalculationFinishProductTrials(
      List<FinishProductTrial> finishProductTrialList);

  public boolean getEquationCheck(String finishProductTestCode);
  
  public void aprovedUpdateStatus(String finishProductTestCode,
      HttpServletRequest request);
}

