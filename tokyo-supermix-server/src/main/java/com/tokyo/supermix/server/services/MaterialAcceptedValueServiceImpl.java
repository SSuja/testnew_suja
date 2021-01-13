package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.AccepetedValueDto;
import com.tokyo.supermix.data.dto.AcceptedValueMainDto;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;

@Service
public class MaterialAcceptedValueServiceImpl implements MaterialAcceptedValueService {

  @Autowired
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;
  @Autowired
  private TestConfigureService testConfigureService;
  @Autowired
  private TestConfigureRepository testConfigureRepository;
  @Autowired
  private RawMaterialRepository rawMaterialRepository;

  @Transactional
  public List<MaterialAcceptedValue> saveAcceptedValue(
      List<MaterialAcceptedValue> materialAcceptedValue) {
    return materialAcceptedValueRepository.saveAll(materialAcceptedValue);
  }

  @Transactional
  public void updateMaterialAcceptedValue(MaterialAcceptedValue materialAcceptedValue) {
    materialAcceptedValueRepository.save(materialAcceptedValue);
  }

  @Transactional(readOnly = true)
  public List<MaterialAcceptedValue> getAllMaterialAcceptedValues() {
    return materialAcceptedValueRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isMaterialAcceptedValueExist(Long id) {
    return materialAcceptedValueRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public MaterialAcceptedValue getMaterialAcceptedValueById(Long id) {
    return materialAcceptedValueRepository.findById(id).get();
  }

  @Transactional
  public void deleteMaterialAcceptedValue(Long id) {
    materialAcceptedValueRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<MaterialAcceptedValue> getMaterialAcceptedValueByTestConfigure(
      TestConfigure testConfigure) {
    return materialAcceptedValueRepository.findByTestConfigure(testConfigure);
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateEntryExist(Long testConfigureId, Long rawMaterialId) {
    return materialAcceptedValueRepository.existsByTestConfigureIdAndRawMaterialId(testConfigureId,
        rawMaterialId);
  }

  public boolean isUpdatedRawMaterialIdExist(Long id, Long testConfigureId, Long rawMaterialId) {
    if ((!getMaterialAcceptedValueById(id).getRawMaterial().getId().equals(rawMaterialId))
        && (isDuplicateEntryExist(testConfigureId, rawMaterialId))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isRawMaterialIdExist(Long rawMaterialId) {
    return materialAcceptedValueRepository.existsByRawMaterialId(rawMaterialId);
  }

  @Transactional(readOnly = true)
  public boolean isTestConfigureIdAndRawMaterialIdAndTestParameterId(Long testConfigureId,
      Long rawMaterialId, Long testParameterId) {
    return materialAcceptedValueRepository
        .existsByTestConfigureIdAndRawMaterialIdAndTestParameterId(testConfigureId, rawMaterialId,
            testParameterId);
  }

  @Transactional(readOnly = true)
  public boolean isCheckValidation(List<MaterialAcceptedValue> materialAcceptedValueList) {
    for (MaterialAcceptedValue materialAcceptedValue : materialAcceptedValueList) {
      if (materialAcceptedValue.getConditionRange().equals(Condition.BETWEEN)) {
        if (materialAcceptedValue.getMaxValue() == null
            || materialAcceptedValue.getMinValue() == null) {
          return true;
        }
      } else if (materialAcceptedValue.getConditionRange().equals(Condition.GREATER_THAN)
          || materialAcceptedValue.getConditionRange().equals(Condition.LESS_THAN)
          || materialAcceptedValue.getConditionRange().equals(Condition.EQUAL)) {
        if (materialAcceptedValue.getValue() == null) {
          return true;
        }
      }
    }
    return false;
  }

  @Transactional(readOnly = true)
  public AcceptedValueMainDto findByTestConfigureId(Long testConfigureId) {
    AcceptedValueMainDto acceptedValueMainDto = new AcceptedValueMainDto();
    acceptedValueMainDto.setTestConfigureResDto(
        testConfigureService.getTestConfigureForAcceptedValue(testConfigureId));
    acceptedValueMainDto.setAccepetedValueDto(getMaterialAccepetedValueDtoList(testConfigureId));
    return acceptedValueMainDto;
  }

  private List<AccepetedValueDto> getMaterialAccepetedValueDtoList(Long testConfigureId) {
    List<AccepetedValueDto> accepetedValueDtolist = new ArrayList<>();
    materialAcceptedValueRepository.findByTestConfigureId(testConfigureId).stream()
        .forEach(acceptedValue -> {
          AccepetedValueDto accepetedValueDto = new AccepetedValueDto();
          accepetedValueDto.setId(acceptedValue.getId());
          accepetedValueDto.setConditionRange(acceptedValue.getConditionRange().toString());
          accepetedValueDto.setMinValue(acceptedValue.getMinValue());
          accepetedValueDto.setMaxValue(acceptedValue.getMaxValue());
          accepetedValueDto.setFinalResult(acceptedValue.isFinalResult());
          accepetedValueDto.setValue(acceptedValue.getValue());
          if (acceptedValue.getTestEquation() != null) {
            accepetedValueDto.setTestEquationId(acceptedValue.getTestEquation().getId());
            accepetedValueDto
                .setTestEquationFormula(acceptedValue.getTestEquation().getEquation().getFormula());
          }
          if (acceptedValue.getRawMaterial() != null) {
            accepetedValueDto.setMaterialId(acceptedValue.getRawMaterial().getId());
            accepetedValueDto.setMaterialName(acceptedValue.getRawMaterial().getName());
          }
          accepetedValueDto
              .setParameterName(acceptedValue.getTestParameter().getParameter().getName());
          accepetedValueDto.setTestParameterId(acceptedValue.getTestParameter().getId());
          if (acceptedValue.getTestParameter().getName() != null) {
            accepetedValueDto.setTestParameterName(acceptedValue.getTestParameter().getName());
          }
          accepetedValueDtolist.add(accepetedValueDto);
        });
    return accepetedValueDtolist;
  }


  @Transactional(readOnly = true)
  public boolean ExistsTestConfigureId(Long testConfigureId) {
    return materialAcceptedValueRepository.existsByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> findRawMaterialByTestConfigureId(Long testConfigureId) {
    ArrayList<RawMaterial> rawMaterials = new ArrayList<RawMaterial>();
    TestConfigure testConfigure = testConfigureRepository.findById(testConfigureId).get();
    if (testConfigure.getMaterialSubCategory() == null) {
      rawMaterials.addAll(rawMaterialRepository.findByMaterialSubCategoryMaterialCategoryId(
          testConfigure.getMaterialCategory().getId()));

    } else if (testConfigure.getRawMaterial() == null) {
      rawMaterials.addAll(rawMaterialRepository
          .findByMaterialSubCategoryId(testConfigure.getMaterialSubCategory().getId()));
    } else {
      rawMaterials
          .add(rawMaterialRepository.findById(testConfigure.getRawMaterial().getId()).get());
    }
    return rawMaterials;
  }
}
