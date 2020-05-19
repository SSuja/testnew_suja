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
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.ConcreteTestResult;
import com.tokyo.supermix.data.entities.CubeTestFinding;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.ConcreteTestResultRepository;
import com.tokyo.supermix.data.repositories.CubeTestFindingRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;

@Service
public class CubeTestFindingServiceImpl implements CubeTestFindingService {

	@Autowired
	private CubeTestFindingRepository cubeTestFindingRepository;
	@Autowired
	private ConcreteTestResultRepository concreteTestResultRepository;
	@Autowired
	private FinishProductSampleRepository finishProductSampleRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private MailConstants mailConstants;

	@Transactional
	public void saveCubeTestFinding(CubeTestFinding cubeTestFinding) {
		cubeTestFindingRepository.save(cubeTestFinding);
	}

	@Transactional
	public void updateCubeTestFinding(CubeTestFinding cubeTestFinding) {
		cubeTestFindingRepository.save(setCubConcreteStrengthRatio(cubeTestFinding));
	}

	@Transactional(readOnly = true)
	public List<CubeTestFinding> getAllCubeTestFindings() {
		return cubeTestFindingRepository.findAll();
	}

	@Transactional(readOnly = true)
	public boolean isCubeTestFindingExist(Long id) {
		return cubeTestFindingRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public CubeTestFinding getCubeTestFindingById(Long id) {
		return cubeTestFindingRepository.findById(id).get();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteCubeTestFinding(Long id) {
		cubeTestFindingRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public boolean checkAge(Long age) {
		if (!(age == 1 || age == 3 || age == 5 || age == 7 || age == 14 || age == 21 || age == 28 || age == 56
				|| age == 128)) {
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	public List<CubeTestFinding> findByFinishProductSampleId(Long finishProductSampleId) {
		return cubeTestFindingRepository.findByFinishProductSampleId(finishProductSampleId);
	}

	@Transactional(readOnly = true)
	public boolean existsByFinishProductSampleId(Long finishProductSampleId) {
		return cubeTestFindingRepository.existsByFinishProductSampleId(finishProductSampleId);
	}

	private void calculateConcreteStatus(Long finishProductSampleId) {
		ConcreteTestResult concreteTestResult = concreteTestResultRepository
				.findByFinishProductSampleId(finishProductSampleId);
		Double ratio = concreteTestResult.getStrengthGradeRatio();
		Long concreteAge = concreteTestResult.getAge();
		if ((ratio >= 0.16 && concreteAge == 1) || (ratio >= 0.40 && concreteAge == 3)
				|| (ratio >= 0.50 && concreteAge == 5) || (ratio >= 0.65 && concreteAge == 7)
				|| (ratio >= 0.90 && concreteAge == 14) || (ratio >= 0.94 && concreteAge == 21)
				|| (ratio >= 0.99 && concreteAge == 28) || (ratio >= 1 && concreteAge == 56)
				|| (ratio >= 1 && concreteAge == 128)) {
			concreteTestResult.setStatus(Status.PASS);
			String messsage = "Congrete Strength Test is " + concreteTestResult.getStatus()
					+ " for the mixdesign code is "
					+ concreteTestResult.getFinishProductSample().getMixDesign().getCode() + "<ul><li> Age : "
					+ concreteTestResult.getAge() + " days </li>" + "<li> Strength : " + concreteTestResult.getResult()
					+ "</li></ul>";
			emailService.sendMailWithFormat(mailConstants.getMailCongreteStrengthTestStatus(),
					Constants.SUBJECT_NEW_CONGRETE_STRENGTH_TEST, messsage);
		} else if (ratio > 0) {
			concreteTestResult.setStatus(Status.FAIL);
			String messsage = "Congrete Strength Test is " + concreteTestResult.getStatus()
					+ " for the mixdesign code is "
					+ concreteTestResult.getFinishProductSample().getMixDesign().getCode() + "<ul><li> Age : "
					+ concreteTestResult.getAge() + "days </li>" + "<li> Strength : " + concreteTestResult.getResult()
					+ "</li></ul>";
			emailService.sendMailWithFormat(mailConstants.getMailCongreteStrengthTestStatus(),
					Constants.SUBJECT_NEW_CONGRETE_STRENGTH_TEST, messsage);
		}
	}

	private Double roundDoubleValue(Double value) {
		DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
		return Double.valueOf(decimalFormat.format(value));
	}

	public Double calculateAverageCubStrength(Long finishProductSampleId) {
		List<CubeTestFinding> CubeTestFindingList = findByFinishProductSampleId(finishProductSampleId);
		double sum = 0;
		double count = 0;
		for (CubeTestFinding cubeTestFinding : CubeTestFindingList) {
			ConcreteTestResult concreteTestResult = concreteTestResultRepository
					.findByFinishProductSampleId(finishProductSampleId);
			if (cubeTestFinding.getAge() == concreteTestResult.getAge()) {
				sum = sum + cubeTestFinding.getValue();
				count++;
			}
		}
		return (sum / count);
	}

	public Double getTargetGradre(Long finishProductSampleId) {
		FinishProductSample finishProductSample = finishProductSampleRepository.findById(finishProductSampleId).get();
		return finishProductSample.getMixDesign().getTargetGrade();
	}

	public Double calculateCubStrengthRatio(Long finishProductSampleId) {
		return calculateAverageCubStrength(finishProductSampleId) / getTargetGradre(finishProductSampleId);
	}

	public CubeTestFinding setCubConcreteStrengthRatio(CubeTestFinding cubeTestFinding) {
		ConcreteTestResult concreteTestResult = concreteTestResultRepository
				.findByFinishProductSampleId(cubeTestFinding.getFinishProductSample().getId());
		concreteTestResult.setResult(
				roundDoubleValue(calculateAverageCubStrength(cubeTestFinding.getFinishProductSample().getId())));
		concreteTestResult.setStrengthGradeRatio(
				roundDoubleValue(calculateCubStrengthRatio(cubeTestFinding.getFinishProductSample().getId())));
		calculateConcreteStatus(cubeTestFinding.getFinishProductSample().getId());
		return cubeTestFinding;
	}

	@Transactional(readOnly = true)
	public Page<CubeTestFinding> searchCubeTestFinding(Predicate predicate, int size, int page) {
		return cubeTestFindingRepository.findAll(predicate,
				PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
	}
}
