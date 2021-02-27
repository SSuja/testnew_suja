package com.tokyo.supermix.data.entities;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(schema = "tokyo-supermix", name = "employee_TOKEN")
public class ConfirmationToken {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long tokenid;
  private String confirmationToken;
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;
  @OneToOne(targetEntity = Employee.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "employee_id")
  private Employee employee;

  public ConfirmationToken() {}

  public ConfirmationToken(Employee employee) {
    this.employee = employee;
    createdDate = new Date();
    confirmationToken = UUID.randomUUID().toString();
  }
}
