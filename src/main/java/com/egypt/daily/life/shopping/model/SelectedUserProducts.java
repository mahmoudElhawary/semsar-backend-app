package com.egypt.daily.life.shopping.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class SelectedUserProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long selectedUserProductsId;
	
	@ManyToOne
    private User user;
	
	private Date selectedDate ;
	
	@ManyToOne
    @JoinColumn(name = "userProductsId")
    private UserProducts userProducts;

	public Long getSelectedUserProductsId() {
		return selectedUserProductsId;
	}

	public void setSelectedUserProductsId(Long selectedUserProductsId) {
		this.selectedUserProductsId = selectedUserProductsId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public UserProducts getUserProducts() {
		return userProducts;
	}

	public void setUserProducts(UserProducts userProducts) {
		this.userProducts = userProducts;
	}
	
}
