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
@Table(schema = "tokyo-supermix", name = "email_group")
public class EmailGroup extends DateAudit implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private boolean schedule;
  private boolean status;
  private static final long serialVersionUID = 1L;
  @ManyToOne
  @JoinColumn(name = "emailPointsId", nullable = false)
  private EmailPoints emailPoints;
  @ManyToOne
  @JoinColumn(name = "plantCode", nullable = true)
  private Plant plant;
}
