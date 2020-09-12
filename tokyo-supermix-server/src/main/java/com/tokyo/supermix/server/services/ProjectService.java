package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.ProjectResponseDto;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;

public interface ProjectService {
  public void saveProject(Project project);

  public List<Project> getAllProjects();

  public List<Project> getAllProjectsByPlant(UserPrincipal currentUser, Pageable pageable);

  public boolean isProjectExist(String code);

  public void deleteProject(String code);

  public Project getProjectByCode(String code);

  public Page<Project> searchProject(Predicate predicate, int size, int page);

  public List<Project> getProjectByPlantCode(String plantCode);

  public List<Project> getAllProjectsByCustomer(Long customerId);

  public boolean isCustomerExistsByProject(Long customerId);

  public List<Project> getAllProjectsByCustomerAndPlant(Long customerId, String plantCode);

  public boolean isNameAndCustomerIdAndProjectExist(String name, Long customerId, String plantCode);

  public Long getCountProject();

  public Long getCountProjectByPlantCode(String plantCode);

  public List<Project> getProjectByPlantCode(String plantCode, Pageable pageable);

  public List<Project> getProjectNameByPlantCode(String plantCode, String name);

  public List<Project> getProjectName(String name);

  public List<ProjectResponseDto> searchProject(BooleanBuilder booleanBuilder, String code, String plantName,
      String name, String customerName, String contactPerson, String startDate, String plantCode,
      Pageable pageable, Pagination pagination, String contactNumber);
}
