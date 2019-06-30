package com.egypt.daily.life.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.SelectedUserProducts;

@Repository
public interface SelectedUserProductsRepository extends CrudRepository<SelectedUserProducts, Long> {

	List<SelectedUserProducts> findAllByUserId(Long id) ;
	List<SelectedUserProducts> findAllByUserProductsUserClassId(Long id) ;
	
}
