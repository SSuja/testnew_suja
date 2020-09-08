package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Pour;
import com.tokyo.supermix.data.repositories.PourRepository;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class PourServiceImpl implements PourService {
  @Autowired
  private PourRepository pourRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;

  @Transactional()
  public Pour savePour(Pour pour) {
    return pourRepository.save(pour);
  }

  @Transactional()
  public List<Pour> getAllPour() {
    return pourRepository.findAll();
  }

  @Transactional()
  public void deletePour(Long id) {
    pourRepository.deleteById(id);
  }

  @Transactional
  public Pour getPourById(Long id) {
    return pourRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isPourExit(Long id) {
    return pourRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public boolean isPourNameExistBYProject(String name, String projectCode) {
    return pourRepository.existsByNameAndProjectCode(name, projectCode);
  }

  @Transactional(readOnly = true)
  public boolean isUpdatedPourExists(Long id, String name, String projectCode) {
    if (!getPourById(id).getName().equalsIgnoreCase(name)
        && isPourNameExistBYProject(name, projectCode)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public Page<Pour> searchPour(Predicate predicate, int page, int size) {
    return pourRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public List<Pour> getPoursByPlantCode(String plantCode) {
    return pourRepository.findByProjectPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<Pour> getAllPourByPlant(UserPrincipal currentUser, Pageable pageable) {
    return pourRepository.findByProjectPlantCodeIn(currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_POUR), pageable);
  }

  @Transactional(readOnly = true)
  public Long getCountPour() {
    return pourRepository.count();
  }

  @Transactional(readOnly = true)
  public Long getCountPourByPlantCode(String plantCode) {
    return pourRepository.countByProjectPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<Pour> getPoursByPlantCode(String plantCode, Pageable pageable) {
    return pourRepository.findAllByProjectPlantCode(plantCode, pageable);
  }

  @Transactional(readOnly = true)
  public List<Pour> getPourNameByPlantCode(String plantCode, String name) {
    if (name.isEmpty()) {
      return null;
    }
    return pourRepository.findByProjectPlantCodeAndNameStartsWith(plantCode, name);
  }

  @Transactional(readOnly = true)
  public List<Pour> getPourName(String name) {
    if (name.isEmpty()) {
      return null;
    }
    return pourRepository.findByNameStartsWith(name);
  }
}
