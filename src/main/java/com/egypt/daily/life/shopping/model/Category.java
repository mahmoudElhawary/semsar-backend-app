package com.egypt.daily.life.shopping.model;

import java.io.Serializable;
import java.util.Date;
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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    private String mainCategoryName;
    private String subCategoryName;
    private Date createdDate ;
    private Date updatedDate ;
    
    @OneToMany(mappedBy = "productCategory")
    @JsonIgnore
    private List<Product> products;

    @OneToMany(mappedBy = "productCategory")
    @JsonIgnore
    private List<UserProducts> userProducts;
    
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

	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<UserProducts> getUserProducts() {
		return userProducts;
	}

	public void setUserProducts(List<UserProducts> userProducts) {
		this.userProducts = userProducts;
	}
    
    
}
