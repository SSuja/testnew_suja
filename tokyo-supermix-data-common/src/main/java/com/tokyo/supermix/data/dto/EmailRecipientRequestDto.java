package com.tokyo.supermix.data.dto;

import java.util.List;
import com.tokyo.supermix.data.enums.RecipientType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailRecipientRequestDto {
  private Long id;
  private Long emailGroupId;
  private List<Long> plantRoleId;
  private List<Long> userId;
  private RecipientType recipientType;
}
