package com.auth.security;

/**
 * Contains all the rest endpoint url constants
 */
public final class AuthEndpointURI {
  private static final String BASE_API_PATH = "/api/v1/";
  /*
   * private constructor to avoid instantiating this class.
   */
  private static final String SLASH = "/";
  private static final String ID = "/{id}";

  /*
   * Authentication
   * 
   */
  public static final String AUTH = BASE_API_PATH + "auth" + SLASH;
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
  public static final String GET_ROLE_BY_ID = ROLE + ID;
  public static final String DELETE_ROLE_BY_ID = ROLE + ID;
  /*
   * User
   */
  public static final String USER = BASE_API_PATH + "user";
  public static final String USERS = BASE_API_PATH + "users";
  public static final String USER_BY_ID = USER + ID;
  /*
   * Permission
   */
  public static final String PERMISSIONS = BASE_API_PATH + "permissions";

  private AuthEndpointURI() {}
}
