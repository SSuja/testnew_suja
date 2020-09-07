package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Pour;
import com.tokyo.supermix.security.UserPrincipal;

public interface PourService {
  public Pour savePour(Pour pour);

  public List<Pour> getAllPour();

  public void deletePour(Long id);

  public Pour getPourById(Long id);

  public boolean isPourExit(Long id);

  public boolean isPourNameExistBYProject(String name, String projectCode);

  public boolean isUpdatedPourExists(Long id, String name, String projectCode);

  public Page<Pour> searchPour(Predicate predicate, int page, int size);

  public List<Pour> getPoursByPlantCode(String plantCode);

  public List<Pour> getAllPourByPlant(UserPrincipal currentUser, Pageable pageable);

  public Long getCountPour();

  public Long getCountPourByPlantCode(String plantCode);

  public List<Pour> getPoursByPlantCode(String plantCode, Pageable pageable);

  public List<Pour> getPourNameByPlantCode(String plantCode, String name);

  public List<Pour> getPourName(String name);
}
