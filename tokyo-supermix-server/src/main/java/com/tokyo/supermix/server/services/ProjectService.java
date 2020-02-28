package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.Project;

public interface ProjectService {
  public void saveProject(Project project);

  public boolean isNameExist(String name);
  public List<Project> getAllProjects();
}
