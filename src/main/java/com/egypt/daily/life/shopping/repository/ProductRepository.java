package com.egypt.daily.life.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long>{
	
	List<Product> findAllByProductCategory(Category category);
	
	List<Product> findAllByIsSlider(boolean isSlider);
	
	List<Product> findTop18ByOrderByProductDateDesc();

    List<Product> findTop18ByOrderBySellCountDesc();
    
    List<Product> findTop18ByOrderBySearchCountDesc();

    List<Product> findTop18ByOrderByProductCommentListCommentCountDesc();
    
    List<Product> findTop18ByOrderByRatingDesc();
    
    List<Product> findTop18ByOrderByProductViewsDesc();
    
    List<Product> findTop5ByOrderByProductPriceDesc();
    
    List<Product> findAllByProductNameIgnoreCaseContaining(String name);
    
    Product findByProductNameIgnoreCase(String name);
}
