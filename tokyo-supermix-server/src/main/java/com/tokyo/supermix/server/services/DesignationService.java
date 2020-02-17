package com.tokyo.supermix.server.services;


import java.util.List;

import com.tokyo.supermix.data.entities.Designation;

public interface DesignationService {
	
	public List<Designation> getAllDesignations();

	public boolean isDesignationExist(Long id);

	public Designation getDesignationById(Long id);

	public void deleteDesignation(Long id);

	public void createDesignation(Designation designation);

	public boolean isDesignationExist(String designation);


}
