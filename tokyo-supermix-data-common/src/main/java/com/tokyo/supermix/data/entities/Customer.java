package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(schema = "tokyo-supermix", name = "customer")
public class Customer extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String phoneNumber;
  private String address;
  private String email;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "customer_customerPlant",
      joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "plant_code", referencedColumnName = "code")})
  private List<Plant> plant;
  private boolean sentMail;
}
