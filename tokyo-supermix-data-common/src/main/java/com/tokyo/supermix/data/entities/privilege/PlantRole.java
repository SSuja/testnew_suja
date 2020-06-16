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

@Entity
@Table(name = "plant_role", schema = "tokyo-supermix")
public class PlantRole implements Serializable {
  private static final long serialVersionUID = 1L;
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
  private Set<RolePlantPermission> rolePlantPermission;

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

  public Set<RolePlantPermission> getRolePlantPermission() {
    return rolePlantPermission;
  }

  public void setRolePlantPermission(Set<RolePlantPermission> rolePlantPermission) {
    this.rolePlantPermission = rolePlantPermission;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
