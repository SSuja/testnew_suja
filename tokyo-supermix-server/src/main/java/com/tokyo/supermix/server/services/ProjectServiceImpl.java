package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(readOnly = true)
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}
}
