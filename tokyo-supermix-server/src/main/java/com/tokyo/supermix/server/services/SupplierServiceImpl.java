package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.data.repositories.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public List<Supplier> getSuppliers() {
		return supplierRepository.findAll();
	}

	@Override
	public List<Supplier> getBySupplierCategoryId(Long supplierCategoryId) {
		return supplierRepository.getBySuppilerCategory(supplierCategoryId);
	}

	@Override
	public Boolean existBySupplierCategoryId(Long supplierCategoryId) {
		 if(supplierRepository.existBySuppilerCategoryId(supplierCategoryId)>0) {
			 return true;
		 }
		 return false;
	}

	@Transactional
	public Supplier createSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	@Transactional
	public void updateSupplier(Supplier supplier) {
		supplierRepository.save(supplier);
	}

	@Transactional
	public void deleteSupplierById(Long id) {
		supplierRepository.deleteById(id);
	}

	@Override
	public Supplier getSupplierById(Long id) {
		return supplierRepository.findSupplierById(id);
	}

	@Override
	public Boolean isSupplierExist(Long id) {
		return supplierRepository.existsById(id);

	}

	@Transactional(readOnly = true)
	public Boolean isSupplierNameExist(String supplier) {
		return supplierRepository.existsByName(supplier);
	}

	@Override
	public Boolean isEmailExist(String email) {
		return supplierRepository.existsByEmail(email);
	}

	@Override
	public Boolean isPhoneNumberExist(String phoneNumber) {
		return supplierRepository.existsByPhoneNumber(phoneNumber);
	}

	@Override
	public Boolean isSupplierNameExistWithId(Long id, String name) {
		if(supplierRepository.checkSupplierNameWithLockId(id, name)>0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean isEmailExistWithId(Long id, String email) {
		if(supplierRepository.checkSupplierNameWithLockId(id, email)>0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean isPhoneNumberExistWithId(Long id, String phoneNumber) {
		if(supplierRepository.checkSupplierNameWithLockId(id,phoneNumber )>0) {
			return true;
		}
		return false;
	}

}
