package com.egypt.daily.life.shopping.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class UserContacts implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6291929431699084769L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userContactsId ;
	 
	private User contacts ;
	
	@ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    private User user;

	public Long getUserContactsId() {
		return userContactsId;
	}

	public void setUserContactsId(Long userContactsId) {
		this.userContactsId = userContactsId;
	}
	
	public User getContacts() {
		return contacts;
	}

	public void setContacts(User contacts) {
		this.contacts = contacts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
