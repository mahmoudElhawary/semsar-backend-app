package com.egypt.daily.life.shopping.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.Product;
import com.egypt.daily.life.shopping.repository.CategoryRepository;
import com.egypt.daily.life.shopping.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public void getVisited(Long productId) {
		Product product = productRepository.findById(productId).get();
		product.setProductViews(product.getProductViews() + 1);
		productRepository.save(product);
	}

	public List<Product> getProductsByMainCategory(String mainCategoryName) {
		List<Category> categoryList = categoryRepository.findAllByMainCategoryName(mainCategoryName);
		List<Product> products = new ArrayList<Product>();
		for (Category category : categoryList) {
			for (Product product : category.getProducts()) {
				products.add(product);
			}
		}
		return products;
	}

	public List<Product> getProductsBySubCategory(String subCategoryName) {
		List<Category> categoryList = categoryRepository.findAllBySubCategoryName(subCategoryName);
		List<Product> products = new ArrayList<Product>();
		for (Category category : categoryList) {
			for (Product product : category.getProducts()) {
				products.add(product);
			}
		}
		return products;
	}

	@Override
	public List<String> getAllNamesProductsByMainCategoryIgnoreCaseContaining(String mainCategoryName) {
		List<Category> categoryList = categoryRepository
				.findAllByMainCategoryNameIgnoreCaseContaining(mainCategoryName);
		List<String> products = new ArrayList<String>();
		for (Category category : categoryList) {
			for (Product product : category.getProducts()) {
				products.add(product.getProductName());
			}
		}
		return products;
	}

	@Override
	public List<String> getAllNamesProductsBySubCategoryIgnoreCaseContaining(String subCategoryName) {
		List<Category> categoryList = categoryRepository.findAllBySubCategoryNameIgnoreCaseContaining(subCategoryName);
		List<String> products = new ArrayList<String>();
		for (Category category : categoryList) {
			for (Product product : category.getProducts()) {
				products.add(product.getProductName());
			}
		}
		return products;
	}

	@Override
	public List<String> findAllNamesByProductNameIgnoreCaseContaining(String name) {
		List<Product> products = productRepository.findAllByProductNameIgnoreCaseContaining(name);
		List<String> names = new ArrayList<String>();
		for (Product product : products) {
			names.add(product.getProductName());
		}
		return names;
	}

	@Override
	public List<Product> getProductsByMainCategoryIgnoreCaseContaining(String mainCategoryName) {
		List<Category> categoryList = categoryRepository
				.findAllByMainCategoryNameIgnoreCaseContaining(mainCategoryName);
		List<Product> products = new ArrayList<Product>();
		for (Category category : categoryList) {
			for (Product product : category.getProducts()) {
				product.setSearchCount(product.getSearchCount() + 1);
				productRepository.save(product);
				products.add(product);
			}
		}
		return products;
	}

	@Override
	public List<Product> getProductsBySubCategoryIgnoreCaseContaining(String subCategoryName) {
		List<Category> categoryList = categoryRepository.findAllBySubCategoryNameIgnoreCaseContaining(subCategoryName);
		List<Product> products = new ArrayList<Product>();
		for (Category category : categoryList) {
			for (Product product : category.getProducts()) {
				product.setSearchCount(product.getSearchCount() + 1);
				productRepository.save(product);
				products.add(product);
			}
		}
		return products;
	}

	@Override
	public List<Product> findAllByProductNameIgnoreCaseContaining(String name) {
		List<Product> products = productRepository.findAllByProductNameIgnoreCaseContaining(name);
		for (Product product : products) {
			product.setSearchCount(product.getSearchCount() + 1);
			productRepository.save(product);
		}
		return products;
	}

	@Override
	public Product findByProductNameIgnoreCase(String name) {
		Product product = productRepository.findByProductNameIgnoreCase(name);
		product.setSearchCount(product.getSearchCount() + 1);
		productRepository.save(product);
		return product;
	}
	public List<Product> sort(List<Product> products, String sortType) {
		// 0: Price ASC, 1: Price DESC
		if (sortType.equals("0")) {
			Collections.sort(products, Product.Comparators.PRICE);
		}
		if (sortType.equals("1")) {
			Collections.sort(products, Product.Comparators.PRICE);
			Collections.reverse(products);
		}
		return products;
	}

	public List<Product> getProductsByCategory(Category category) {
		return productRepository.findAllByProductCategory(category);
	}

	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	public Product getProductById(Long productId) {
		Product product = productRepository.findById(productId).get();
		return product;
	}

	public Product save(Product product) {
		product.setProductDate(new Date());
		return productRepository.save(product);
	}

	public void delete(Long productId) {
		productRepository.deleteById(productId);
	}

	@Override
	public List<Product> findTop18ByOrderByProductDateDesc() {
		return productRepository.findTop18ByOrderByProductDateDesc();
	}

	@Override
	public List<Product> findTop18ByOrderBySellCountDesc() {
		return productRepository.findTop18ByOrderBySellCountDesc();
	}

	@Override
	public List<Product> findTop18ByOrderByRatingDesc() {
		return productRepository.findTop18ByOrderByRatingDesc();
	}

	@Override
	public List<Product> findTop18ByOrderByViewsDesc() {
		return productRepository.findTop18ByOrderByProductViewsDesc();
	}

	@Override
	public List<Product> findTop18ByOrderByProductCommentListDesc() {
		return productRepository.findTop18ByOrderByProductCommentListCommentCountDesc();
	}

	@Override
	public List<Product> findTop18ByOrderBySearchCountDesc() {
		return productRepository.findTop18ByOrderBySearchCountDesc();
	}

	@Override
	public List<Product> getAllProductsSlider(Boolean isSlider) {
		return productRepository.findAllByIsSlider(isSlider);
	}

	@Override
	public List<Product> findAllByProductName(String name) {
		return productRepository.findAllByProductNameIgnoreCaseContaining(name);
	}

	@Override
	public List<Product> findTop5ByOrderByProductPriceDesc() {
		return productRepository.findTop5ByOrderByProductPriceDesc();
	}
}
