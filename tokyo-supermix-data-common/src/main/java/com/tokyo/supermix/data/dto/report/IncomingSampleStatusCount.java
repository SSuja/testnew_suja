package com.tokyo.supermix.data.dto.report;

public class IncomingSampleStatusCount {
  private int passCount;
  private int failCount;
  private int newCount;
  private int processCount;

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
}
