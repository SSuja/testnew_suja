package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.FinishProductParameterResult;
import com.tokyo.supermix.data.repositories.FinishProductParameterResultRepository;

@Service
public class FinishProductParameterResultServiceImpl
    implements FinishProductParameterResultService {
  @Autowired
  private FinishProductParameterResultRepository finishProductParameterResultRepository;

  @Transactional(readOnly = true)
  public List<FinishProductParameterResult> getallFinishProductParameterResults() {
    return finishProductParameterResultRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<FinishProductParameterResult> getFinishProductResultsByFinishProductTestCode(String finishProductTestCode) {
    return finishProductParameterResultRepository
        .findByFinishProductTestCodeOrderByUpdatedAtDesc(finishProductTestCode);
  }
}
