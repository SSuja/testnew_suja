package com.tokyo.supermix;

public final class PrivilegeEndpointURI {
  private static final String BASE_API_PATH = "/api/v1/";

  /*
   * private constructor to avoid instantiating this class.
   */
  private static final String SLASH = "/";
  private static final String CODE = "/{code}";
  private static final String ID = "/{id}";
  private static final String SEARCH = "/search";
  private static final String SERIALNO = "/{serialNo}";
  /*
   * Authentication
   * 
   */
  public static final String AUTH = BASE_API_PATH + "auth/";
  public static final String SIGNIN = AUTH + "sign-in";
  public static final String SIGNUP = AUTH + "sign-up";
  public static final String CHANGE_PASSWORD = AUTH + "change-password";
  public static final String FORGOT_PASSWORD = AUTH + "forgot-password";
  public static final String RESET_PASSWORD = AUTH + "reset-password";

  /*
   * Role apis
   */
  public static final String ROLE = BASE_API_PATH + "role";
  public static final String ROLES = BASE_API_PATH + "roles";
  public static final String ROLE_BY_ID = ROLE + ID;

  /*
   * User
   */
  public static final String USER = BASE_API_PATH + "user";
  public static final String USERS = BASE_API_PATH + "users";
  public static final String USER_BY_ID = USER + ID;
  public static final String UPDATE_USER_STATUS_BY_ID =
      USER + SLASH + "{userId}" + SLASH + "status" + SLASH + "{status}";
  public static final String UPDATE_USER_ROLE = USER + SLASH + "role";
  public static final String USER_BY_PLANT = USER + "/plant";
  public static final String USER_BY_PLANT_BY_USERTYPE = USER + "/plant"+SLASH+"{userType}";
  public static final String USER_BY_USERTYPE = USER +SLASH+"user-type"+SLASH+"{userType}";
  /*
   * User Role
   */
  public static final String USER_ROLE = BASE_API_PATH + "user-role";
  public static final String USER_ROLE_BY_USER = USER_ROLE + SLASH + "user" + SLASH + "{userId}";
  public static final String USER_ROLE_BY_ROLE = USER_ROLE + SLASH + "role" + SLASH + "{roleId}";
  /*
   * User Plant Role
   */
  public static final String USER_PLANT_ROLE = BASE_API_PATH + "user-plant-role";
  public static final String USER_PLANT_ROLE_BY_USER = USER_PLANT_ROLE + SLASH + "user" + SLASH + "{userId}";
  public static final String USER_PLANT_ROLE_BY_ROLE = USER_PLANT_ROLE + SLASH + "plant-role" + SLASH + "{plantRoleId}";
  public static final String USER_PLANT_ROLE_BY_ROLE_BY_USERTYPE = USER_PLANT_ROLE + SLASH + "plant-role" + SLASH +
      "{plantRoleId}"+SLASH+"user-type"+SLASH+"{userType}";
  /*
   * Permission
   */
  public static final String PERMISSION = BASE_API_PATH + "permission";
  public static final String PERMISSIONS = BASE_API_PATH + "permissions";
  public static final String SUBMODULE = "sub-module" + SLASH + "{subModuleName}";
  public static final String PERMISSION_BY_SUBMODULE = PERMISSION + SLASH + SUBMODULE;

  /*
   * role Permission
   */
  public static final String ROLE_PERMISSION = BASE_API_PATH + "role-permission";
  public static final String ROLE_PERMISSIONS = ROLE_PERMISSION + SLASH + "{roleId}";
  public static final String STATUS_ROLE_PERMISSIONS =
      ROLE_PERMISSION + SLASH + "status" + SLASH + "{status}";
  public static final String ROLE_PERMISSION_MODULE_STATUS =
      ROLE_PERMISSION + SLASH + "module" + SLASH + "status" + SLASH + "{roleId}";


