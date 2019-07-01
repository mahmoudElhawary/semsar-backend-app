package com.egypt.daily.life.shopping.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class UserProducts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userProductsId;
	@OrderBy("productName asc")
	private String productName;

	private String size;

	private String defaultSize;

	private String color;

	private String quantity;

	@Lob
	@Basic(fetch = FetchType.EAGER)
	private byte[] productPhoto;

	private String productPhotoName;

	private int rating;

	@Column(name = "sellCount", nullable = false, columnDefinition = "bigint(20) default 0")
	private long selectedCount = 0;

	private String productSummary;

	private String productDescription;

	private Date productDate;

	private String Auther;

	private Date publishedDate;
	
	private String powercapacity;

	private String processor;

	private String space;

	private String ram;

	private String type;

	private int age;

	@Column(name = "productViews", nullable = false, columnDefinition = "bigint(20) default 0")
	private long productViews = 0;

	@Min(value = 0, message = "Product price must no be less then zero.")
	private double productPrice;

	private String productCondition;

	@OneToMany(mappedBy = "selectedUserProductsId")
	@JsonIgnore
	private List<SelectedUserProducts> selectedUserProducts;

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<ProductComment> productCommentList;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryId")
	private Category productCategory;

	@ManyToOne
	private User userClass;

	public Long getUserProductsId() {
		return userProductsId;
	}

	public void setUserProductsId(Long userProductsId) {
		this.userProductsId = userProductsId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDefaultSize() {
		return defaultSize;
	}

	public void setDefaultSize(String defaultSize) {
		this.defaultSize = defaultSize;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public byte[] getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(byte[] productPhoto) {
		this.productPhoto = productPhoto;
	}

	public String getProductPhotoName() {
		return productPhotoName;
	}

	public void setProductPhotoName(String productPhotoName) {
		this.productPhotoName = productPhotoName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	public String getProductSummary() {
		return productSummary;
	}

	public void setProductSummary(String productSummary) {
		this.productSummary = productSummary;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public long getProductViews() {
		return productViews;
	}

	public void setProductViews(long productViews) {
		this.productViews = productViews;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductCondition() {
		return productCondition;
	}

	public void setProductCondition(String productCondition) {
		this.productCondition = productCondition;
	}

	public List<ProductComment> getProductCommentList() {
		return productCommentList;
	}

	public void setProductCommentList(List<ProductComment> productCommentList) {
		this.productCommentList = productCommentList;
	}

	public Category getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(Category productCategory) {
		this.productCategory = productCategory;
	}

	public User getUserClass() {
		return userClass;
	}

	public void setUserClass(User userClass) {
		this.userClass = userClass;
	}

	public List<SelectedUserProducts> getSelectedUserProducts() {
		return selectedUserProducts;
	}

	public void setSelectedUserProducts(List<SelectedUserProducts> selectedUserProducts) {
		this.selectedUserProducts = selectedUserProducts;
	}

	public long getSelectedCount() {
		return selectedCount;
	}

	public void setSelectedCount(long selectedCount) {
		this.selectedCount = selectedCount;
	}

	public String getAuther() {
		return Auther;
	}

	public void setAuther(String auther) {
		Auther = auther;
	}

	public String getPowercapacity() {
		return powercapacity;
	}

	public void setPowercapacity(String powercapacity) {
		this.powercapacity = powercapacity;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
