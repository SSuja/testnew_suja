package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.Project;

public interface ProjectService {
  public void saveProject(Project project);

  public boolean isNameExist(String name);

  public List<Project> getAllProjects();

  public boolean isProjectExist(String code);

  public void deleteProject(String code);

  public Project getProjectByCode(String code);

  public boolean isUpdatedProjectExist(String code, String name);

}
