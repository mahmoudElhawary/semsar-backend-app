package com.elhawary.semsar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class BuildingAddressDB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long buildingAddressDBId;
	
	private String mainGovernorate ;
	private String subGovernorate ;
	private String neighborhood ;
	private Date createdDate ;
	private Date updatedDate ;
	public Long getBuildingAddressDBId() {
		return buildingAddressDBId;
	}
	public void setBuildingAddressDBId(Long buildingAddressDBId) {
		this.buildingAddressDBId = buildingAddressDBId;
	}
	public String getMainGovernorate() {
		return mainGovernorate;
	}
	public void setMainGovernorate(String mainGovernorate) {
		this.mainGovernorate = mainGovernorate;
	}
	public String getSubGovernorate() {
		return subGovernorate;
	}
	public void setSubGovernorate(String subGovernorate) {
		this.subGovernorate = subGovernorate;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
