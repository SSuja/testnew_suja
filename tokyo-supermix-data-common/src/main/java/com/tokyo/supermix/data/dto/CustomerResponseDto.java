package com.tokyo.supermix.data.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerResponseDto {
  private Long id;
  private String name;
  private String phoneNumber;
  private String address;
  private String email;
  private List<PlantDto> plant;
  private String createdAt;
  private String updatedAt;
  private boolean sentMail;
}
