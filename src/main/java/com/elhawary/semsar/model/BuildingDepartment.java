package com.elhawary.semsar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class BuildingDepartment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long buildingDepartmentId;
	
	private String buildingDepartment ;
	
	@OneToOne(targetEntity=Buildings.class) 
    private Buildings buildings;

	public Long getBuildingDepartmentId() {
		return buildingDepartmentId;
	}

	public void setBuildingDepartmentId(Long buildingDepartmentId) {
		this.buildingDepartmentId = buildingDepartmentId;
	}

	public String getBuildingDepartment() {
		return buildingDepartment;
	}

	public void setBuildingDepartment(String buildingDepartment) {
		this.buildingDepartment = buildingDepartment;
	}

	public Buildings getBuildings() {
		return buildings;
	}

	public void setBuildings(Buildings buildings) {
		this.buildings = buildings;
	}


}