  /*
   * main Module
   */
  public static final String MODULE = "module";
  public static final String MAIN_MODULES = BASE_API_PATH + "main-modules";
  public static final String MODULE_ROLE_PERMISSIONS = ROLE_PERMISSION + SLASH + MODULE;
  /*
   * sub Module
   */
  public static final String SUB_MODULES_BY_MAIN_MODULE = PERMISSION + SLASH + "{mainModule}";
  /*
   * plant permission
   */
  public static final String PLANT_PERMISSION = BASE_API_PATH + "plant-permission";
  public static final String PLANT_PERMISSIONS = BASE_API_PATH + "plant-permissions";
  public static final String PLANT_PERMISSION_BY_ID = PLANT_PERMISSION + ID;
  public static final String PLANT_PERMISSION_BY_PERMISSION_NAME =
      PLANT_PERMISSION + SLASH + "{permissionName}";

  /*
   * plant Role
   */
  public static final String PLANT_ROLE = BASE_API_PATH + "plant-role";
  public static final String PLANT_ROLES = BASE_API_PATH + "plant-roles";
  public static final String PLANT_ROLE_BY_ROLE_NAME =
      PLANT_ROLE + SLASH + "role-name" + SLASH + "{roleName}";
  public static final String PLANT_ROLES_BY_PLANT_CODE = PLANT_ROLE + SLASH + "{plantCode}";
  /*
   * plant Role Plant Permission
   */
  public static final String PLANT_ROLE_PLANT_PERMISSION =
      BASE_API_PATH + "plant-role-plant-permission";
  public static final String PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID =
      PLANT_ROLE_PLANT_PERMISSION + SLASH + "{plantRoleId}";

  public static final String PLANT_ROLE_PERMISSION_MODULE_STATUS =
      PLANT_ROLE_PLANT_PERMISSION + SLASH + "module" + SLASH + "status" + SLASH + "{plantRoleId}";

  public static final String PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID_AND_SUBMODULE_ID =
      PLANT_ROLE_PLANT_PERMISSION + SLASH + "{plantRoleId}" + SLASH + "subModuleId" + SLASH
          + "{subModuleId}";

  public static final String PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID_AND_STATUS =
      PLANT_ROLE_PLANT_PERMISSION + SLASH + "{plantRoleId}" + SLASH + "status" + SLASH + "{status}";
  public static final String PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID_AND_SUBMODULE_ID_AND_STATUS =
      PLANT_ROLE_PLANT_PERMISSION + SLASH + "{plantRoleId}" + SLASH + "subModuleId" + SLASH
          + "{subModuleId}" + SLASH + "status" + SLASH + "{status}";
  
  public static final String PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID_AND_PERMISSION_NAME_AND_STATUS =
      PLANT_ROLE_PLANT_PERMISSION + SLASH + "{plantRoleId}" + SLASH + "permissionName" + SLASH
          + "{permissionName}" + SLASH + "status" + SLASH + "{status}";

  public static final String PLANT_PERMISSION_BY_PLANT_CODE_AND_SUBMODULE_ID_AND_MAINMODULE_ID =
      PLANT_ROLE_PLANT_PERMISSION + SLASH + "{plantCode}" + SLASH + "subModuleId" + SLASH
          + "{subModuleId}" + SLASH + "mainmodule" + SLASH + "{mainModuleId}";;
  public static final String PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID_AND_PLANT_CODE =
      PLANT_ROLE_PLANT_PERMISSION + SLASH + "{plantRoleId}" + SLASH + "plantCode" + SLASH
          + "{plantCode}";
  /*
   * plant access level
   */
  public static final String PLANT_ACCESS_LEVEL = BASE_API_PATH + "plant-access-level";
  public static final String PLANT_ROLE_BY_PLANT_CODE_AND_STATUS =
      PLANT_ACCESS_LEVEL + SLASH + "plantCode" + SLASH
          + "{plantCode}" + SLASH + "status" + SLASH + "{status}";
  
  /*
   * user  Plant Permission
   */
  public static final String USER_PLANT_PERMISSION =
      BASE_API_PATH + "user-plant-permission";
  public static final String USER_PLANT_PERMISSION_BY_USER_ID =
      USER_PLANT_PERMISSION + SLASH + "{userId}";

}
