package com.tokyo.supermix.data.dto;

public class StatusCountResponseDto {
  private int total;
  private int passCount;
  private int newCount;
  private int processCount;
  private int failCount;

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getPassCount() {
    return passCount;
  }

  public void setPassCount(int passCount) {
    this.passCount = passCount;
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

  public int getFailCount() {
    return failCount;
  }

  public void setFailCount(int failCount) {
    this.failCount = failCount;
  }

}
