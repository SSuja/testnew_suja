package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.Supplier;

public interface SupplierService {
	public List<Supplier> getSuppliers();

	public Supplier createSupplier(Supplier supplier);

	public Supplier getSupplierById(Long id);

	public void updateSupplier(Supplier supplier);

	public void deleteSupplierById(Long id);

	public List<Supplier> getBySupplierCategoryId(Long supplierCategoryId);

	public Boolean existBySupplierCategoryId(Long supplierCategoryId);

	public Boolean isSupplierExist(Long id);

	public Boolean isSupplierNameExist(String name);

	public Boolean isEmailExist(String email);

	public Boolean isPhoneNumberExist(String phoneNumber);

	public Boolean isSupplierNameExistWithId(Long id, String name);

	public Boolean isPhoneNumberExistWithId(Long id, String phoneNumber);

	public Boolean isEmailExistWithId(Long id, String email);
}
