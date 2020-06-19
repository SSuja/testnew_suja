package com.tokyo.supermix.data.entities.privilege;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.auth.Role;
import com.tokyo.supermix.data.entities.auth.UserPlantRole;

@Entity
@Table(name = "plant_role", schema = "tokyo-supermix")
public class PlantRole implements Serializable {
  private static final long serialVersionUID = -2155789570042170615L;
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  Long id;
  @ManyToOne
  @JoinColumn(name = "plant_code")
  private Plant plant;
  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;
  private String name;
  @OneToMany(mappedBy = "plantRole")
  private Set<PlantRolePlantPermission> plantRolePlantPermissions;
  @OneToMany(mappedBy = "plantRole")
  private Set<UserPlantRole> userPlantRoles;
  @OneToMany(mappedBy = "plantRole")
  Set<PlantAccessLevel> plantAccessLevel;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<PlantRolePlantPermission> getPlantRolePlantPermissions() {
    return plantRolePlantPermissions;
  }

  public void setPlantRolePlantPermissions(
      Set<PlantRolePlantPermission> plantRolePlantPermissions) {
    this.plantRolePlantPermissions = plantRolePlantPermissions;
  }

  public Set<UserPlantRole> getUserPlantRoles() {
    return userPlantRoles;
  }

  public void setUserPlantRoles(Set<UserPlantRole> userPlantRoles) {
    this.userPlantRoles = userPlantRoles;
  }

  public Set<PlantAccessLevel> getPlantAccessLevel() {
    return plantAccessLevel;
  }

  public void setPlantAccessLevel(Set<PlantAccessLevel> plantAccessLevel) {
    this.plantAccessLevel = plantAccessLevel;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
