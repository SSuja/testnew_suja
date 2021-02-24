package com.tokyo.supermix.server.services;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.MaterialStateDto;
import com.tokyo.supermix.data.entities.MaterialState;
import com.tokyo.supermix.data.entities.QMaterialState;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.MaterialStateRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

@Service
public class MaterialStateServiceImpl implements MaterialStateService {

  @Autowired
  private MaterialStateRepository materialStateRepository;

  @Autowired
  private Mapper mapper;

  @Transactional
  public MaterialState saveMaterialState(MaterialState materialState) {
    return materialStateRepository.save(materialState);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialStateExist(String materialState) {
    return materialStateRepository.existsByMaterialState(materialState);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialStateExist(Long id) {
    return materialStateRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<MaterialState> getAllMaterialStates() {
    return materialStateRepository.findAll();
  }

  @Transactional(readOnly = true)
  public MaterialState getMaterialStateById(Long id) {
    return materialStateRepository.findById(id).get();
  }

  public boolean isUpdatedMaterialStateExist(Long id, String materialState) {
    if ((!getMaterialStateById(id).getMaterialState().equalsIgnoreCase(materialState))
        && (isMaterialStateExist(materialState))) {
      return true;
    }
    return false;
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMaterialState(Long id) {
    materialStateRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<MaterialState> getAllMaterialState(Pageable pageable) {
    return materialStateRepository.findAllByOrderByUpdatedAtDesc(pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<MaterialStateDto> searchMaterialState(BooleanBuilder booleanBuilder,
      String materialState, Pageable pageable, Pagination pagination) {
    if (materialState != null && !materialState.isEmpty()) {
      booleanBuilder.and(QMaterialState.materialState1.materialState.contains(materialState));
    }
    pagination.setTotalRecords(
        ((Collection<MaterialState>) materialStateRepository.findAll(booleanBuilder)).stream()
            .count());
    return mapper.map(materialStateRepository.findAll(booleanBuilder, pageable).toList(),
        MaterialStateDto.class);
  }

  @Override
  public Long getCountMaterialState() {
    return materialStateRepository.count();
  }

}
