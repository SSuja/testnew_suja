package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(schema = "tokyo-supermix", name = "finish_product_parameter_result")
public class FinishProductParameterResult extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double result;
  @ManyToOne
  @JoinColumn(name = "testParameterId", nullable = false)
  private TestParameter testParameter;
  @ManyToOne
  @JoinColumn(name = "finishProductTestCode", nullable = false)
  private FinishProductTest finishProductTest;
}
