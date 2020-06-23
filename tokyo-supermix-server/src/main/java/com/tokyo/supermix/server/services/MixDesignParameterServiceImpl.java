package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MixDesignParameter;
import com.tokyo.supermix.data.repositories.MixDesignParameterRepository;

@Service
public class MixDesignParameterServiceImpl implements MixDesignParameterService {

  @Autowired
  private MixDesignParameterRepository mixDesignParameterRepository;

  @Transactional
  public void saveMixDesignParameter(MixDesignParameter mixDesignParameter) {
    mixDesignParameterRepository.save(mixDesignParameter);
  }

  @Transactional(readOnly = true)
  public List<MixDesignParameter> getAllMixDesignParameters() {
    return mixDesignParameterRepository.findAll();
  }

  @Transactional(readOnly = true)
  public MixDesignParameter getMixDesignParameterById(Long id) {
    return mixDesignParameterRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public MixDesignParameter getMixDesignParameterByEquationId(Long equationId) {
    return mixDesignParameterRepository.findByEquationId(equationId);
  }

  @Transactional(readOnly = true)
  public boolean isMixDesignParameterExist(Long id) {
    return mixDesignParameterRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMixDesignParameter(Long id) {
    mixDesignParameterRepository.deleteById(id);
  }
}
