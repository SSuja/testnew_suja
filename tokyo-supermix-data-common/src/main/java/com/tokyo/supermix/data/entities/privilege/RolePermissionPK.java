package com.tokyo.supermix.data.entities.privilege;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RolePermissionPK implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "ROLE_ID")
  private Long role_id;

  @Column(name = "PERMISSION_ID")
  private Long permission_id;
}
