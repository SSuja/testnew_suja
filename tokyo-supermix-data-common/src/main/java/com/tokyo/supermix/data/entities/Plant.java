package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.entities.privilege.PlantPermission;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(schema = "tokyo-supermix", name = "plant")
public class Plant extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private String name;
  private String address;
  private String phoneNumber;
  private String description;
  private String faxNumber;
  @OneToMany(mappedBy = "plant")
  Set<PlantRole> plantRole;
  @OneToMany(mappedBy = "plant")
  Set<PlantPermission> plantPermission;
  @ManyToOne
  @JoinColumn(name = "subBusinessUnitId", nullable = true)
  private SubBusinessUnit subBusinessUnit;
  private boolean sentMail;
}
