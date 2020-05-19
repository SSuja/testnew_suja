package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.ConcreteTestResult;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.entities.QConcreteTestResult;
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

	@Transactional(readOnly = true)
	public Page<ConcreteTestResult> searchConcreteTestResult(Long finishProductSampleId, Long ConcreteTestId,
			Status status, Double result, Double resultMin, Double resultMax, Double strenghGradeRatio,
			Double strenghGradeRatioMin, Double strenghGradeRatioMax, Double slump, Double slumpMin, Double slumpMax,
			Double slumpGradeRatio, Double slumpGradeRatioMin, Double slumpGradeRatioMax, BooleanBuilder booleanBuilder,
			int page, int size) {
		if (finishProductSampleId != null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.id.eq(finishProductSampleId));
		}
		if (ConcreteTestId != null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.id.eq(ConcreteTestId));
		}
		if (status != null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.status.eq(status));
		}
		if (resultMax != null && resultMax != 0 && resultMin == null && result == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.result.lt(resultMax));
		}
		if (resultMin != null && resultMin != 0 && resultMax == null && result == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.result.gt(resultMin));
		}

		if (resultMin != null && resultMin != 0 && resultMax != null && resultMax != null && result == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.result.between(resultMin, resultMax));
		}
		if (result != null && result != 0 && resultMax == null && resultMin == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.result.eq(result));
		}
		if (strenghGradeRatioMax != null && strenghGradeRatioMax != 0 && strenghGradeRatioMin == null
				&& strenghGradeRatio == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.strengthGradeRatio.lt(strenghGradeRatioMax));
		}
		if (strenghGradeRatioMin != null && strenghGradeRatioMin != 0 && strenghGradeRatioMax == null
				&& strenghGradeRatio == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.strengthGradeRatio.gt(strenghGradeRatioMin));
		}

		if (strenghGradeRatioMin != null && strenghGradeRatioMin != 0 && strenghGradeRatioMax != null
				&& strenghGradeRatioMax != null && strenghGradeRatio == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.strengthGradeRatio.between(strenghGradeRatioMin,
					strenghGradeRatioMax));
		}
		if (strenghGradeRatio != null && strenghGradeRatio != 0 && strenghGradeRatioMax == null
				&& strenghGradeRatioMin == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.strengthGradeRatio.eq(strenghGradeRatio));
		}

		if (slumpMax != null && slumpMax != 0 && slumpMin == null && slump == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.slump.lt(slumpMax));
		}
		if (slumpMin != null && slumpMin != 0 && slumpMax == null && slump == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.slump.gt(slumpMin));
		}

		if (slumpMin != null && slumpMin != 0 && slumpMax != null && slumpMax != null && slump == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.slump.between(slumpMin, slumpMax));
		}
		if (slump != null && slump != 0 && slumpMax == null && slumpMin == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.slump.eq(slump));
		}

		if (slumpGradeRatioMax != null && slumpGradeRatioMax != 0 && slumpGradeRatioMin == null
				&& slumpGradeRatio == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.slumpGradeRatio.lt(slumpGradeRatioMax));
		}
		if (slumpGradeRatioMin != null && slumpGradeRatioMin != 0 && slumpGradeRatioMax == null
				&& slumpGradeRatio == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.slumpGradeRatio.gt(slumpGradeRatioMin));
		}

		if (slumpGradeRatioMin != null && slumpGradeRatioMin != 0 && slumpGradeRatioMax != null
				&& slumpGradeRatioMax != null && slumpGradeRatio == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.slumpGradeRatio.between(slumpGradeRatioMin,
					slumpGradeRatioMax));
		}
		if (slumpGradeRatio != null && slumpGradeRatio != 0 && slumpGradeRatioMax == null
				&& slumpGradeRatioMin == null) {
			booleanBuilder.and(QConcreteTestResult.concreteTestResult.slumpGradeRatio.eq(slumpGradeRatio));
		}

		return concreteTestResultRepository.findAll(booleanBuilder,
				PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
	}
}
