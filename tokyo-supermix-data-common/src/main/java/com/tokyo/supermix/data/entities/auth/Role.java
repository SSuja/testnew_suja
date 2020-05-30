package com.tokyo.supermix.data.entities.auth;
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
import com.tokyo.supermix.data.entities.auth.Permission;

@Entity
@Table(schema = "tokyo-supermix", name = "role")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String roleName;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "permission_role", joinColumns = {
          @JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {
          @JoinColumn(name = "permission_id", referencedColumnName = "id")})
  private List<Permission> permissions;
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

public List<Permission> getPermissions() {
	return permissions;
}

public void setPermissions(List<Permission> permissions) {
	this.permissions = permissions;
}

}
