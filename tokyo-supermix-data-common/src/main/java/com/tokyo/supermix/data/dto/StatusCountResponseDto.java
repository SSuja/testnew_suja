package com.tokyo.supermix.data.dto;

public class StatusCountResponseDto {
  private Long passCount;
  private Long newCount;
  private Long processCount;
  private Long failCount;

  public Long getPassCount() {
    return passCount;
  }

  public void setPassCount(Long passCount) {
    this.passCount = passCount;
  }

  public Long getNewCount() {
    return newCount;
  }

  public void setNewCount(Long newCount) {
    this.newCount = newCount;
  }

  public Long getProcessCount() {
    return processCount;
  }

  public void setProcessCount(Long processCount) {
    this.processCount = processCount;
  }

  public Long getFailCount() {
    return failCount;
  }

  public void setFailCount(Long failCount) {
    this.failCount = failCount;
  }
}
