package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.dto.MaterialParameterResultDto;
import com.tokyo.supermix.data.dto.ParameterResultDto;
import com.tokyo.supermix.data.entities.ParameterEquation;
import com.tokyo.supermix.data.entities.ParameterEquationElement;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationElementRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;

@Service
public class ParameterResultServiceImpl implements ParameterResultService {
	@Autowired
	private ParameterResultRepository parameterResultRepository;
	@Autowired
	private TestParameterService testParameterService;
	@Autowired
	MaterialQualityParameterRepository materialQualityParameterRepository;
	@Autowired
	MaterialTestTrialRepository materialTestTrialRepository;
	@Autowired
	MaterialTestRepository materialTestRepository;
	@Autowired
	TestParameterRepository testParameterRepository;
	@Autowired
	ParameterEquationRepository parameterEquationRepository;
	@Autowired
	ParameterEquationElementRepository parameterEquationElementRepository;

	@Transactional
	public void saveParameterValue(ParameterResult parameterValue) {
		parameterResultRepository.save(parameterValue);
	}

	@Transactional(readOnly = true)
	public List<ParameterResult> getAllParameterResults() {
		return parameterResultRepository.findAll();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteParameterResult(Long id) {
		parameterResultRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public ParameterResult getParameterResultById(Long id) {
		return parameterResultRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public boolean isParameterResultExist(Long id) {
		return parameterResultRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public List<ParameterResult> findByMaterialTestTrialCode(String materialTestTrialCode) {
		return parameterResultRepository.findByMaterialTestTrialCode(materialTestTrialCode);
	}

	// private Double roundDoubleValue(Double value) {
	// DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
	// return Double.valueOf(decimalFormat.format(value));
	// }

	public void isTestParameterValueInConfigLevel(ParameterResult parameterResult) {
		if (testParameterService.getTestParameterById(parameterResult.getTestParameter().getId()) != null) {
			if (testParameterService.getTestParameterById(parameterResult.getTestParameter().getId()).getEntryLevel()
					.equals(TestParameterType.CONFIGURE)) {
				parameterResult.setValue(testParameterService
						.getTestParameterById(parameterResult.getTestParameter().getId()).getValue());
			}
		}
	}

	@Transactional(readOnly = true)
	public List<ParameterResult> findParameterResultByPlantCode(String plantCode) {
		return parameterResultRepository.findByMaterialTestTrialMaterialTestIncomingSamplePlantCode(plantCode);
	}

	@Transactional(readOnly = true)
	public List<ParameterResult> getParameterResultWithConfigValue(String materialTestTrialCode,
			String materialTestCode) {
		List<ParameterResult> paramterResultList = parameterResultRepository
				.findByMaterialTestTrialCodeAndMaterialTestCode(materialTestTrialCode, materialTestCode);
		for (ParameterResult parameterResult : paramterResultList) {
			isTestParameterValueInConfigLevel(parameterResult);
		}
		return paramterResultList;
	}

	public List<ParameterResult> findByMaterialTestCode(String materialTestCode) {
		return parameterResultRepository.findByMaterialTestCode(materialTestCode);
	}

	@Transactional(readOnly = true)
	public List<ParameterResult> getTestParamWithEquationByTestTrial(String materialTestTrialCode) {
		return parameterResultRepository
				.findByMaterialTestTrialCodeAndTestParameterEquationExistsTrue(materialTestTrialCode);
	}

	// set values to equation less parameters
	public void setParameterResults(MaterialParameterResultDto materialParameterResultDto) {
		ArrayList<ParameterResult> parameterResultList = new ArrayList<ParameterResult>();
		HashMap<Long, Double> map = new HashMap<>();
		for (ParameterResultDto parameterResultDto : materialParameterResultDto.parameterResults) {
			ParameterResult parameterResult = new ParameterResult();
			parameterResult.setMaterialTest(
					materialTestRepository.findByCode(materialParameterResultDto.getMaterialTestCode()));
			parameterResult.setMaterialTestTrial(
					materialTestTrialRepository.findByCode(materialParameterResultDto.getMaterialTestTrialCode()));
			map.put(parameterResultDto.getTestParameterId(), parameterResultDto.getValue());
			parameterResult
					.setTestParameter(testParameterRepository.findById(parameterResultDto.getTestParameterId()).get());
			parameterResult.setValue(parameterResultDto.getValue());
			parameterResultList.add(parameterResult);
			parameterResultRepository.save(parameterResult);
		}
		setParameterResultWhenEquationExist(parameterResultList, materialParameterResultDto.getMaterialTestTrialCode());

	}

	public String getEquation(Long paramterEquationId) {
		return parameterEquationRepository.findById(paramterEquationId).get().getEquation().getFormula();
	}

	public void setParameterResultWhenEquationExist(List<ParameterResult> parameterResultList,
			String materialTestTrialCode,HashMap<Long, Double> map) {
		double result = 0;
		// setParameterResults(materialParameterResultDto);
//		HashMap<Long, Double> map = new HashMap<>();
		parameterResultList= getTestParamWithEquationByTestTrial(
				materialTestTrialCode);
		for (ParameterResult parameterResult : parameterResultList) {
			ParameterEquation parameterEquation = parameterEquationRepository
					.findByTestParameterId(parameterResult.getTestParameter().getId());
			List<ParameterEquationElement> parameterEquationElementList = parameterEquationElementRepository
					.findByParameterEquationId(parameterEquation.getId());
			for (ParameterEquationElement parameterEquationElement : parameterEquationElementList) {
				if (parameterEquationElement.getParameterEquation().getEquation().getName()
						.equalsIgnoreCase("Equation")) {
					result = findResult(,
							getEquation(parameterEquation.getId()));
					System.out.println("brgvyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyye"+result);
					System.out.println("valuryguygvsuuuuuuuuuuuuuuus" + parameterResult.getValue());
//					// parameterResult.setValue(result);
				}
			}
		}
	}

	public String getAbbrevation(ParameterResult parameterResult) {
		TestParameter testParameter = testParameterRepository.findById(parameterResult.getTestParameter().getId())
				.get();
		return testParameter.getAbbreviation();
	}

	public double findResult(List<ParameterResult> parameterResultList, String equation) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		double result = 0;
		for (ParameterResult parameterResult : parameterResultList) {
			TestParameter testParameter = testParameterRepository.findById(parameterResult.getTestParameter().getId())
					.get();
			engine.put(abbreviation, value);
		}
		try {
			result = (double) engine.eval(equation);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
	}
}
