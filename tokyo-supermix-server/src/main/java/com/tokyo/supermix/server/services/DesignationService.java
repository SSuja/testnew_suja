package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.entities.Designation;

public interface DesignationService {

	public boolean isDesignationExist(Long id);

	public void deleteDesignation(Long id);

	public void createDesignation(Designation designation);

	public boolean isDesignationExist(String designation);

}
