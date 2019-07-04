package com.elhawary.semsar.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class BuildingAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long buildingAddressId;
	private String mainGovernorate ;
	private String subGovernorate ;
	private String neighborhood ;
	
	@OneToMany(mappedBy = "buildingsId")
    @JsonIgnore
    private List<Buildings> buildings;

	public Long getBuildingAddressId() {
		return buildingAddressId;
	}

	public void setBuildingAddressId(Long buildingAddressId) {
		this.buildingAddressId = buildingAddressId;
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

	public List<Buildings> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Buildings> buildings) {
		this.buildings = buildings;
	}
}
