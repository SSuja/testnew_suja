package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.RatioConfigEquation;
import com.tokyo.supermix.data.repositories.RatioConfigEquationRepository;

@Service
public class RatioConfigEquationServiceImpl implements RatioConfigEquationService {

  @Autowired
  private RatioConfigEquationRepository ratioConfigEquationRepository;

  @Transactional
  public void saveRatioConfigEquation(RatioConfigEquation ratioConfigEquation) {
    ratioConfigEquationRepository.save(ratioConfigEquation);
  }

  @Transactional(readOnly = true)
  public List<RatioConfigEquation> getAllRatioConfigEquations() {
    return ratioConfigEquationRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<RatioConfigEquation> getAllRatioConfigEquationsByRatioConfig(Long ratioConfigId) {
    return ratioConfigEquationRepository.findByRatioConfigId(ratioConfigId);
  }

  @Transactional(readOnly = true)
  public RatioConfigEquation getRatioConfigEquationById(Long id) {
    return ratioConfigEquationRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isRatioConfigEquationExistsById(Long id) {
    return ratioConfigEquationRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public boolean isRatioConfigEquationExistsByRatioConfigId(Long ratioConfigId) {
    return ratioConfigEquationRepository.existsByRatioConfigId(ratioConfigId);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteRatioConfigEquation(Long id) {
    ratioConfigEquationRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isRatioExists(String ratio) {
    return ratioConfigEquationRepository.existsByRatio(ratio);
  }

  @Transactional(readOnly = true)
  public boolean isRatioExistsByRatioConfig(Long ratioConfigId, String ratio) {
    return ratioConfigEquationRepository.existsByRatioConfigIdAndRatio(ratioConfigId, ratio);
  }
}
