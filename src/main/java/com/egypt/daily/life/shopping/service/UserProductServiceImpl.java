package com.egypt.daily.life.shopping.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.UserProducts;
import com.egypt.daily.life.shopping.repository.CategoryRepository;
import com.egypt.daily.life.shopping.repository.UserProductRepository;

@Service
public class UserProductServiceImpl implements UserProductService{
	
	@Autowired
	private UserProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void getVisited(Long productId){
		UserProducts product = productRepository.findById(productId).get();
		product.setProductViews(product.getProductViews()+1);
		productRepository.save(product);
	}
	
	public List<UserProducts> getProductsByMainCategory(String mainCategoryName){
		List<Category> categoryList = categoryRepository.findAllByMainCategoryName(mainCategoryName); 
		List<UserProducts> products = new ArrayList<UserProducts>();
		for(Category category: categoryList){
			for(UserProducts product : category.getUserProducts()){
				products.add(product);
			}
		}
		return products;
	}
	public List<UserProducts> getProductsBySubCategory(String subCategoryName){
		List<Category> categoryList = categoryRepository.findAllBySubCategoryName(subCategoryName); 
		List<UserProducts> products = new ArrayList<UserProducts>();
		for(Category category: categoryList){
			for(UserProducts product : category.getUserProducts()){
				products.add(product);
			}
		}
		return products;
	}
	@Override
	public List<String> getAllNamesProductsByMainCategoryIgnoreCaseContaining(String mainCategoryName) {
		List<Category> categoryList = categoryRepository.findAllByMainCategoryNameIgnoreCaseContaining(mainCategoryName); 
		List<String> products = new ArrayList<String>();
		for(Category category: categoryList){
			for(UserProducts product : category.getUserProducts()){
				products.add(product.getProductName());
			}
		}
		return products;
	}
	@Override
	public List<String> getAllNamesProductsBySubCategoryIgnoreCaseContaining(String subCategoryName) {
		List<Category> categoryList = categoryRepository.findAllBySubCategoryNameIgnoreCaseContaining(subCategoryName); 
		List<String> products = new ArrayList<String>();
		for(Category category: categoryList){
			for(UserProducts product : category.getUserProducts()){
				products.add(product.getProductName());
			}
		}
		return products;
	}
	@Override
	public List<String> findAllNamesByProductNameIgnoreCaseContaining(String name) {
		List<UserProducts> products= productRepository.findAllByProductNameIgnoreCaseContaining(name);
		List<String> names = new ArrayList<String>();
		for(UserProducts product : products){
			names.add(product.getProductName());
		}
		return names ;
	}
	@Override
	public List<UserProducts> getProductsByMainCategoryIgnoreCaseContaining(String mainCategoryName) {
		List<Category> categoryList = categoryRepository.findAllByMainCategoryNameIgnoreCaseContaining(mainCategoryName); 
		List<UserProducts> products = new ArrayList<UserProducts>();
		for(Category category: categoryList){
			for(UserProducts product : category.getUserProducts()){
				products.add(product);
			}
		}
		return products;
	}
	@Override
	public List<UserProducts> getProductsBySubCategoryIgnoreCaseContaining(String subCategoryName) {
		List<Category> categoryList = categoryRepository.findAllBySubCategoryNameIgnoreCaseContaining(subCategoryName); 
		List<UserProducts> products = new ArrayList<UserProducts>();
		for(Category category: categoryList){
			for(UserProducts product : category.getUserProducts()){
				products.add(product);
			}
		}
		return products;
	}
	@Override
	public List<UserProducts> findAllByProductNameIgnoreCaseContaining(String name) {
		List<UserProducts> products = productRepository.findAllByProductNameIgnoreCaseContaining(name) ;
		return products;
	}
	
	public List<UserProducts> getProductsByCategory(Category category){
		return productRepository.findAllByProductCategory(category);
	}
	
	public List<UserProducts> getAllProducts(){
		return  (List<UserProducts>) productRepository.findAll();
	}
	
	public UserProducts getProductById(Long productId){
		UserProducts product = productRepository.findById(productId).get();
		product.setProductViews(product.getProductViews()+1);
		productRepository.save(product);
		return product;
	}
	public UserProducts save(UserProducts product){
		product.setProductDate(new Date());
		return productRepository.save(product);
	}
	
	public void delete(Long productId){
		productRepository.deleteById(productId);
	}

	@Override
	public List<UserProducts> findTop12ByOrderByProductDateDesc() {
		return productRepository.findTop12ByOrderByProductDateDesc();
	}

	@Override
	public List<UserProducts> findTop12ByOrderBySellCountDesc() {
		return productRepository.findTop12ByOrderBySellCountDesc();
	}

	@Override
	public List<UserProducts> findTop12ByOrderByRatingDesc() {
		return productRepository.findTop12ByOrderByRatingDesc();
	}

	@Override
	public List<UserProducts> findAllByProductName(String name) {
		return productRepository.findAllByProductNameIgnoreCaseContaining(name);
	}
	
	@Override
	public List<UserProducts> findTop12ByOrderByProductCommentListCommentCountDesc() {
		return productRepository.findTop12ByOrderByProductCommentListCommentCountDesc();
	}

	@Override
	public List<UserProducts> findTop12ByOrderByProductViewsDesc() {
		return productRepository.findTop12ByOrderByProductViewsDesc();
	}

	@Override
	public List<UserProducts> findAllByUserId(Long id) {
		return productRepository.findAllByUserClassId(id);
	}
}
