package com.egypt.daily.life.shopping.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MainCategory {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    private String mainCategoryName;
    private String subCategoryName;
    private Date createdDate ;
    private Date updatedDate ;
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getMainCategoryName() {
		return mainCategoryName;
	}
	public void setMainCategoryName(String mainCategoryName) {
		this.mainCategoryName = mainCategoryName;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
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
