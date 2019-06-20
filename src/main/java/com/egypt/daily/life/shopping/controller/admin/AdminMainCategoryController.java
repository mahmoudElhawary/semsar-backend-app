package com.egypt.daily.life.shopping.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egypt.daily.life.shopping.model.MainCategory;
import com.egypt.daily.life.shopping.repository.MainCategoryRepository;
import com.egypt.daily.life.shopping.service.MainCategoryService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AdminMainCategoryController {

	@Autowired
	private MainCategoryService categoryService;

	@Autowired
	private MainCategoryRepository mainCategoryRepository;

	@GetMapping("/allCategoriesMain")
	public ResponseEntity<List<MainCategory>> getAllCategory2() {
		List<MainCategory> categories = categoryService.getAllCategory();
		return new ResponseEntity<List<MainCategory>>(categories, HttpStatus.OK);
	}

	@GetMapping("/allMainCategoriesMain")
	public ResponseEntity<List<String>> getAllMainCategory2() {
		List<String> categories = categoryService.getAllMainCategory();
		return new ResponseEntity<List<String>>(categories, HttpStatus.OK);
	}

	@GetMapping("/allSubCategoriesMain")
	public ResponseEntity<List<String>> getAllSubCategory2() {
		List<String> categories = categoryService.getAllSubCategory();
		return new ResponseEntity<List<String>>(categories, HttpStatus.OK);
	}

	@PostMapping("/SubCategoriesByMainNameMain")
	public ResponseEntity<List<MainCategory>> getSubCategoryMain2(@RequestBody String mainCategory) {
		List<MainCategory> categories = categoryService.findAllByMainCategoryName(mainCategory);
		return new ResponseEntity<List<MainCategory>>(categories, HttpStatus.OK);
	}

	@PostMapping("/saveCategoryMain")
	public ResponseEntity<List<MainCategory>> createCategory2(@RequestBody MainCategory category) {
		if (category != null) {
			categoryService.save(category);
			List<MainCategory> mainCategories = categoryService.getAllCategory();
			return new ResponseEntity<List<MainCategory>>(mainCategories, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@PostMapping("/updateCategoryMain")
	public ResponseEntity<List<MainCategory>> updateCategoryMain(@RequestParam("category") String category,
			@RequestParam("id") String id) throws JsonParseException, JsonMappingException, IOException {
		if (category == null) {
			throw new NullPointerException();
		}
		MainCategory mainCategoryData = new ObjectMapper().readValue(category, MainCategory.class);
		long catId = new ObjectMapper().readValue(id, Long.class);

		MainCategory mainCategory = categoryService.getCategoryById(catId);
		mainCategory.setMainCategoryName(mainCategoryData.getMainCategoryName());
		mainCategory.setSubCategoryName(mainCategoryData.getSubCategoryName());
		categoryService.save(mainCategory);
		List<MainCategory> mainCategories = categoryService.getAllCategory();
		return new ResponseEntity<List<MainCategory>>(mainCategories, HttpStatus.OK);
	}

	@DeleteMapping("/deleteCategoryMain/{id}")
	public ResponseEntity<List<MainCategory>> deleteCategory2(@PathVariable("id") Long id) {
		if (id != null) {
			categoryService.delete(id);
			List<MainCategory> mainCategories = categoryService.getAllCategory();
			return new ResponseEntity<List<MainCategory>>(mainCategories, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@GetMapping("/getCategoryMain/{id}")
	public ResponseEntity<MainCategory> getCategory2(@PathVariable("id") Long id) {
		if (id != null) {
			MainCategory categoryDB = categoryService.getCategoryById(id);
			return new ResponseEntity<MainCategory>(categoryDB, HttpStatus.OK);
		} else {
			return null;
		}
	}
}
