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
	public MaterialTest getMaterialTestByCode(String code) {
		return materialTestRepository.findByCode(code);
	}

	@Transactional(readOnly = true)
	public List<MaterialTest> getAllMaterialTests() {
		return materialTestRepository.findAll();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteMaterialTest(String code) {
		materialTestRepository.deleteById(code);
	}

	@Transactional(readOnly = true)
	public boolean isMaterialTestExists(String code) {
		return materialTestRepository.existsByCode(code);
	}

	@Transactional(readOnly = true)
	public List<MaterialTest> getMaterialTestByStatus(String status) {
		return materialTestRepository.findByStatus(status);
	}

	@Transactional(readOnly = true)
	public List<MaterialTest> getMaterialTestByTest(Long testId) {
		return materialTestRepository.findByTest(testId);
	}

	@Transactional(readOnly = true)
	public boolean isMaterialTestStatusExists(String status) {
		return materialTestRepository.existsByStatus(status);
	}

	@Transactional(readOnly = true)
	public boolean isMaterialTestByTestExists(Long testId) {
		return materialTestRepository.existsByTest(testId);
	}

}
