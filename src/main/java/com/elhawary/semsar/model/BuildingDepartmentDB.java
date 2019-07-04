package com.elhawary.semsar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class BuildingDepartmentDB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long buildingDepartmentDBId;
	
	private String buildingDepartment ;
	
	private Date createdDate ;
	
	private Date updatedDate ;

	public Long getBuildingDepartmentDBId() {
		return buildingDepartmentDBId;
	}

	public void setBuildingDepartmentDBId(Long buildingDepartmentDBId) {
		this.buildingDepartmentDBId = buildingDepartmentDBId;
	}

	public String getBuildingDepartment() {
		return buildingDepartment;
	}

	public void setBuildingDepartment(String buildingDepartment) {
		this.buildingDepartment = buildingDepartment;
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

