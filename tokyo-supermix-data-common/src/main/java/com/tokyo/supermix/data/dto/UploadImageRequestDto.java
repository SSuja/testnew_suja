package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UploadImageRequestDto {
  private Long id;
  private String testImage;
  private String name;
  private String materialTestCode;
  private String finishProductTestCode;
  private String description;
}
