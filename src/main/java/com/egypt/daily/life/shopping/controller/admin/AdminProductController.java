package com.egypt.daily.life.shopping.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.Product;
import com.egypt.daily.life.shopping.model.ProductComment;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.repository.ProductCommentRepository;
import com.egypt.daily.life.shopping.repository.ProductRepository;
import com.egypt.daily.life.shopping.service.CategoryService;
import com.egypt.daily.life.shopping.service.ProductService;
import com.egypt.daily.life.shopping.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AdminProductController {

	@Autowired
	private ServletContext context;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductCommentRepository productCommentRepository;

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/allProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/sliderProducts")
	public ResponseEntity<List<Product>> getSliderProducts() {
		List<Product> products = productService.getAllProductsSlider(true);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@PostMapping("/allProductsByMainCategory")
	public ResponseEntity<List<Product>> getProductsByMainCategory(@RequestBody String mainCategory) {
		if (mainCategory != null) {
			List<Product> products = productService.getProductsByMainCategory(mainCategory);
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@PostMapping("/allProductsBySubCategory")
	public ResponseEntity<List<Product>> getProductsBySubCategory(@RequestBody String subCategory) {
		if (subCategory != null) {
			List<Product> products = productService.getProductsBySubCategory(subCategory);
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} else {
			return null;
		}

	}

	@PostMapping("/allProductsByCategory")
	public ResponseEntity<List<Product>> getProductsByCategory(@RequestBody Category category) {
		if (category != null) {
			Category categoryid = categoryService.getCategoryById(category.getCategoryId());
			List<Product> products = productService.getProductsByCategory(categoryid);
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} else {
			return null;
		}

	}

	@PostMapping("/saveProduct")
	public ResponseEntity<List<Product>> createProduct(@RequestParam("productFile") MultipartFile productFile,
			@RequestParam("product") String product) throws JsonParseException, JsonMappingException, IOException {
		if (product != null) {
			// get product data from rest api
			Product productData = new ObjectMapper().readValue(product, Product.class);
			// get photo data from file to product class
			productData.setProductPhotoName(productFile.getOriginalFilename());
			productData.setProductPhoto(productFile.getBytes());
			// save photo
//			boolean isExist = new File(context.getRealPath("/products/")).exists();
//			if (!isExist) {
//				new File(context.getRealPath("/products/")).mkdir();
//			}
//			File serverFile = new File(
//					context.getRealPath("/products/") + File.separator + productFile.getOriginalFilename());
//			System.out.println("/////////////" + serverFile);
//			try {
//				FileUtils.writeByteArrayToFile(serverFile, productFile.getBytes());
//			} catch (Exception e) {
//				System.out.println("admin controller - save product " + e.getMessage());
//			}

			// save product data
			productService.save(productData);
			List<Product> products = productService.getAllProducts();
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@PostMapping("/updateProduct")
	public ResponseEntity<List<Product>> updateProduct(@RequestParam("productFile") MultipartFile productFile,
			@RequestParam("product") String product, @RequestParam("id") String id)
			throws JsonParseException, JsonMappingException, IOException {
		if (product != null) {
			// get product data from rest api
			Product productData = new ObjectMapper().readValue(product, Product.class);
			long proId = new ObjectMapper().readValue(id, Long.class);

			Product productDB = productService.getProductById(proId);
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
			productDB.setIsSlider(productData.getIsSlider());
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
			List<Product> products = productService.getAllProducts();
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@GetMapping("/deleteProduct/{id}")
	public ResponseEntity<List<Product>> deleteProduct(@PathVariable("id") Long id) {
		if (id != null) {
			productService.delete(id);
			List<Product> products = productService.getAllProducts();
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@GetMapping("/getProduct/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
		if (id != null) {
			Product productDB = productService.getProductById(id);
			return new ResponseEntity<Product>(productDB, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@GetMapping(value = "/getProductsImages")
	@CrossOrigin
	public ResponseEntity<List<String>> getImage() throws IOException {

		List<String> images = new ArrayList<String>();

		String filePath = context.getRealPath("/products");
		File fileFolder = new File(filePath);

		if (fileFolder != null) {
			for (final File file : fileFolder.listFiles()) {
				if (!file.isDirectory()) {
					String encodeBase64 = null;
					try {
						String extention = FilenameUtils.getExtension(file.getName());
						FileInputStream fileInputStream = new FileInputStream(file);
						byte[] bytes = new byte[(int) file.length()];
						fileInputStream.read(bytes);
						encodeBase64 = Base64.getEncoder().encodeToString(bytes);
						images.add("data:Image/" + extention + ";base64," + encodeBase64);
						fileInputStream.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
		return new ResponseEntity<List<String>>(images, HttpStatus.OK);
	}

	@GetMapping("/maxRatingProducts")
	public ResponseEntity<List<Product>> maxRatingProducts() {
		List<Product> products = productService.findTop18ByOrderByRatingDesc();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/maxSellingProducts")
	public ResponseEntity<List<Product>> maxSellingProducts() {
		List<Product> products = productService.findTop18ByOrderBySellCountDesc();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/maxViewProducts")
	public ResponseEntity<List<Product>> maxViewProducts() {
		List<Product> products = productService.findTop18ByOrderByViewsDesc();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/maxCommentProducts")
	public ResponseEntity<List<Product>> maxCommentProducts() {
		List<Product> products = productService.findTop18ByOrderByProductCommentListDesc();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/newestProducts")
	public ResponseEntity<List<Product>> newestProducts() {
		List<Product> products = productService.findTop18ByOrderByProductDateDesc();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/maxProductsPrice")
	public ResponseEntity<List<Product>> maxProductsPrice() {
		List<Product> products = productService.findTop5ByOrderByProductPriceDesc();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/maxProductsSearch")
	public ResponseEntity<List<Product>> maxProductsSearch() {
		List<Product> products = productService.findTop18ByOrderBySearchCountDesc();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@PostMapping(value = "/productComment")
	public ResponseEntity<List<ProductComment>> addProductComment(@RequestParam("product") String product,
			@RequestParam("commentFile") MultipartFile commentFile, @RequestParam("comment") String comment,
			@RequestParam("id") String idd) throws JsonParseException, JsonMappingException, IOException {
		if ((product != null) || (comment != null)) {
			Product productData = new ObjectMapper().readValue(product, Product.class);
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
			Product productValue = productService.getProductById(productData.getProductId());
			productComment.setProduct(productValue);
			productCommentRepository.save(productComment);
			Product productResponse = productService.getProductById(productData.getProductId());
			List<ProductComment> comments = productCommentRepository.findAllByProduct(productResponse);
			return new ResponseEntity<List<ProductComment>>(comments, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@PostMapping(value = "/productRating")
	public ResponseEntity<Product> addproductRating(@RequestParam("product") String productRest,
			@RequestParam("rating") String rating) throws JsonParseException, JsonMappingException, IOException {
		if (productRest != null && rating != null) {
			Product product = new ObjectMapper().readValue(productRest, Product.class);
			int rat = new ObjectMapper().readValue(rating, Integer.class);
			int rate = Math.round(((rat + product.getRating()) / 2));
			product.setRating(rate);
			productRepository.save(product);
			Product productResponse = productService.getProductById(product.getProductId());
			return new ResponseEntity<Product>(productResponse, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@GetMapping("/productComments/{id}")
	public ResponseEntity<List<ProductComment>> getproductComments(@PathVariable("id") Long id) {
		if (id != null) {
			Product product = productService.getProductById(id);
			List<ProductComment> comments = productCommentRepository.findAllByProduct(product);
			return new ResponseEntity<List<ProductComment>>(comments, HttpStatus.OK);
		} else {
			return null;
		}

	}
}
