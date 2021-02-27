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
@Table(schema = "tokyo-supermix", name = "supplier")
public class Supplier extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String address;
  private String phoneNumber;
  private String email;
  private boolean sentMail;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "supplier_supplierCategory",
      joinColumns = {@JoinColumn(name = "supplier_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "supplierCategory_id", referencedColumnName = "id")})
  private List<SupplierCategory> supplierCategories;
  @ManyToOne
  @JoinColumn(name = "plantCode", nullable = false)
  private Plant plant;
}
