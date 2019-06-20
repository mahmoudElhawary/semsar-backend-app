package com.egypt.daily.life.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.egypt.daily.life.shopping.model.Product;
import com.egypt.daily.life.shopping.service.ProductService;

@RestController
public class SearchController {

	@Autowired
	private ProductService productService;
	List<Product> products = new ArrayList<Product>();
	List<String> names = new ArrayList<String>();

	@PostMapping("/getRequiredProduct")
	public ResponseEntity<List<Product>> searchProducts(@RequestBody String name) {
		if (name.length() > 0) {
			products = productService.findAllByProductNameIgnoreCaseContaining(name);
			if (products.isEmpty()) {
				products.addAll(productService.getProductsBySubCategoryIgnoreCaseContaining(name));
			} else if (products.isEmpty()) {
				products.addAll(productService.getProductsByMainCategoryIgnoreCaseContaining(name));
			}
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} else {
			return null;
		}
	}
	@PostMapping("/getRequiredProductsName")
	public ResponseEntity<List<String>> getRequiredProductsName(@RequestBody String name) {
		if (name.length() > 0) {
			names = productService.findAllNamesByProductNameIgnoreCaseContaining(name);
			names.addAll(productService.getAllNamesProductsBySubCategoryIgnoreCaseContaining(name));
			names.addAll(productService.getAllNamesProductsByMainCategoryIgnoreCaseContaining(name));
			return new ResponseEntity<List<String>>(names, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@PostMapping("/searchForOneProduct")
	public ResponseEntity<Product> searchForOneProduct(@RequestBody String name) {
		if (name.length() > 0) {
			Product product = productService.findByProductNameIgnoreCase(name) ;
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {
			return null;
		}
	}
}
