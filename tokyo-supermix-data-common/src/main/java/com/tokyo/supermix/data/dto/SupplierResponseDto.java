package com.tokyo.supermix.data.dto;

import java.util.List;
import com.tokyo.supermix.data.entities.SupplierCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SupplierResponseDto {
  private Long id;
  private String name;
  private String address;
  private String phoneNumber;
  private String email;
  private PlantDto plant;
  private List<SupplierCategory> supplierCategories;
  private String createdAt;
  private String updatedAt;
  private boolean sentMail;
}
