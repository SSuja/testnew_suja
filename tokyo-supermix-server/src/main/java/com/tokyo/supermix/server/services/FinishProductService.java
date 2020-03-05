package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.FinishProduct;

public interface FinishProductService {

	public void saveFinishProduct(FinishProduct finishProduct);

	public List<FinishProduct> getAllFinishProducts();

	public FinishProduct getById(Long id);

	public boolean isFinishProductExists(Long id);

	public void deleteFinishProduct(Long id);

	public boolean isPourIdExists(Long pourId);

	public boolean isUpdatedPourExist(Long id, Long pourId);

}
