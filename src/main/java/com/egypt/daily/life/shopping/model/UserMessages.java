package com.egypt.daily.life.shopping.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class UserMessages implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3338891750849342285L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userMessagesId ;
	private String messages ;
	private Long senderId ;
	@Lob
    @Basic(fetch=FetchType.EAGER)
    private byte[]  messagePhoto ;
	private Date messageDate;
	@ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    private User user;

	public Long getUserMessagesId() {
		return userMessagesId;
	}

	public void setUserMessagesId(Long userMessagesId) {
		this.userMessagesId = userMessagesId;
	}

	
	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public byte[] getMessagePhoto() {
		return messagePhoto;
	}

	public void setMessagePhoto(byte[] messagePhoto) {
		this.messagePhoto = messagePhoto;
	}
	
}
