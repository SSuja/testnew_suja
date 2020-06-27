package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.security.UserPrincipal;

public interface ProjectService {
	public void saveProject(Project project);

	public boolean isNameExist(String name);

	public List<Project> getAllProjects(UserPrincipal currentUser);

	public boolean isProjectExist(String code);

	public void deleteProject(String code);

	public Project getProjectByCode(String code);

	public boolean isUpdatedProjectExist(String code, String name);

	public Page<Project> searchProject(Predicate predicate, int size, int page);

	public List<Project> getProjectByPlantCode(String plantCode);

}
