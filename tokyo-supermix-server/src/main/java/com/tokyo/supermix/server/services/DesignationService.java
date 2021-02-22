package com.tokyo.supermix.server.services;


import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.DesignationDto;
import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

public interface DesignationService {

  public List<Designation> getAllDesignations();

  public boolean isDesignationExist(Long id);

  public Designation getDesignationById(Long id);

  public void deleteDesignation(Long id);

  public void saveDesignation(Designation designation);

  public boolean isDesignationExist(String designation);

  public boolean isUpdatedDesignationNameExist(Long id, String designationName);

  public Long getCountDesignation();

  public List<Designation> getAllDesignation(Pageable pageable);

  public List<DesignationDto> searchDesignation(BooleanBuilder booleanBuilder, String name,
      String description, Pageable pageable, Pagination pagination);

}
