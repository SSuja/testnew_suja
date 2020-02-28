package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.entities.Project;

public interface ProjectService {
  public void saveProject(Project project);

  public boolean isNameExist(String name);
}
