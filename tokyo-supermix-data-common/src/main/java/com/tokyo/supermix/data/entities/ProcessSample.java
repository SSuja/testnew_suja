package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "process_sample")
public class ProcessSample implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private String code;
  private String totalQuantity;
  @ManyToOne
  @JoinColumn(name = "materialLoadCode", nullable = false)
  private MaterialLoad materialLoad;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getTotalQuantity() {
    return totalQuantity;
  }

  public void setTotalQuantity(String totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

  public MaterialLoad getMaterialLoad() {
    return materialLoad;
  }

  public void setMaterialLoad(MaterialLoad materialLoad) {
    this.materialLoad = materialLoad;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
