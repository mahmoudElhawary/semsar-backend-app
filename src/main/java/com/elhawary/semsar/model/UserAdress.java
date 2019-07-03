package com.elhawary.semsar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class UserAdress implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8610975649613041304L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userAdressId ;
    private String address;
    private String city;
    private String state;
    private String country;
    @OneToOne(targetEntity=User.class) 
    private User user;
    
    
	public Long getUserAdressId() {
		return userAdressId;
	}
	public void setUserAdressId(Long userAdressId) {
		this.userAdressId = userAdressId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    
    
}
