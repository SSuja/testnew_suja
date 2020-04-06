package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "finish_product_sample")
public class FinishProductSample implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String workOrderNo;
  @ManyToOne
  @JoinColumn(name = "concreteMixerId", nullable = false)
  private ConcreteMixer concreteMixer;
  @ManyToOne
  @JoinColumn(name = "mixDesignCode", nullable = false)
  private MixDesign mixDesign;
  @ManyToOne
  @JoinColumn(name = "finishProductId", nullable = false)
  private FinishProduct finishProduct;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getWorkOrderNo() {
    return workOrderNo;
  }

  public void setWorkOrderNo(String workOrderNo) {
    this.workOrderNo = workOrderNo;
  }

  public ConcreteMixer getConcreteMixer() {
    return concreteMixer;
  }

  public void setConcreteMixer(ConcreteMixer concreteMixer) {
    this.concreteMixer = concreteMixer;
  }

  public MixDesign getMixDesign() {
    return mixDesign;
  }

  public void setMixDesign(MixDesign mixDesign) {
    this.mixDesign = mixDesign;
  }

  public FinishProduct getFinishProduct() {
    return finishProduct;
  }

  public void setFinishProduct(FinishProduct finishProduct) {
    this.finishProduct = finishProduct;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
