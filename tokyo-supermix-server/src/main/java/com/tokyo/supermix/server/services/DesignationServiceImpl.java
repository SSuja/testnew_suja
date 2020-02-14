package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.repositories.DesignationRepository;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	private DesignationRepository designationRepository;

	@Transactional(readOnly = true)
	public boolean isDesignationExist(Long id) {
		return designationRepository.existsById(id);
	}

	@Transactional
	public List<Designation> getAllDesignations() {
		return designationRepository.findAll();
	}

}
