package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
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
@Table(schema = "tokyo-supermix", name = "project")
public class Project extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private String name;
  private String contactNumber;
  private String contactPerson;
  private Date startDate;
  private boolean sentMail;
  @ManyToOne
  @JoinColumn(name = "customerId", nullable = false)
  private Customer customer;
  @ManyToOne
  @JoinColumn(name = "plantCode", nullable = false)
  private Plant plant;
}
