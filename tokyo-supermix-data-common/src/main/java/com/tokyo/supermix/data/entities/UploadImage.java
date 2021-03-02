package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(schema = "tokyo-supermix", name = "upload_image")
public class UploadImage extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String testImage;
  private String name;
  @ManyToOne
  @JoinColumn(name = "materialTestCode", nullable = true)
  private MaterialTest materialTest;
  @ManyToOne
  @JoinColumn(name = "finishProductTestCode", nullable = true)
  private FinishProductTest finishProductTest;
  private String description;
}
