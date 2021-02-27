package com.tokyo.supermix.data.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SupplierRequestDto {
  private Long id;
  @NotNull(message = "{supplierRequestDto.name.null}")
  @NotEmpty(message = "{supplierRequestDto.name.empty}")
  private String name;
  private String address;
  private String phoneNumber;
  private String email;
  private boolean sentMail;
  @NotNull(message = "{supplierRequestDto.plantCode.null}")
  @NotEmpty(message = "{supplierRequestDto.plantCode.empty}")
  private String plantCode;
  private List<Long> suppilerCategoryIds;
}
