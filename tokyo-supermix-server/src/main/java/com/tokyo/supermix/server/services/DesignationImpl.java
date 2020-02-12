package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.repositories.DesignationRepository;

@Service
public class DesignationImpl implements DesignationService {

	@Autowired
	private DesignationRepository designationRepository;

	//check is designation already exists 
	@Transactional(readOnly = true)
	public boolean isDesignationAlreadyExist(String designation) {
		return designationRepository.existsByName(designation);
	}

	//get all designation list
	@Transactional
	public List<Designation> getAllDesignations() {
		return designationRepository.findAll();
	}




}
