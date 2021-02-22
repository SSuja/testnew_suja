package com.tokyo.supermix.server.services;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.DesignationDto;
import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.entities.QDesignation;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.DesignationRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

@Service
public class DesignationServiceImpl implements DesignationService {

  @Autowired
  private DesignationRepository designationRepository;

  @Autowired
  private Mapper mapper;

  @Transactional(readOnly = true)
  public List<Designation> getAllDesignations() {
    return designationRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isDesignationExist(Long id) {
    return designationRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public Designation getDesignationById(Long id) {
    return designationRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteDesignation(Long id) {
    designationRepository.deleteById(id);
  }

  @Transactional
  public void saveDesignation(Designation designation) {
    designationRepository.save(designation);
  }

  @Transactional(readOnly = true)
  public boolean isDesignationExist(String designation) {
    return designationRepository.existsByName(designation);

  }

  public boolean isUpdatedDesignationNameExist(Long id, String designationName) {
    if ((!getDesignationById(id).getName().equalsIgnoreCase(designationName))
        && (isDesignationExist(designationName))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public Long getCountDesignation() {
    return designationRepository.count();
  }

  @Transactional(readOnly = true)
  public List<Designation> getAllDesignation(Pageable pageable) {
    return designationRepository.findAll(pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<DesignationDto> searchDesignation(BooleanBuilder booleanBuilder, String name,
      String description, Pageable pageable, Pagination pagination) {
    if (name != null && !name.isEmpty()) {
      booleanBuilder.and(QDesignation.designation.name.contains(name));
    }
    if (description != null && !description.isEmpty()) {
      booleanBuilder.and(QDesignation.designation.name.contains(description));
    }
    pagination.setTotalRecords(
        ((Collection<Designation>) designationRepository.findAll(booleanBuilder)).stream().count());
    return mapper.map(designationRepository.findAll(booleanBuilder, pageable).toList(),
        DesignationDto.class);
  }
}
