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
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.RawMaterialResponseDto;
import com.tokyo.supermix.data.entities.QRawMaterial;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.util.Constants;
import java.util.Collection;

@Service
public class RawMaterialServiceImpl implements RawMaterialService {
  @Autowired
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  private EmailNotification emailNotification;
  @Autowired
  private Mapper mapper;

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
    if ((!getRawMaterialById(id).getName().equalsIgnoreCase(name))
        && (isRawMaterialNameExist(name))) {
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
    return rawMaterialRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.Direction.ASC, "id"));
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
  public List<RawMaterial> getRawMaterialsByMaterialSubCategoryAndPlantCode(
      Long materialSubCategoryId, String plantCode) {
    return rawMaterialRepository
        .findByMaterialSubCategoryIdAndPlantCodeOrPlantNull(materialSubCategoryId, plantCode);
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
  public List<RawMaterialResponseDto> searchRawMaterial(BooleanBuilder booleanBuilder, String name,
      String materialSubCategoryName, String plantName, String plantCode, Pageable pageable,
      Pagination pagination) {

    if (name != null && !name.isEmpty()) {
      booleanBuilder.and(QRawMaterial.rawMaterial.name.startsWithIgnoreCase(name));
    }
    if (materialSubCategoryName != null && !materialSubCategoryName.isEmpty()) {
      booleanBuilder.and(QRawMaterial.rawMaterial.materialSubCategory.name
          .startsWithIgnoreCase(materialSubCategoryName));
    }
    if (plantName != null && !plantName.isEmpty()) {
      booleanBuilder.and(QRawMaterial.rawMaterial.plant.name.startsWithIgnoreCase(plantName));
    }

    if (plantCode != null && !plantCode.isEmpty()
        && !(plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      booleanBuilder.and(QRawMaterial.rawMaterial.plant.code.startsWithIgnoreCase(plantCode));
    }
    pagination.setTotalRecords(
        ((Collection<RawMaterial>) rawMaterialRepository.findAll(booleanBuilder)).stream().count());
    return mapper.map(rawMaterialRepository.findAll(booleanBuilder, pageable).toList(),
        RawMaterialResponseDto.class);
  }
}

