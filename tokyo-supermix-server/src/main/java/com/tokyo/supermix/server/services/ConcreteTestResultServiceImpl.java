package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.ConcreteTestResult;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.ConcreteTestResultRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.MixDesignProportionRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class ConcreteTestResultServiceImpl implements ConcreteTestResultService {

	@Autowired
	private ConcreteTestResultRepository concreteTestResultRepository;
	@Autowired
	private MixDesignProportionRepository mixDesignProportionRepository;
	@Autowired
	private FinishProductSampleRepository finishProductSampleRepository;

	@Transactional
	public ConcreteTestResult saveConcreteTestResult(ConcreteTestResult concreteTestResult) {
		return concreteTestResultRepository.save(calculateRatio(concreteTestResult));
	}

	@Transactional(readOnly = true)
	public List<ConcreteTestResult> getAllConcreteTestResults() {
		return concreteTestResultRepository.findAll();
	}

	@Transactional(readOnly = true)
	public ConcreteTestResult getConcreteTestResultById(Long id) {
		return concreteTestResultRepository.findById(id).get();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteConcreteTestResult(Long id) {
		concreteTestResultRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public boolean isConcreteTestResultExists(Long id) {
		return concreteTestResultRepository.existsById(id);
	}

	public ConcreteTestResult calculateRatio(ConcreteTestResult concreteTestResult) {
		FinishProductSample finishProductSample = finishProductSampleRepository
				.findById(concreteTestResult.getFinishProductSample().getId()).get();
		List<MixDesignProportion> mixDesignProportionList = mixDesignProportionRepository
				.findByMixDesignCode(finishProductSample.getMixDesign().getCode());
		Long quantity = null;
		Long binderquantity = 0L;
		for (MixDesignProportion mixDesignProportion : mixDesignProportionList) {
			if (mixDesignProportion.getRawMaterial().getName().equalsIgnoreCase(Constants.RAW_MATERIAL_CEMENT)) {
				quantity = mixDesignProportion.getQuantity();
				concreteTestResult.setWaterCementRatio(
						calculateWaterCementRatio(concreteTestResult.getWaterContent(), quantity.doubleValue()));
			}
			if (mixDesignProportion.getRawMaterial().getName().equalsIgnoreCase(Constants.RAW_MATERIAL_FLYASH)) {
				binderquantity = mixDesignProportion.getQuantity();
			}
		}
		concreteTestResult.setWaterBinderRatio(calculateWaterBinderRatio(concreteTestResult.getWaterContent(),
				binderquantity.doubleValue(), quantity.doubleValue()));
		concreteTestResult.setSlumpGradeRatio(calculateSlumpGradeRatio(
				concreteTestResult.getFinishProductSample().getId(), concreteTestResult.getSlump()));
		concreteTestResult.setStatus(calculateConcreteStatus(concreteTestResult.getFinishProductSample().getId(),
				concreteTestResult.getSlump()));
		return concreteTestResult;
	}

	private Status calculateConcreteStatus(Long finishProductSampleId, Double slump) {
		Double targetSlump = finishProductSampleRepository.findById(finishProductSampleId).get().getMixDesign()
				.getTargetSlump();
		if (targetSlump - 25 <= slump && slump <= targetSlump + 25) {
			return Status.PASS;
		} else {
			return Status.FAIL;
		}
	}

	private Double calculateWaterCementRatio(Double waterContent, Double cementQuantity) {
		return roundDoubleValue(100 * waterContent / cementQuantity);
	}

	private Double calculateWaterBinderRatio(Double waterContent, Double binderquantity, Double quantity) {
		return roundDoubleValue(100 * waterContent / (quantity + binderquantity));
	}

	private Double calculateSlumpGradeRatio(Long finishProductSampleId, Double slump) {
		Double targetSlump = finishProductSampleRepository.findById(finishProductSampleId).get().getMixDesign()
				.getTargetSlump();
		return roundDoubleValue(slump / targetSlump);
	}

	private Double roundDoubleValue(Double value) {
		DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
		return Double.valueOf(decimalFormat.format(value));
	}
}
