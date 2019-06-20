package com.egypt.daily.life.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.Product;
import com.egypt.daily.life.shopping.model.ProductComment;
import com.egypt.daily.life.shopping.model.UserProducts;

@Repository
public interface ProductCommentRepository extends CrudRepository<ProductComment, Long>{
	List<ProductComment> findAllByProduct(Product product);
	List<ProductComment> findAllByUserProducts(UserProducts product);
}
