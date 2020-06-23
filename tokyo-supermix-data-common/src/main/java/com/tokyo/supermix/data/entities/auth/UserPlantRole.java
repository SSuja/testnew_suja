package com.tokyo.supermix.data.entities.auth;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.enums.RoleType;

@Entity
@Table(name = "user_plant_role", schema = "tokyo-supermix")
public class UserPlantRole implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne
  @JoinColumn(name = "plant_role_id")
  private PlantRole plantRole;

  public UserPlantRole() {}

  @Enumerated(EnumType.ORDINAL)
  private RoleType roleType;

  public RoleType getRoleType() {
    return roleType;
  }

  public void setRoleType(RoleType roleType) {
    this.roleType = roleType;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public UserPlantRole(User user, PlantRole plantRole) {
    super();
    this.user = user;
    this.plantRole = plantRole;
    if(plantRole.getRole().getName().equalsIgnoreCase("USER")) {
      this.roleType =RoleType.INDIVIDUAL;
    }else {
      this.roleType =RoleType.QROUP;
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public PlantRole getPlantRole() {
    return plantRole;
  }

  public void setPlantRole(PlantRole plantRole) {
    this.plantRole = plantRole;
  }
}
