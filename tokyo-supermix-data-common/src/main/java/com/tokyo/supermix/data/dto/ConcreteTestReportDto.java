package com.tokyo.supermix.data.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConcreteTestReportDto {
  private String reportNo;
  private String projectName;
  private String address;
  private String faxNumber;
  private String plantName;
  private String customerName;
  private double targetGrade;
  private double targetSlump;
  private String dateOfTesting;
  private String dateOfCasting;
  private String ageOfCubeTest;
  private double averageStrength;
  private List<CubeTestReportDto> CubeTestReports;
}
