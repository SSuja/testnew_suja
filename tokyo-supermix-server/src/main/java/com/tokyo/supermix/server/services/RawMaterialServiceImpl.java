package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.notification.EmailNotification;

@Service
public class RawMaterialServiceImpl implements RawMaterialService {
	@Autowired
	private RawMaterialRepository rawMaterialRepository;
	@Autowired
	private EmailNotification emailNotification;

	@Transactional
	public void saveRawMaterial(RawMaterial rawMaterial) {
		RawMaterial rawMaterialObj = rawMaterialRepository.save(rawMaterial);
		if (rawMaterialObj != null) {
			emailNotification.sendRawmaterialCreationEmail(rawMaterial);
		}
	}

	@Transactional(readOnly = true)
	public boolean isRawMaterialNameExist(String name) {
		return rawMaterialRepository.existsByName(name);
	}

	@Transactional(readOnly = true)
	public boolean isRawMaterialExist(Long id) {
		return rawMaterialRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public List<RawMaterial> getAllRawMaterials(Pageable pageable) {
		return rawMaterialRepository.findAll(pageable).toList();
	}

	@Transactional(readOnly = true)
	public RawMaterial getRawMaterialById(Long id) {
		return rawMaterialRepository.findById(id).get();
	}

	public boolean isUpdatedNameExist(Long id, String name) {
		if ((!getRawMaterialById(id).getName().equalsIgnoreCase(name)) && (isRawMaterialNameExist(name))) {
			return true;
		}
		return false;
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteRawMaterial(Long id) {
		rawMaterialRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Page<RawMaterial> searchRawMaterial(Predicate predicate, int page, int size) {
		return rawMaterialRepository.findAll(predicate, PageRequest.of(page, size, Sort.Direction.ASC, "id"));
	}

	@Transactional(readOnly = true)
	public List<RawMaterial> getAllActiveRawMaterials() {
		return rawMaterialRepository.findByActiveTrue();
	}

	@Transactional(readOnly = true)
	public List<RawMaterial> getByMaterialSubCategoryId(Long materialSubCategoryId) {
		return rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategoryId);
	}

	@Transactional(readOnly = true)
	public List<RawMaterial> getRawMaterialsByPlantCode(String plantCode, Pageable pageable) {
		return rawMaterialRepository.findByPlantCodeOrPlantNull(plantCode, pageable).toList();
	}

	@Transactional(readOnly = true)
	public List<RawMaterial> getRawMaterialsByMaterialSubCategoryAndPlantCode(Long materialSubCategoryId,
			String plantCode) {
		return rawMaterialRepository.findByMaterialSubCategoryIdAndPlantCodeOrPlantNull(materialSubCategoryId,
				plantCode);
	}

	@Transactional(readOnly = true)
	public Long countRawMaterials() {
		return rawMaterialRepository.count();
	}

	@Transactional(readOnly = true)
	public Long countRawMaterialByPlant(String plantCode) {
		return rawMaterialRepository.countByPlantCode(plantCode);
	}

	@Transactional(readOnly = true)
	public boolean isPrefixAlreadyExists(String prefix) {
		if (rawMaterialRepository.existsByPrefix(prefix)) {
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	public boolean isPrefixAlreadyExistsUpdate(Long id, String prefix) {
		if ((!getRawMaterialById(id).getPrefix().equalsIgnoreCase(prefix))
				&& rawMaterialRepository.existsByPrefix(prefix)) {
			return true;
		}
		return false;
	}
}
