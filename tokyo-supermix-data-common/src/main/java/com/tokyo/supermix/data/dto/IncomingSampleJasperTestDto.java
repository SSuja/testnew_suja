package com.tokyo.supermix.data.dto;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IncomingSampleJasperTestDto {
  private String testName;
  private String status;
  private Double average;
  private Date date;
  private AcceptedValueJasperDto acceptanceCriteria;
}
