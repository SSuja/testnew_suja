package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.dto.MaterialParameterResultDto;
import com.tokyo.supermix.data.dto.ParameterResultDto;
import com.tokyo.supermix.data.entities.ParameterEquation;
import com.tokyo.supermix.data.entities.ParameterEquationElement;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;

@Service
public class ParameterResultServiceImpl implements ParameterResultService {
	@Autowired
	private ParameterResultRepository parameterResultRepository;
	@Autowired
	private TestParameterService testParameterService;
	@Autowired
	MaterialQualityParameterRepository materialQualityParameterRepository;

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
//	public String setParameterResults(MaterialParameterResultDto materialParameterResultDto) {
//		List<ParameterResult> parameterResults = new ArrayList();
//		HashMap<Long, Double> map = new HashMap<>();
//		for (ParameterResult parameterResult : parameterResults) {
//			ParameterResult parameterResult = new ParameterResult();
//			parameterResults.setMaterialTestTrial(
//					findByMaterialTestTrialCode(materialParameterResultDto.getMaterialTestTrialCode()));
//			parameterResults.setMaterialTest(findByMaterialTestCode(materialParameterResultDto.getMaterialTestCode()));
//			parameterResults.setParameter(parameterResultDto.getTestParameterId());
//			parameterResult.ssetValue(parameterResultDto.getValue());
//		}
//		return null;
//	}

//	public String setParameterResultWhenEquationExist(List<ParameterResult> paramResult, String materialTestTrialCode) {
//		List<ParameterResult> paramEquationResults = getTestParamWithEquationByTestTrial(materialTestTrialCode);
//		for (ParameterResult parameterResult : paramEquationResults) {
//		}
//		return null;
//	}

//	double findResult(String abbreviation, double value, String equation) {
//		ScriptEngineManager mgr = new ScriptEngineManager();
//		ScriptEngine engine = mgr.getEngineByName("JavaScript");
//		 double result = 0;
//		ParameterEquationElement parameterEquationElement = new ParameterEquationElement();
//		for (ParameterResult parameterResult ) {
//		engine.put(parameterEquationElement.getTestParameter().getAbbreviation(), parameterRes);
//		result = (double) engine.eval(equation);
//		}
//		return result;
//	}
}
