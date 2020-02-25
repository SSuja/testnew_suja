package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.repositories.MaterialSubCategoryRepository;

@Service
public class MaterialSubCategoryServiceImpl implements MaterialSubCategoryService {
	@Autowired
	private MaterialSubCategoryRepository materialSubCategoryRepository;

	@Transactional
	public List<MaterialSubCategory> getMaterialSubCategories() {
		return materialSubCategoryRepository.findAll();
	}

	@Transactional
	public MaterialSubCategory getMaterialSubCategoryById(long id) {
		return materialSubCategoryRepository.findById(id).get();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteMaterialSubCategory(Long id) {
		materialSubCategoryRepository.deleteById(id);
	}

	@Transactional
	public void saveMaterialSubCategory(MaterialSubCategory materialSubCategory) {
		materialSubCategoryRepository.save(materialSubCategory);
	}

	@Transactional(readOnly = true)
	public boolean isMaterialSubCategoryExist(Long id) {
		return materialSubCategoryRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public boolean isMaterialSubCategoryNameExist(String name) {
		return materialSubCategoryRepository.existsByName(name);
	}

	@Override
	public boolean isUpdatedMaterialSubCategoryNameExist(Long id, String name) {
		if ((!getMaterialSubCategoryById(id).getName().equalsIgnoreCase(name))
				&& (isMaterialSubCategoryNameExist(name))) {
			return true;
		}
		return false;
	}

	@Override
	public List<MaterialSubCategory> getMaterialSubCategoryByCategory(MaterialCategory materialCategory) {
		return materialSubCategoryRepository.findByMaterialCategory(materialCategory);
	}
}
