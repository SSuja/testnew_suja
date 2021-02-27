package com.tokyo.supermix.data.dto;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MixDesignRequestDto {
  private String code;
  @NotNull(message = "{mixDesignRequestDto.date.null}")
  private Date date;
  private String plantCode;
  private Status status;
  private Long rawMaterialId;
  private boolean approved;
  private boolean sentMail;
  private boolean checkDepend;
}
