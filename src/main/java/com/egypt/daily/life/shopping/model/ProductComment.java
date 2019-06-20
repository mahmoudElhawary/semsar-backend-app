package com.egypt.daily.life.shopping.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column(columnDefinition = "TEXT")
    private String commentCotents;

//    private User from ;
    
    private int commentStarts;

    private long commentCount = 0 ;
    
    @Column(name="id")
    private long userId ;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userProductsId")
    private UserProducts userProducts;
    
    @ManyToOne
    private User user;

    @Column(columnDefinition="DATETIME")
    private Date commentDate;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getCommentCotents() {
		return commentCotents;
	}

	public void setCommentCotents(String commentCotents) {
		this.commentCotents = commentCotents;
	}

	public int getCommentStarts() {
		return commentStarts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCommentStarts(int commentStarts) {
		this.commentStarts = commentStarts;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public UserProducts getUserProducts() {
		return userProducts;
	}

	public void setUserProducts(UserProducts userProducts) {
		this.userProducts = userProducts;
	}

	public long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
   
}
