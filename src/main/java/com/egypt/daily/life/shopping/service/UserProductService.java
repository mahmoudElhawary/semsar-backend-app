package com.egypt.daily.life.shopping.service;

import java.util.List;

import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.model.UserProducts;

public interface UserProductService {

	void getVisited(Long productId);

	List<UserProducts> getProductsByMainCategory(String mainCategoryName);

	List<UserProducts> getProductsBySubCategory(String subCategoryName);

	List<UserProducts> findAllByProductName(String name);

	List<String> getAllNamesProductsByMainCategoryIgnoreCaseContaining(String mainCategoryName);

	List<String> getAllNamesProductsBySubCategoryIgnoreCaseContaining(String subCategoryName);

	List<String> findAllNamesByProductNameIgnoreCaseContaining(String name);

	List<UserProducts> getProductsByMainCategoryIgnoreCaseContaining(String mainCategoryName);

	List<UserProducts> getProductsBySubCategoryIgnoreCaseContaining(String subCategoryName);

	List<UserProducts> findAllByProductNameIgnoreCaseContaining(String name);

	List<UserProducts> getProductsByCategory(Category category);
 
	List<UserProducts> findTop12ByOrderByProductDateDesc();

    List<UserProducts> findTop12ByOrderBySelectedCountDesc();

    List<UserProducts> findTop12ByOrderByProductCommentListCommentCountDesc();
    
    List<UserProducts> findTop12ByOrderByRatingDesc();
    
    List<UserProducts> findTop12ByOrderByProductViewsDesc();

	List<UserProducts> getAllProducts();
	
	List<UserProducts> findAllByUserId(Long id) ;
	
	UserProducts getProductById(Long productId);

	UserProducts save(UserProducts product);

	void delete(Long productId);
}
