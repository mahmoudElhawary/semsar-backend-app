package com.egypt.daily.life.shopping.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egypt.daily.life.shopping.model.Cart;
import com.egypt.daily.life.shopping.model.CartItem;
import com.egypt.daily.life.shopping.model.Product;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.repository.CartItemRepository;
import com.egypt.daily.life.shopping.repository.CartRepository;
import com.egypt.daily.life.shopping.repository.ProductRepository;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository ;
	@Autowired
	private CartItemRepository cartItemRepository;
	
	public Cart getCartByCartId(Long cartId){
		return cartRepository.findById(cartId).get();
	}
	
	@Override
	public Cart AddToCart(User user, Long id, int amount) {
		if (amount <= 0 || id <= 0) {
            throw new IllegalArgumentException("Invalid parameters");
        }
		Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
//            cart.setCreateDate(new Date());
        } else if (cart.getCartItems() != null || !cart.getCartItems().isEmpty()) {
            for (CartItem i : cart.getCartItems()) {
                if (i.getProduct().getProductId().equals(id)) {
                    i.setQuantity(i.getQuantity() + amount);
                    cartRepository.save(cart);
                    return cart;
                }
            }
        }
        Optional optional = productRepository.findById(id);
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("Product not found.");
        }  
        Product product = (Product) optional.get() ;
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(amount);
        cartItem.setProduct(product);
        
      //this will save the cart object as well because there is cascading from cartItem
        cartItem.setCart(cart);
        if (cart.getCartItems() == null) {
            cart.setCartItems(new ArrayList<>());
        }
        cart.getCartItems().add(cartItem);
        cartItemRepository.save(cartItem);
        
		return cart;
	}
	
	public void emptyCart(Cart cart){
		for(CartItem cartItem : cart.getCartItems()){
			cartItemRepository.delete(cartItem);
		}
		cart.setGrandTotal(0);
		cartRepository.save(cart);
	}
	
	public Cart save(Cart cart){
		cart.setCreateDate(new Date());
		return cartRepository.save(cart);
	}

	@Override
	public List<Cart> findAll() {
		return (List<Cart>) cartRepository.findAll();
	}

	@Override
	public Cart findById(Long id) {
		return cartRepository.findById(id).get();
	}

	@Override
	public List<Cart> findByUser(User user) {
		return cartRepository.findByUser(user);
	}

	@Override
	public List<Cart> findByUserId(Long id) {
		return cartRepository.findAllByUserId(id);
	}

	@Override
	public void delete(Long id) {
		 cartRepository.deleteById(id);
	}

	@Override
	public Cart findCartByUserId(Long id) {
		return cartRepository.findByUserId(id);
	}

	@Override
	public List<Cart> findAllByIsOrdered(boolean b) {
		return cartRepository.findAllByIsOrdered(b);
	}

	@Override
	public List<Cart> findAllByIsOrderedAndUserId(boolean b, Long id) {
		return cartRepository.findAllByIsOrderedAndUserId(b, id);
	}
}
