package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;

@Service
public class MaterialTestTrialServiceImpl implements MaterialTestTrialService {
	@Autowired
	private MaterialTestTrialRepository materialTestTrialRepository;
	@Autowired
	private AcceptedValueRepository acceptedValueRepository;
	@Autowired
	private MaterialTestRepository materialTestRepository;

	@Transactional
	public MaterialTestTrial saveMaterialTestTrial(MaterialTestTrial materialTestTrial) {
		return materialTestTrialRepository.save(materialTestTrial);
	}

	@Transactional(readOnly = true)
	public List<MaterialTestTrial> getAllMaterialTestTrial() {
		return materialTestTrialRepository.findAll();
	}

	@Transactional(readOnly = true)
	public MaterialTestTrial getMaterialTestTrialByCode(String code) {
		return materialTestTrialRepository.findByCode(code);
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteMaterialTestTrial(String code) {
		materialTestTrialRepository.deleteById(code);
	}

	@Transactional(readOnly = true)
	public boolean isMaterialTestTrialExits(String code) {
		return materialTestTrialRepository.existsByCode(code);
	}

	@Transactional(readOnly = true)
	public List<MaterialTestTrial> getMaterialTestTrialByMaterialTestCode(String materialTestCode) {
		return materialTestTrialRepository.findByMaterialTestCode(materialTestCode);
	}

	@Transactional(readOnly = true)
	public boolean isMaterialTestIdExits(String materialTestCode) {
		return materialTestTrialRepository.existsByMaterialTestCode(materialTestCode);
	}

	@Transactional
	public void getAverageAndStatus(String materialTestCode) {
		compareWithAverage(calculateAverage(materialTestCode), materialTestCode);

	}

	private void compareWithAverage(Double average, String materialTestCode) {
		Long testId = materialTestRepository.findByCode(materialTestCode).getTest().getId();
		if (acceptedValueRepository.findByTestId(testId).getMinValue() <= average
				&& acceptedValueRepository.findByTestId(testId).getMaxValue() >= average) {
			updateAverage(average, materialTestCode, Status.PASS);
		} else {
			updateAverage(average, materialTestCode, Status.FAIL);

		}
	}

	private Double calculateAverage(String materialTestCode) {
		Double totalResult = 0.0;
		int trialTotal = 0;
		List<MaterialTestTrial> listMaterialTestTrial = materialTestTrialRepository
				.findByMaterialTestCode(materialTestCode);
		for (MaterialTestTrial materialTestTrial : listMaterialTestTrial) {
			totalResult = totalResult + materialTestTrial.getResult();
			trialTotal = listMaterialTestTrial.size();
		}
		return (totalResult / trialTotal);
	}

	@Transactional
	public MaterialTest updateAverage(Double average, String code, Status status) {
		MaterialTest materialTest = materialTestRepository.findByCode(code);
		materialTest.setAverage(average);
		materialTest.setStatus(status);
		return materialTestRepository.save(materialTest);
	}
}