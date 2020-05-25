package com.auth.security.util;

public class AuthConstants {
  // for email notification
  public static final String SUBJECT_NEW_USER = "Notification : Congratulations!";
  public static final String SUBJECT_FORGOT_PASSWORD="Forgot Password";
  //Authentication
  public static final String PASSWORD="passWord";
  public static final String EMAIL_OR_USERNAME="emailOrUserName";
  public static final String CREDENCIALS="credentials";
  public static final String UPDATE_PASSWORD_SUCCESS = "User Password Updated Successfully";
  public static final String GENERATE_PASSWORD_SUCCESS = "Random Password token Generate and mailed Successfully";
  public static final String MESSAGE_OF_FORGOT_PASSWORD="This the token for Reset your Password ";

  public static final String ROLE = "role";
  public static final String ROLES = "roles";
  public static final String ROLE_ID = "roleId";
  public static final String ADD_ROLE_SUCCESS = "Role Added Successfully";
  public static final String ROLE_DELETED = "Role Successfully Deleted";
  public static final String ROLE_UPDATED_SUCCESS = "Role Updated Successfully";
  
  public static final String USER_NAME = "username";
  public static final String USER = "user";
  public static final String USER_ID = "id";
  public static final String ADD_USER_SUCCESS = "User Added Successfully";
  public static final String UPDATE_USER_SUCCESS = "User Updated Successfully";
  public static final String DELETE_USER_SCCESS = "User Successfully Deleted";
  public static final String EMAIL = "email";
  public static final String EMPLOYEE = "employee";
	/*
	 * Encapsulate constructor to restrict modification from outside
	 */
	 
	private AuthConstants() {
	}
}
