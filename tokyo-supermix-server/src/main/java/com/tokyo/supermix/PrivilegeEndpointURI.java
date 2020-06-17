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
  public static final String PLANT_ROLE = BASE_API_PATH + "plant-role";
  /*
   * plant Role Plant Permission
   */
  public static final String PLANT_ROLE_PLANT_PERMISSION =
      BASE_API_PATH + "plant-role-plant-permission";
  public static final String PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID =
      PLANT_ROLE_PLANT_PERMISSION + SLASH + "{plantRoleId}";
  public static final String PLANT_ROLE_PERMISSION_MODULE_STATUS =
      PLANT_ROLE_PLANT_PERMISSION + SLASH + "module" + SLASH + "status" + SLASH + "{plantRoleId}";

}
