package com.egypt.daily.life.shopping.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egypt.daily.life.shopping.model.SelectedUserProducts;
import com.egypt.daily.life.shopping.repository.SelectedUserProductsRepository;

@Service
public class SelectedUserProductsServiceImpl implements SelectedUserProductsService {

	@Autowired
	private SelectedUserProductsRepository selectedUserProductsRepository ;

	@Override
	public List<SelectedUserProducts> findByUserId(Long id) {
		return selectedUserProductsRepository.findAllByUserId(id) ;
	}

	@Override
	public List<SelectedUserProducts> findByUserProductsUserClassId(Long id) {
		return selectedUserProductsRepository.findAllByUserProductsUserClassId(id);
	}

	@Override
	public SelectedUserProducts save(SelectedUserProducts selectedUserProducts) {
		selectedUserProducts.setSelectedDate(new Date());
		return selectedUserProductsRepository.save(selectedUserProducts);
	}

	@Override
	public SelectedUserProducts findById(Long id) {
		return selectedUserProductsRepository.findById(id).get();
	}
}
