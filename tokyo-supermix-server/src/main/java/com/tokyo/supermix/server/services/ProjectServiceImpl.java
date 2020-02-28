package com.tokyo.supermix.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.data.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
  @Autowired
  private ProjectRepository projectRepository;

  @Override
  public void saveProject(Project project) {
    projectRepository.save(project);
  }

  @Override
  public boolean isNameExist(String name) {
    return projectRepository.existsByName(name);
  }
}
