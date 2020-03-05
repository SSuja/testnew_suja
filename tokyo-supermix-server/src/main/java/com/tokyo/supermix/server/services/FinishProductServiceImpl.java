package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.FinishProduct;
import com.tokyo.supermix.data.repositories.FinishProductRepository;

@Service
public class FinishProductServiceImpl implements FinishProductService {

	@Autowired
	private FinishProductRepository finishProductRepository;

	@Transactional
	public void saveFinishProduct(FinishProduct finishProduct) {
		finishProductRepository.save(finishProduct);
	}

	@Transactional(readOnly = true)
	public List<FinishProduct> getAllFinishProducts() {

		return finishProductRepository.findAll();
	}

	@Transactional(readOnly = true)
	public FinishProduct getById(Long id) {
		return finishProductRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public boolean isFinishProductExists(Long id) {
		return finishProductRepository.existsById(id);
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteFinishProduct(Long id) {
		finishProductRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public boolean isProjectCodeExists(String projectCode) {
		return finishProductRepository.existsByProjectCode(projectCode);
	}

	@Transactional(readOnly = true)
	public boolean isMixDesignCodeExists(String mixDesignCode) {
		return finishProductRepository.existsByMixDesignCode(mixDesignCode);
	}

	@Transactional(readOnly = true)
	public boolean isPourIdExists(Long pourId) {
		return finishProductRepository.existsByPourId(pourId);
	}
}
