package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.ConcreteMixer;
import com.tokyo.supermix.data.repositories.ConcreteMixerRepository;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class ConcreteMixerServiceImpl implements ConcreteMixerService {
  @Autowired
  private ConcreteMixerRepository concreteMixerRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;

  @Transactional
  public void saveConcreteMixer(ConcreteMixer concreteMixer) {
    concreteMixerRepository.save(concreteMixer);
  }

  @Transactional(readOnly = true)
  public List<ConcreteMixer> getAllConcreteMixers() {
    return concreteMixerRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteConcreteMixer(Long id) {
    concreteMixerRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isConcreteMixerExist(Long id) {
    return concreteMixerRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public ConcreteMixer getConcreteMixerById(Long id) {
    return concreteMixerRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public List<ConcreteMixer> findByPlantCode(String plantCode) {
    return concreteMixerRepository.findByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateEntryExist(String name, String plantCode) {
    if (concreteMixerRepository.existsByNameAndPlantCode(name, plantCode)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isNameNull(String name) {
    if (name != null && !name.isEmpty()) {
      return false;
    }
    return true;
  }

  @Transactional(readOnly = true)
  public Page<ConcreteMixer> searchConcreteMixer(Predicate predicate, int page, int size) {
    return concreteMixerRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }
  @Transactional(readOnly = true)
  public List<ConcreteMixer> getAllConcreteMixersByPlant(UserPrincipal currentUser) {
    return concreteMixerRepository.findByPlantCodeIn(currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_CONCRETE_MIXER));
  }

}
