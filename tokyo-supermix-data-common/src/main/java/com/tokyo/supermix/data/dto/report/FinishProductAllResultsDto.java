package com.tokyo.supermix.data.dto.report;

import java.util.List;

public class FinishProductAllResultsDto {
  private List<FinishProductSampleDto> finishProductSamples;
  private List<StrengthResultDto> strengthResults;
  private List<MixDesignProportionDto> mixDesignProportions;
  private List<SlumpTestResult> slumpTestResults;
  public List<FinishProductSampleDto> getFinishProductSamples() {
    return finishProductSamples;
  }
  public void setFinishProductSamples(List<FinishProductSampleDto> finishProductSamples) {
    this.finishProductSamples = finishProductSamples;
  }
  public List<StrengthResultDto> getStrengthResults() {
    return strengthResults;
  }
  public void setStrengthResults(List<StrengthResultDto> strengthResults) {
    this.strengthResults = strengthResults;
  }
  public List<MixDesignProportionDto> getMixDesignProportions() {
    return mixDesignProportions;
  }
  public void setMixDesignProportions(List<MixDesignProportionDto> mixDesignProportions) {
    this.mixDesignProportions = mixDesignProportions;
  }
  public List<SlumpTestResult> getSlumpTestResults() {
    return slumpTestResults;
  }
  public void setSlumpTestResults(List<SlumpTestResult> slumpTestResults) {
    this.slumpTestResults = slumpTestResults;
  }
  
}
