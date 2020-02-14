package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.Designation;

public interface DesignationService {
	
	public boolean isDesignationExist(Long id);
	
	public List<Designation> getAllDesignations();

}
