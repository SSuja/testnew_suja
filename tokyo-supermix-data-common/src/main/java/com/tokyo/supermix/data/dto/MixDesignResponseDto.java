package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MixDesignResponseDto {
  private String code;
  private Date date;
  private String plantCode;
  private String plantName;
  private Status status;
  private String createdAt;
  private String updatedAt;
  private boolean checkDepend;
  private String rawMaterialName;
  private Long rawMaterialId;
  private Long rawMaterialMaterialSubCategoryId;
  private boolean approved;
  private boolean sentMail;
}
