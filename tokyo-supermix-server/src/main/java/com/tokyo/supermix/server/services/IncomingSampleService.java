package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.enums.Status;

public interface IncomingSampleService {
  public void saveIncomingSample(IncomingSample incomingSample);
  public void deleteIncomingSample(String code);
  public IncomingSample getIncomingSampleById(String code);
  public List<IncomingSample> getAllIncomingSamples();
  boolean isIncomingSampleExist(String code);
  public void updateStatusForIncomingSample(String code ,Status status);
  boolean isIncomingSampleStatusExist(Status status);
  public IncomingSample getIncomingSampleByStatus(Status status);
}
