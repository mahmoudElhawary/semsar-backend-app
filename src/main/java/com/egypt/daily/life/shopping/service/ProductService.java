package com.egypt.daily.life.shopping.service;

import java.util.List;

import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.Product;

public interface ProductService {

	void getVisited(Long productId);

	List<Product> getProductsByMainCategory(String mainCategoryName);

	List<Product> getProductsBySubCategory(String subCategoryName);

	List<Product> findAllByProductName(String name);

	Product findByProductNameIgnoreCase(String name);
	
	List<String> getAllNamesProductsByMainCategoryIgnoreCaseContaining(String mainCategoryName);

	List<String> getAllNamesProductsBySubCategoryIgnoreCaseContaining(String subCategoryName);

	List<String> findAllNamesByProductNameIgnoreCaseContaining(String name);

	List<Product> getProductsByMainCategoryIgnoreCaseContaining(String mainCategoryName);

	List<Product> getProductsBySubCategoryIgnoreCaseContaining(String subCategoryName);

	List<Product> findAllByProductNameIgnoreCaseContaining(String name);

	List<Product> getProductsByCategory(Category category);

	List<Product> findTop18ByOrderByProductDateDesc();

	List<Product> findTop18ByOrderBySellCountDesc();

	List<Product> findTop18ByOrderByRatingDesc();

	List<Product> findTop18ByOrderByViewsDesc();

	List<Product> findTop18ByOrderBySearchCountDesc();
	
	List<Product> findTop5ByOrderByProductPriceDesc();
	
    List<Product> findTop18ByOrderByProductCommentListDesc();

	List<Product> getAllProducts();

	List<Product> getAllProductsSlider(Boolean isSlider);

	Product getProductById(Long productId);

	Product save(Product product);

	void delete(Long productId);

	List<Product> sort(List<Product> products, String sortType);
}
