package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;

@Service
public class MaterialTestServiceImpl implements MaterialTestService {

	@Autowired
	private MaterialTestRepository materialTestRepository;

	@Transactional
	public void saveMaterialTest(MaterialTest materialTest) {
		materialTestRepository.save(materialTest);
	}

	@Transactional(readOnly = true)
	public MaterialTest getMaterialTestById(Long id) {
		return materialTestRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public List<MaterialTest> getAllMaterialTests() {
		return materialTestRepository.findAll();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteMaterialTest(Long id) {
		materialTestRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public boolean isMaterialTestExists(Long id) {
		return materialTestRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public List<MaterialTest> getMaterialTestByStatus(String status) {
		return materialTestRepository.findByStatus(status);
	}

	@Override
	public List<MaterialTest> getMaterialTestByTest(Long testId) {
		
		return null;
	}

	
}
