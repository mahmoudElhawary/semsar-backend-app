package com.egypt.daily.life.shopping.service;

import java.util.List;

import com.egypt.daily.life.shopping.model.Cart;
import com.egypt.daily.life.shopping.model.User;

public interface CartService {
	
	void emptyCart(Cart cart);
	
	Cart save(Cart cart);
	
	Cart AddToCart(User user ,Long id , int amount) ;
	
	List<Cart> findAll();

	Cart findById(Long id);

	List<Cart> findAllByIsOrdered(boolean b) ;
	
	List<Cart> findAllByIsOrderedAndUserId(boolean b , Long id) ;
	
	Cart findCartByUserId(Long id) ;
	
	List<Cart> findByUser(User user) ;
	
	List<Cart> findByUserId(Long id) ;
	
	void delete(Long id);
}
