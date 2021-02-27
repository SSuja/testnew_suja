package com.tokyo.supermix.data.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.TestParameterType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestParameterRequestDto {
  private Long id;
  private Long testConfigureId;
  private Long parameterId;
  private Long unitId;
  @NotNull(message = "{testParameterDto.abbreviation.null}")
  @NotEmpty(message = "{testParameterDto.abbreviation.empty}")
  private String abbreviation;
  private TestParameterType type;
  private Double value;
  private Long qualityParameterId;
  private boolean acceptedCriteria;
  private InputMethod inputMethods;
  private String level;
  private String name;
  private Long tableFormatId;
  private LocalDateTime dateValue;
}
