package com.tokyo.supermix.data.dto;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectResponseDto {
  private String code;
  private String name;
  private String contactNumber;
  private String contactPerson;
  private Date startDate;
  private PlantDto plant;
  private CustomerResponseDto customer;
  private String createdAt;
  private String updatedAt;
  private boolean sentMail;
}
