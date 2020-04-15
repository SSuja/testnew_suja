package com.tokyo.supermix.data.dto;

public class CubeTestFindingResponseDto {
  private Long id;
  private Long cubeNo;
  private Long age;
  private Double value;
  private Long finishProductSampleId;
  private Long concreteTestId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCubeNo() {
    return cubeNo;
  }

  public void setCubeNo(Long cubeNo) {
    this.cubeNo = cubeNo;
  }

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public Long getFinishProductSampleId() {
    return finishProductSampleId;
  }

  public void setFinishProductSampleId(Long finishProductSampleId) {
    this.finishProductSampleId = finishProductSampleId;
  }

  public Long getConcreteTestId() {
    return concreteTestId;
  }

  public void setConcreteTestId(Long concreteTestId) {
    this.concreteTestId = concreteTestId;
  }

}
