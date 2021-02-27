package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.RecipientType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailRecipientResponseDto {
  private Long id;
  private Long plantRoleId;
  private String plantRoleName;
  private Long userId;
  private String userName;
  private Long emailGroupId;
  private RecipientType recipientType;
}
