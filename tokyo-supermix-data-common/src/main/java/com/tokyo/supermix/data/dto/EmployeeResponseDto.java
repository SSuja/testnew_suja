package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeResponseDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String address;
  private String plantName;
  private String designationName;
  private String plantCode;
  private Long designationId;
  private boolean hasUser;
  private String createdAt;
  private String updatedAt;
  private boolean isEnabled;
  private String profilePicPath;
  private boolean sentMail;
}
