package com.tokyo.supermix.data.entities.auth;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.privilege.UserPlantPermission;
import com.tokyo.supermix.data.enums.UserType;

@Entity
@Table(schema = "tokyo-supermix", name = "user")
public class User extends DateAudit {
  /**
   * 
   */
  private static final long serialVersionUID = -3475726035036394016L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String userName;
  private String password;
  private String email;
  @OneToOne
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;
  @Enumerated(EnumType.ORDINAL)
  private UserType userType;
  private Boolean isActive=true;
  @OneToMany(mappedBy = "user")
  private Set<UserRole> userRoles;
  @OneToMany(mappedBy = "user")
  private Set<UserPlantRole> userPlantRoles;
  @OneToMany(mappedBy = "user")
  private Set<UserPlantPermission> userPlantPermissions;
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public Set<UserRole> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(Set<UserRole> userRoles) {
    this.userRoles = userRoles;
  }

  public Set<UserPlantRole> getUserPlantRoles() {
    return userPlantRoles;
  }

  public void setUserPlantRoles(Set<UserPlantRole> userPlantRoles) {
    this.userPlantRoles = userPlantRoles;
  }

  public Set<UserPlantPermission> getUserPlantPermissions() {
    return userPlantPermissions;
  }

  public void setUserPlantPermissions(Set<UserPlantPermission> userPlantPermissions) {
    this.userPlantPermissions = userPlantPermissions;
  }
  
}
