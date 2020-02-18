package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.repositories.DesignationRepository;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	private DesignationRepository designationRepository;

	@Transactional
	public List<Designation> getAllDesignations() {
		return designationRepository.findAll();
	}

	@Transactional(readOnly = true)
	public boolean isDesignationExist(Long id) {
		return designationRepository.existsById(id);
	}

	@Transactional()
	public Designation getDesignationById(Long id) {
		return designationRepository.findById(id).get();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteDesignation(Long id) {
		designationRepository.deleteById(id);
	}

	@Transactional
	public void createDesignation(Designation designation) {
		designationRepository.save(designation);
	}

	@Transactional(readOnly = true)
	public boolean isDesignationExist(String designation) {
		return designationRepository.existsByName(designation);

	}

	@Transactional
	public Designation updateDesignation(Designation designation) {
		return designationRepository.save(designation);
	}

}
