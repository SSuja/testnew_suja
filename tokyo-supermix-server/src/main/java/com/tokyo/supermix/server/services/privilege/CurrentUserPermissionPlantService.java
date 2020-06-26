package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.security.UserPrincipal;

public interface CurrentUserPermissionPlantService {
  List<String> getPermissionPlantCodeByCurrentUser(UserPrincipal currentUser,String permissionName);
}
