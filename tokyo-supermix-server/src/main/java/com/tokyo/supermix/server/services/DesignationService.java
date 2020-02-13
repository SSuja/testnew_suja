package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.entities.Designation;

public interface DesignationService {

	// check designation already exists or not
	public boolean isDesignationAlreadyExist(String designation);

	// check designation is already exists or not
	public boolean isDesignationIdAlreadyExist(Long id);

	// get designation by id
	public Designation getDesignationById(Long id);

}
