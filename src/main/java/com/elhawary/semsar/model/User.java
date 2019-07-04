package com.elhawary.semsar.model;

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
	
	private String phoneNumber;
	@Lob
    @Basic(fetch=FetchType.EAGER)
    private byte[]  userPhoto ;
	
	private String password;
	private boolean enabled;
	private String role;
	private Date createdDate;
	private Date updatedDate;

	
	@OneToMany(mappedBy = "user", targetEntity = UserMessages.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserMessages> userMessages;

	@OneToOne(targetEntity=UserSocialMedia.class,cascade=CascadeType.ALL) 
	private UserSocialMedia userSocialMedias;

	@OneToOne(targetEntity=UserAdress.class,cascade=CascadeType.ALL) 
	private UserAdress userAdresses;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Buildings> buildings;

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public byte[] getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(byte[] userPhoto) {
		this.userPhoto = userPhoto;
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

	public List<UserMessages> getUserMessages() {
		return userMessages;
	}

	public void setUserMessages(List<UserMessages> userMessages) {
		this.userMessages = userMessages;
	}

	public UserSocialMedia getUserSocialMedias() {
		return userSocialMedias;
	}

	public void setUserSocialMedias(UserSocialMedia userSocialMedias) {
		this.userSocialMedias = userSocialMedias;
	}

	public UserAdress getUserAdresses() {
		return userAdresses;
	}

	public void setUserAdresses(UserAdress userAdresses) {
		this.userAdresses = userAdresses;
	}
}
