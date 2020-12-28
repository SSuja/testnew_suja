package com.tokyo.supermix.data.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(schema = "tokyo-supermix", name = "mix_Design_TOKEN")
public class MixDesignConfirmationToken {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mixDesignTokenId;
	private String confirmationToken;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@OneToOne(targetEntity = MixDesign.class, fetch = FetchType.EAGER)
	  @JoinColumn(nullable = false, name = "mixDesign_id")
	  private MixDesign mixDesign;
	
	public MixDesignConfirmationToken() {}
	  public MixDesignConfirmationToken(MixDesign mixDesign) {
	      this. mixDesign =  mixDesign;
	      createdDate = new Date();
	      confirmationToken = UUID.randomUUID().toString();
	  }

	public Long getMixDesignTokenId() {
		return mixDesignTokenId;
	}

	public void setMixDesignTokenId(Long mixDesignTokenId) {
		this.mixDesignTokenId = mixDesignTokenId;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public MixDesign getMixDesign() {
		return mixDesign;
	}

	public void setMixDesign(MixDesign mixDesign) {
		this.mixDesign = mixDesign;
	}	

}
