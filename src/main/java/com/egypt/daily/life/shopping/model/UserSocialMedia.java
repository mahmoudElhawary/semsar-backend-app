package com.egypt.daily.life.shopping.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class UserSocialMedia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5343659386576579875L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userSocialMediaId ;
	
	private String facebook;
	private String twitter;
	private String githup ;
	private String googlePlus;
	private String instagram ;
	private String linkedIn;

	@OneToOne(targetEntity=User.class) 
    private User user;

	public Long getUserSocialMediaId() {
		return userSocialMediaId;
	}

	public void setUserSocialMediaId(Long userSocialMediaId) {
		this.userSocialMediaId = userSocialMediaId;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getGithup() {
		return githup;
	}

	public void setGithup(String githup) {
		this.githup = githup;
	}

	public String getGooglePlus() {
		return googlePlus;
	}

	public void setGooglePlus(String googlePlus) {
		this.googlePlus = googlePlus;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
