package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.data.repositories.ProjectRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class ProjectServiceImpl implements ProjectService {
  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private EmailNotification emailNotification;

  @Transactional(readOnly = true)
  public List<Project> getAllProjectsByPlant(UserPrincipal currentUser) {
    return projectRepository.findByPlantCodeIn(currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_PROJECT));
  }

  @Transactional
  public void saveProject(Project project) {
    if (project.getCode() == null) {
      String codePrefix = project.getPlant().getCode() + "-PRJ-";
      List<Project> projectList = projectRepository.findByCodeContaining(codePrefix);
      if (projectList.size() == 0) {
        project.setCode(codePrefix + String.format("%03d", 1));
      } else {
        project.setCode(codePrefix + String.format("%03d", maxNumberFromCode(projectList) + 1));
      }
    }
    Project projectObj = projectRepository.save(project);
    if (projectObj != null)
      emailNotification.sendProjectEmail(projectObj);
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<Project> projectList) {
    List<Integer> list = new ArrayList<Integer>();
    projectList.forEach(obj -> {
      String code = obj.getCode();
      list.add(getNumberFromCode(code.substring(code.length() - code.indexOf("-"))));
    });
    return Collections.max(list);
  }

  @Transactional(readOnly = true)
  public Project getProjectByCode(String code) {
    return projectRepository.findById(code).get();
  }

  @Transactional(readOnly = true)
  public boolean isProjectExist(String code) {
    return projectRepository.existsById(code);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteProject(String code) {
    projectRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public Page<Project> searchProject(Predicate predicate, int size, int page) {
    return projectRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code")));
  }

  @Transactional(readOnly = true)
  public List<Project> getProjectByPlantCode(String plantCode) {
    return projectRepository.findByPlantCode(plantCode);
  }


  @Transactional(readOnly = true)
  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<Project> getAllProjectsByCustomer(Long customerId) {
    return projectRepository.findByCustomerId(customerId);
  }

  @Transactional(readOnly = true)
  public List<Project> getAllProjectsByCustomerAndPlant(Long customerId, String plantCode) {
    return projectRepository.findByCustomerIdAndPlantCode(customerId, plantCode);
  }

  @Transactional(readOnly = true)
  public boolean isCustomerExistsByProject(Long customerId) {
    return projectRepository.existsByCustomerId(customerId);
  }

  @Transactional(readOnly = true)
  public boolean isNameAndCustomerIdAndProjectExist(String name, Long customerId, String plantCode) {
    return projectRepository.existsByNameAndCustomerIdAndPlantCode(name, customerId, plantCode);
  }
}
