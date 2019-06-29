package com.egypt.daily.life.shopping.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.egypt.daily.life.shopping.domain.Response;
import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.Product;
import com.egypt.daily.life.shopping.model.ProductComment;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.model.UserProducts;
import com.egypt.daily.life.shopping.repository.ProductCommentRepository;
import com.egypt.daily.life.shopping.repository.UserProductRepository;
import com.egypt.daily.life.shopping.service.CategoryService;
import com.egypt.daily.life.shopping.service.UserProductService;
import com.egypt.daily.life.shopping.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AdminUserProductController {

	@Autowired
	private UserProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductCommentRepository productCommentRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserProductRepository productRepository;

	@GetMapping("/allUserProducts")
	public ResponseEntity<List<UserProducts>> getAllProducts() {
		List<UserProducts> products = productService.getAllProducts();
		return new ResponseEntity<List<UserProducts>>(products, HttpStatus.OK);
	}

	@PostMapping("/allUserProductsByMainCategory")
	public ResponseEntity<List<UserProducts>> getProductsByMainCategory(@RequestBody String mainCategory) {
		if (mainCategory != null) {
			List<UserProducts> products = productService.getProductsByMainCategory(mainCategory);
			return new ResponseEntity<List<UserProducts>>(products, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@PostMapping("/allUserProductsBySubCategory")
	public ResponseEntity<List<UserProducts>> getProductsBySubCategory(@RequestBody String subCategory) {
		if (subCategory != null) {
			List<UserProducts> products = productService.getProductsBySubCategory(subCategory);
			return new ResponseEntity<List<UserProducts>>(products, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@GetMapping("/getUserProductsById/{id}")
	public ResponseEntity<List<UserProducts>> getUserProducts(@PathVariable("id") Long id) {
		if (id != null) {
			List<UserProducts> products = productService.findAllByUserId(id);
			return new ResponseEntity<List<UserProducts>>(products, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@PostMapping("/saveUserProduct")
	public ResponseEntity<List<UserProducts>> createProduct(@RequestParam("productFile") MultipartFile productFile,
			@RequestParam("product") String product, @RequestParam("user") String user)
			throws JsonParseException, JsonMappingException, IOException {
		if (product != null) {
			// get product data from rest api
			UserProducts productData = new ObjectMapper().readValue(product, UserProducts.class);
			User userData = new ObjectMapper().readValue(user, User.class);
			// get photo data from file to product class
			productData.setProductPhotoName(productFile.getOriginalFilename());
			productData.setProductPhoto(productFile.getBytes());
			// save product data
			productService.save(productData);
			List<UserProducts> products = productService.findAllByUserId(userData.getId());
			return new ResponseEntity<List<UserProducts>>(products, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@PostMapping("/updateUserProduct")
	public ResponseEntity<List<UserProducts>> updateUserProduct(@RequestParam("productFile") MultipartFile productFile,
			@RequestParam("product") String product, @RequestParam("id") String id, @RequestParam("user") String user)
			throws JsonParseException, JsonMappingException, IOException {
		if (product != null) {
			// get product data from rest api
			UserProducts productData = new ObjectMapper().readValue(product, UserProducts.class);
			long proId = new ObjectMapper().readValue(id, Long.class);
			User userData = new ObjectMapper().readValue(user, User.class);
			UserProducts productDB = productService.getProductById(proId);
			// get photo data from file to product class
			if (productFile == null) {
				productDB.setProductPhotoName(productData.getProductPhotoName());
				productDB.setProductPhoto(productData.getProductPhoto());
			} else {
				productDB.setProductPhotoName(productFile.getOriginalFilename());
				productDB.setProductPhoto(productFile.getBytes());
			}

			productDB.setProductCategory(productData.getProductCategory());
			productDB.setColor(productData.getColor());
			productDB.setDefaultSize(productData.getDefaultSize());
			productDB.setProductCondition(productData.getProductCondition());
			productDB.setProductDate(new Date());
			productDB.setSize(productData.getSize());
			productDB.setProductSummary(productData.getProductSummary());
			productDB.setProductDescription(productData.getProductDescription());
			productDB.setProductName(productData.getProductName());
			productDB.setProductPrice(productData.getProductPrice());
			productDB.setQuantity(productData.getQuantity());
			// save product data
			productService.save(productDB);
			List<UserProducts> products = productService.findAllByUserId(userData.getId());
			;
			return new ResponseEntity<List<UserProducts>>(products, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@GetMapping("/deleteUserProduct/{id}")
	public ResponseEntity<Response> deleteProduct(@PathVariable("id") Long id) {
		if (id != null) {
			productService.delete(id);
			return new ResponseEntity<Response>(new Response("this product is deleted successfuly"), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response("you dont select an product"), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getUserProduct/{id}")
	public ResponseEntity<UserProducts> getProduct(@PathVariable("id") Long id) {
		if (id != null) {
			UserProducts productDB = productService.getProductById(id);
			return new ResponseEntity<UserProducts>(productDB, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@GetMapping("/maxRatingUserProducts")
	public ResponseEntity<List<UserProducts>> maxRatingProducts() {
		List<UserProducts> products = productService.findTop12ByOrderByRatingDesc();
		return new ResponseEntity<List<UserProducts>>(products, HttpStatus.OK);
	}

	@GetMapping("/maxSellingUserProducts")
	public ResponseEntity<List<UserProducts>> maxSellingProducts() {
		List<UserProducts> products = productService.findTop12ByOrderBySellCountDesc();
		return new ResponseEntity<List<UserProducts>>(products, HttpStatus.OK);
	}

	@GetMapping("/maxViewUserProducts")
	public ResponseEntity<List<UserProducts>> maxViewProducts() {
		List<UserProducts> products = productService.findTop12ByOrderByProductViewsDesc();
		return new ResponseEntity<List<UserProducts>>(products, HttpStatus.OK);
	}

	@GetMapping("/maxUserCommentProducts")
	public ResponseEntity<List<UserProducts>> maxCommentProducts() {
		List<UserProducts> products = productService.findTop12ByOrderByProductCommentListCommentCountDesc();
		return new ResponseEntity<List<UserProducts>>(products, HttpStatus.OK);
	}

	@GetMapping("/newestUserProducts")
	public ResponseEntity<List<UserProducts>> newestProducts() {
		List<UserProducts> products = productService.findTop12ByOrderByProductDateDesc();
		return new ResponseEntity<List<UserProducts>>(products, HttpStatus.OK);
	}

	@PostMapping(value = "/addUserProductComment")
	public ResponseEntity<List<ProductComment>> addProductComment(@RequestParam("product") String product,
			@RequestParam("comment") String comment, @RequestParam("commentFile") MultipartFile commentFile,
			@RequestParam("id") String idd) throws JsonParseException, JsonMappingException, IOException {
		if ((product != null) || (comment != null)) {
			UserProducts productData = new ObjectMapper().readValue(product, UserProducts.class);
			ProductComment productComment = new ObjectMapper().readValue(comment, ProductComment.class);
			long id = new ObjectMapper().readValue(idd, Long.class);
			User user = userService.findOne(id);
			if (commentFile == null) {
				productComment.setCommentPhoto(null);
			}
			productComment.setCommentPhoto(commentFile.getBytes());
			productComment.setCommentDate(new Date());
			productComment.setUserId(user.getId());
			productComment.setUser(user);
			productComment.setCommentCount(productComment.getCommentCount() + 1);
			UserProducts productValue = productService.getProductById(productData.getUserProductsId());
			productComment.setUserProducts(productValue);
			productCommentRepository.save(productComment);
			UserProducts productResponse = productService.getProductById(productData.getUserProductsId());
			List<ProductComment> comments = productCommentRepository.findAllByUserProducts(productResponse);
			return new ResponseEntity<List<ProductComment>>(comments, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@PostMapping(value = "/userProductRating")
	public ResponseEntity<UserProducts> userProductRating(@RequestParam("product") String productRest,
			@RequestParam("rating") String rating) throws JsonParseException, JsonMappingException, IOException {
		if (productRest != null && rating != null) {
			UserProducts product = new ObjectMapper().readValue(productRest, UserProducts.class);
			int rat = new ObjectMapper().readValue(rating, Integer.class);
			int rate = Math.round(((rat + product.getRating()) / 2));
			product.setRating(rate);
			productRepository.save(product);
			UserProducts productResponse = productService.getProductById(product.getUserProductsId());
			return new ResponseEntity<UserProducts>(productResponse, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@GetMapping("/userProductComments/{id}")
	public ResponseEntity<List<ProductComment>> getproductComments(@PathVariable("id") Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		UserProducts product = productService.getProductById(id);
		List<ProductComment> comments = productCommentRepository.findAllByUserProducts(product);
		return new ResponseEntity<List<ProductComment>>(comments, HttpStatus.OK);
	}
}
