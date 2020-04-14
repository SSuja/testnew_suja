package com.tokyo.supermix.data.dto;

public class CountMaterialDto {
  private String materialName;
  private int passCount;
  private int failCount;
  private int newCount;
  private int processCount;
  private int total;

  public String getMaterialName() {
    return materialName;
  }

  public void setMaterialName(String materialName) {
    this.materialName = materialName;
  }

  public int getPassCount() {
    return passCount;
  }

  public void setPassCount(int passCount) {
    this.passCount = passCount;
  }

  public int getFailCount() {
    return failCount;
  }

  public void setFailCount(int failCount) {
    this.failCount = failCount;
  }

  public int getNewCount() {
    return newCount;
  }

  public void setNewCount(int newCount) {
    this.newCount = newCount;
  }

  public int getProcessCount() {
    return processCount;
  }

  public void setProcessCount(int processCount) {
    this.processCount = processCount;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }
}
