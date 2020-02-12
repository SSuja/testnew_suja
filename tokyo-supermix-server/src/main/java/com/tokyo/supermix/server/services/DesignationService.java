package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.Designation;

public interface DesignationService {


	//check creating designation s already exists or not
	public boolean isDesignationAlreadyExist(String designation);

	//list all the designation
	public List<Designation> getAllDesignations();
	
		
}