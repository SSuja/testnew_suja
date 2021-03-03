package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.enums.FinishProductSampleType;
import com.tokyo.supermix.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(schema = "tokyo-supermix", name = "finish_product_sample")
public class FinishProductSample extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private String finishProductCode;
  private Date date;
  @ManyToOne
  @JoinColumn(name = "plantEquipmentSerialNo", nullable = false)
  private PlantEquipment plantEquipment;
  @ManyToOne
  @JoinColumn(name = "mixDesignCode", nullable = false)
  private MixDesign mixDesign;
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  private String truckNo;
  @ManyToOne
  @JoinColumn(name = "projectCode", nullable = true)
  private Project project;
  @OneToOne
  @JoinColumn(name = "pourId", nullable = true)
  private Pour pour;
  private String workOrderNumber;
  private boolean sentMail;
  @ManyToOne
  @JoinColumn(name = "userId", nullable = true)
  private User user;
  @Enumerated(EnumType.ORDINAL)
  private FinishProductSampleType finishProductSampleType;
}
