package com.egypt.daily.life.shopping.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.egypt.daily.life.shopping.model.SelectedUserProducts;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.model.UserProducts;
import com.egypt.daily.life.shopping.service.SelectedUserProductsService;
import com.egypt.daily.life.shopping.service.UserProductService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SelectedUserProductsController {

	@Autowired
	private SelectedUserProductsService selectedUserProductsService;

	@Autowired
	private UserProductService userProductService ; 
	@GetMapping("/findByUserProductsUserByUserId/{id}")
	public ResponseEntity<List<SelectedUserProducts>> findByUserProductsUserByUserId(@PathVariable("id") Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		List<SelectedUserProducts> selectedUserProducts = selectedUserProductsService.findByUserProductsUserClassId(id);
		return new ResponseEntity<List<SelectedUserProducts>>(selectedUserProducts, HttpStatus.OK);
	}

	@GetMapping("/findSelectedProductsByUserId/{id}")
	public ResponseEntity<List<SelectedUserProducts>> findSelectedProductsByUserId(@PathVariable("id") Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		List<SelectedUserProducts> selectedUserProducts = selectedUserProductsService.findByUserId(id);
		return new ResponseEntity<List<SelectedUserProducts>>(selectedUserProducts, HttpStatus.OK);
	}

	@GetMapping("/findSelectedProductsById/{id}")
	public ResponseEntity<SelectedUserProducts> findSelectedProductsById(@PathVariable("id") Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		SelectedUserProducts selectedUserProducts = selectedUserProductsService.findById(id);
		return new ResponseEntity<SelectedUserProducts>(selectedUserProducts, HttpStatus.OK);
	}

	@PostMapping(value = "/addSelectedProducts")
	public ResponseEntity<SelectedUserProducts> addProductComment(@RequestParam("userProduct") String product,
			@RequestParam("user") String user) throws JsonParseException, JsonMappingException, IOException {
		if (product == null) {
			throw new NullPointerException();
		}
		UserProducts productData = new ObjectMapper().readValue(product, UserProducts.class);
		User userData = new ObjectMapper().readValue(user, User.class);
		UserProducts userProducts = userProductService.getProductById(productData.getUserProductsId()) ;
		userProducts.setSelectedCount(productData.getSelectedCount() + 1);
		SelectedUserProducts selectedUserProducts = new SelectedUserProducts();
		selectedUserProducts.setUser(userData);
		selectedUserProducts.setUserProducts(userProducts);
		userProductService.save(userProducts) ;
		SelectedUserProducts selectedUserProductsResponse = selectedUserProductsService.save(selectedUserProducts);
		return new ResponseEntity<SelectedUserProducts>(selectedUserProductsResponse, HttpStatus.OK);

	}
}
