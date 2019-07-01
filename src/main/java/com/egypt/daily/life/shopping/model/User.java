package com.egypt.daily.life.shopping.model;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4120198217883192233L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private String gender;
	
	private String profilePrivacy ;
	
	private String phoneNumber;
	@Lob
    @Basic(fetch=FetchType.EAGER)
    private byte[]  userPhoto ;
	
	private String password;
	private boolean enabled;
	private String role;
	private Date createdDate;
	private Date updatedDate;

	private Long age ;
	
	@OneToMany(mappedBy = "user", targetEntity = UserMessages.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserMessages> userMessages;

	@OneToOne(targetEntity=UserSocialMedia.class,cascade=CascadeType.ALL) 
	private UserSocialMedia userSocialMedias;

	@OneToOne(targetEntity=UserAdress.class,cascade=CascadeType.ALL) 
	private UserAdress userAdresses;
	
	@OneToOne
	@JoinColumn(name = "cartId")
	@JsonIgnore
	private Cart cart;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<ProductComment> productComments;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ShippingAddress> shippingAddresses;
	
	
	@OneToMany(mappedBy = "userClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UserProducts> userProducts;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<SelectedUserProducts> selectedUserProducts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<ProductComment> getProductComments() {
		return productComments;
	}

	public void setProductComments(List<ProductComment> productComments) {
		this.productComments = productComments;
	}

	public List<ShippingAddress> getShippingAddresses() {
		return shippingAddresses;
	}

	public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
		this.shippingAddresses = shippingAddresses;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte[] getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(byte[] userPhoto) {
		this.userPhoto = userPhoto;
	}

	public UserAdress getUserAdresses() {
		return userAdresses;
	}

	public void setUserAdresses(UserAdress userAdresses) {
		this.userAdresses = userAdresses;
	}

	public List<UserMessages> getUserMessages() {
		return userMessages;
	}

	public void setUserMessages(List<UserMessages> userMessages) {
		this.userMessages = userMessages;
	}

	public List<UserProducts> getUserProducts() {
		return userProducts;
	}

	public void setUserProducts(List<UserProducts> userProducts) {
		this.userProducts = userProducts;
	}

	public UserSocialMedia getUserSocialMedias() {
		return userSocialMedias;
	}

	public void setUserSocialMedias(UserSocialMedia userSocialMedias) {
		this.userSocialMedias = userSocialMedias;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getProfilePrivacy() {
		return profilePrivacy;
	}

	public void setProfilePrivacy(String profilePrivacy) {
		this.profilePrivacy = profilePrivacy;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<SelectedUserProducts> getSelectedUserProducts() {
		return selectedUserProducts;
	}

	public void setSelectedUserProducts(List<SelectedUserProducts> selectedUserProducts) {
		this.selectedUserProducts = selectedUserProducts;
	}
	
	
}
