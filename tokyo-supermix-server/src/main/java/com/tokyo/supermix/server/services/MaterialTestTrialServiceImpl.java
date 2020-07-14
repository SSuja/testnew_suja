package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TrailResult;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class MaterialTestTrialServiceImpl implements MaterialTestTrialService {
	@Autowired
	private MaterialTestTrialRepository materialTestTrialRepository;
	@Autowired
	private AcceptedValueRepository acceptedValueRepository;
	@Autowired
	private MaterialTestRepository materialTestRepository;
	@Autowired
	private MaterialAcceptedValueRepository materialAcceptedValueRepository;
	@Autowired
	private CurrentUserPermissionPlantService currentUserPermissionPlantService;
	@Autowired
	TestParameterRepository testParameterRepository;
	@Autowired
	ParameterResultRepository parameterResultRepository;
	@Autowired
	MaterialTestService materialTestService;

	@Transactional
	public String saveMaterialTestTrial(MaterialTestTrial materialTestTrial) {
		String codePrefix = materialTestTrial.getMaterialTest().getCode();
		String subPrefix = codePrefix + "-T-";
		List<MaterialTestTrial> materialTestTrialList = materialTestTrialRepository.findByCodeContaining(subPrefix);
		if (materialTestTrialList.size() == 0) {
			materialTestTrial.setCode(subPrefix + String.format("%04d", 1));
			materialTestTrial.setTrialNo(1l);
		} else {
			materialTestTrial.setCode(subPrefix + String.format("%04d", maxNumberFromCode(materialTestTrialList) + 1));
			materialTestTrial.setTrialNo(maxNumberFromCode(materialTestTrialList).longValue() + 1l);
		}
		materialTestTrialRepository.save(materialTestTrial);
		return materialTestTrial.getCode();
	}

	private Integer getNumberFromCode(String code) {
		String numberOnly = code.replaceAll("[^0-9]", "");
		return Integer.parseInt(numberOnly);
	}

	private Integer maxNumberFromCode(List<MaterialTestTrial> materialTestTrialList) {
		List<Integer> list = new ArrayList<Integer>();
		materialTestTrialList.forEach(obj -> {
			String code = obj.getCode();
			list.add(getNumberFromCode(code.substring(code.length() - code.indexOf("-"))));
		});
		return Collections.max(list);
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
		MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
		Long testConfigureId = materialTest.getTestConfigure().getId();
		if (materialTest.getIncomingSample().getRawMaterial().getMaterialSubCategory().getMaterialCategory().getName()
				.equalsIgnoreCase(Constants.ADMIXTURE)) {
			MaterialAcceptedValue materialAcceptedValue = materialAcceptedValueRepository
					.findByTestConfigureIdAndRawMaterialId(testConfigureId,
							materialTest.getIncomingSample().getRawMaterial().getId());
			calculateStatus(average, materialAcceptedValue.getMinValue(), materialAcceptedValue.getMaxValue(),
					materialTestCode);
		} else {
			calculateStatus(average, acceptedValueRepository.findByTestConfigureId(testConfigureId).getMinValue(),
					acceptedValueRepository.findByTestConfigureId(testConfigureId).getMaxValue(), materialTestCode);
		}
	}

	public void sieveavg(String materialTestCode) {
		Double result = 0.0;
		MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
		Long testConfigureId = materialTest.getTestConfigure().getId();
		TestParameter testParameter = testParameterRepository.findByTestConfigureIdAndTrailResult(testConfigureId,
				TrailResult.SUM);
		Double trailparsum = 0.0;

		List<ParameterResult> parameterResultlist = parameterResultRepository
				.findByTestParameterIdAndMaterialTestCode(testParameter.getId(), materialTest.getCode());
		for (int i = 0; i < parameterResultlist.size(); i++) {
			if (parameterResultlist.get(i).getMaterialTestTrial().getSieveSize().getSize() != 0.0) {
				trailparsum = trailparsum + parameterResultlist.get(i).getValue();
			}

		}
		HashMap<String, Double> sievemain = new HashMap<String, Double>();
		sievemain.put(testParameter.getAbbreviation(), trailparsum);
		result = findResult(sievemain, materialTest.getTestConfigure().getEquation().getFormula());
		materialTest.setAverage(result);
		compareWithAverage(result, materialTest.getCode());
		materialTestService.updateIncomingSampleStatusByIncomingSample(materialTest);
	}

	public double findResult(HashMap<String, Double> abb, String equation) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		double result = 0.0;
		for (String i : abb.keySet()) {
			engine.put(i, abb.get(i));
		}
		try {
			result = (double) engine.eval(equation);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
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
		return roundDoubleValue(totalResult / trialTotal);
	}

	private void calculateStatus(Double average, Double minValue, Double maxValue, String materialTestCode) {
		if (maxValue == null && minValue <= average || minValue == null && average <= maxValue) {
			updateAverage(average, materialTestCode, Status.PASS);
		} else if (minValue != null && minValue <= average && maxValue != null && average <= maxValue) {
			updateAverage(average, materialTestCode, Status.PASS);
		} else {
			updateAverage(average, materialTestCode, Status.FAIL);
		}
	}

	@Transactional
	public MaterialTest updateAverage(Double average, String code, Status status) {
		MaterialTest materialTest = materialTestRepository.findByCode(code);
		materialTest.setAverage(roundDoubleValue(average));
		materialTest.setStatus(status);
		materialTestRepository.save(materialTest);
		return materialTest;
	}

	private Double roundDoubleValue(Double value) {
		DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
		return Double.valueOf(decimalFormat.format(value));
	}

	@Transactional(readOnly = true)
	public List<MaterialTestTrial> getMaterialTestTrialByPlantCode(String plantCode) {
		return materialTestTrialRepository.findByMaterialTestIncomingSamplePlantCode(plantCode);
	}

	@Override
	public List<MaterialTestTrial> getAllMaterialTestTrialByplant(UserPrincipal currentUser) {
		return materialTestTrialRepository.findByMaterialTestIncomingSamplePlantCodeIn(currentUserPermissionPlantService
				.getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_MATERIAL_TEST_TRIAL));
	}
}
