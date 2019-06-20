package com.egypt.daily.life.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.UserProducts;

@Repository
public interface UserProductRepository extends CrudRepository<UserProducts, Long>, PagingAndSortingRepository<UserProducts, Long>{
	
	List<UserProducts> findAllByUserClassId(Long id) ;
	
	List<UserProducts> findAllByProductCategory(Category category);
	
	List<UserProducts> findTop12ByOrderByProductDateDesc();

    List<UserProducts> findTop12ByOrderBySellCountDesc();

    List<UserProducts> findTop12ByOrderByProductCommentListCommentCountDesc();
    
    List<UserProducts> findTop12ByOrderByRatingDesc();
    
    List<UserProducts> findTop12ByOrderByProductViewsDesc();
    
    List<UserProducts> findAllByProductNameIgnoreCaseContaining(String name);
    
}
