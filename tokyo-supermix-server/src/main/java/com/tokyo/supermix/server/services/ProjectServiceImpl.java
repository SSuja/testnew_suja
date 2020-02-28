package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.data.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
  @Autowired
  private ProjectRepository projectRepository;

  @Transactional(readOnly = true)
  public boolean isNameExist(String name) {
    return projectRepository.existsByName(name);
  }

  @Transactional(readOnly = true)
  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }

  @Transactional
  public void saveProject(Project project) {
    projectRepository.save(project);
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

  public boolean isUpdatedProjectExist(String code, String name) {
    if ((!getProjectByCode(code).getName().equalsIgnoreCase(name)) && (isNameExist(name))) {
      return true;
    }
    return false;
  }
}
