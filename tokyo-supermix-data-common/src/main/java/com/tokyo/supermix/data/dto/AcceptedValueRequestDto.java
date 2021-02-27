package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.Condition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AcceptedValueRequestDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  @NotNull(message = "{acceptedValueRequestDto.testConfigureId.null}")
  private Long testConfigureId;
  private Long testEquationId;
  private Double value;
  private Condition conditionRange;
  private boolean finalResult;
  private Long testParameterId;
}
