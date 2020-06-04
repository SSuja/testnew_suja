package com.tokyo.supermix.data.entities.privilege;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	private String name;
	 @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
	    private Set<RolePermission> rolePermission ;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<RolePermission> getRolePermission() {
		return rolePermission;
	}
	public void setRolePermission(Set<RolePermission> rolePermission) {
		this.rolePermission = rolePermission;
	}

}
