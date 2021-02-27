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
public class CustomerRequestDto {
  private Long id;
  @NotNull(message = "{customerDto.name.null}")
  @NotEmpty(message = "{customerDto.name.empty}")
  private String name;
  private String phoneNumber;
  private String address;
  private String email;
  @NotNull(message = "{customerDto.plantCode.null}")
  @NotEmpty(message = "{customerDto.plantCode.empty}")
  private List<String> plantCodes;
  private boolean sentMail;
}
