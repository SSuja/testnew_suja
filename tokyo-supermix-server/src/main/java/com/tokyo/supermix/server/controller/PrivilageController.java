package com.tokyo.supermix.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.data.repositories.auth.PermissionRepository;
import com.tokyo.supermix.data.repositories.auth.RoleRepository;


@CrossOrigin
@RestController
public class PrivilageController {
  @Autowired
  private PermissionRepository permissionRepository;
  @Autowired
  private RoleRepository roleRepository;
  private static final Logger logger = Logger.getLogger(PrivilageController.class);

//  @PostMapping(value = EndpointURI.PRIVILAGE)
//  public ResponseEntity<Object> createPrivilage(
//      @Valid @RequestBody List<PrivilageRequestDto> privilageRequestDtos) {
//    List<String> dtoList = new ArrayList<String>();
//    for (int i = 0; i < privilageRequestDtos.size(); i++) {
//      dtoList.add(privilageRequestDtos.get(i).getRoleName());
//    }
//    for (int j = 0; j < dtoList.size(); j++) {
//      ArrayList<Long> permissionsId = new ArrayList<Long>();
//      ArrayList<Long> falsePermissionId = new ArrayList<Long>();
//      String roleName = roleRepository.findByRoleName(dtoList.get(j)).get().getRoleName();
//      List<Permission> permissions = roleRepository.findByRoleName(roleName).get().getPermissions();
//      for (Permission permission : permissions) {
//        permissionsId.add(permission.getId());
//      }
//      for (PrivilageRequestDto privilageRequestDto : privilageRequestDtos) {
//
//        if (privilageRequestDto.getStatus() == true
//            && privilageRequestDto.getRoleName() == dtoList.get(j)) {
//          permissionsId.add(privilageRequestDto.getPermissionId());
//        } else if (privilageRequestDto.getStatus() == false
//            && privilageRequestDto.getRoleName() == dtoList.get(j)) {
//          privilageRequestDto.getPermissionId();
//          falsePermissionId.add(privilageRequestDto.getPermissionId());
//        }
//      }
//      permissionsId.removeAll(falsePermissionId);
//      Role role = new Role();
//      role.setId(roleRepository.findByRoleName(roleName).get().getId());
//      role.setName(roleName);
//      List<Permission> permissionList = new ArrayList<Permission>();
//      permissionsId.forEach(id -> permissionList.add(permissionRepository.findById(id).get()));
////      role.setPermissions(permissionList);
//      roleRepository.save(role);
//    }
//    return new ResponseEntity<>(
//        new BasicResponse<>(RestApiResponseStatus.OK, Constants.PERMISSIONS_SUCCESS),
//        HttpStatus.OK);
//  }
}
