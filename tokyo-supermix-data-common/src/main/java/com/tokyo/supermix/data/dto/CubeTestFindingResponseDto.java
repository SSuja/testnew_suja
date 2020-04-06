package com.tokyo.supermix.data.dto;

public class CubeTestFindingResponseDto {
  private Long id;
  private Long cubeNo;
  private Long age;
  private Double value;
  private Long finishProductSampleId;
  private Long concreteTestElementId;

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

  public Long getConcreteTestElementId() {
    return concreteTestElementId;
  }

  public void setConcreteTestElementId(Long concreteTestElementId) {
    this.concreteTestElementId = concreteTestElementId;
  }
}
