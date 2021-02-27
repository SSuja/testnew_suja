package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(schema = "tokyo-supermix", name = "parameter_equation")
public class ParameterEquation extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "equationId", nullable = false)
  private Equation equation;
  @ManyToOne
  @JoinColumn(name = "testParameterId", nullable = false)
  private TestParameter testParameter;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "parameterEquation")
  private List<ParameterEquationElement> parameterEquationElements =
      new ArrayList<ParameterEquationElement>();
}
