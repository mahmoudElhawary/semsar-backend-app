package com.egypt.daily.life.shopping.service;

import java.util.List;

import com.egypt.daily.life.shopping.model.SelectedUserProducts;

public interface SelectedUserProductsService {

	List<SelectedUserProducts> findByUserId(Long id) ;
	List<SelectedUserProducts> findByUserProductsUserClassId(Long id) ;
	SelectedUserProducts save(SelectedUserProducts selectedUserProducts) ;
	SelectedUserProducts findById(Long id) ;
}
