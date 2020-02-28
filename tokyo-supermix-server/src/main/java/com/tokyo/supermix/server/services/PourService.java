package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.Pour;

public interface PourService {
  public Pour savePour(Pour pour);

  public List<Pour> getAllPour();

  public void deletePour(Long id);

  public Pour getPourById(Long id);

  public boolean isPourExit(Long id);
}
