package com.smartinventory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartinventory.dto.ProductRequest;
import com.smartinventory.dto.ProductResponse;
import com.smartinventory.service.ProductService;

import jakarta.validation.Valid;

@RequestMapping("/products")
@RestController
public class ProductController {
	private ProductService productService;
	ProductController(ProductService productService){
		this.productService = productService;
	}
	@PostMapping
	public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest) {
		return productService.createProduct(productRequest);
	}

	@GetMapping("/{productId}")
	public ProductResponse findByProductId(@PathVariable Long productId) {
		return productService.findByProductId(productId);
	}

	@GetMapping
	public List<ProductResponse> getAllProducts() {
		return productService.getAllProducts();
	}
	@GetMapping("/low-stock")
	public List<ProductResponse> getLowStockProducts(){
		return productService.getLowStockProducts();
	}

	@PutMapping("/{productId}")
	public ProductResponse updateProduct(@PathVariable Long productId,@Valid @RequestBody ProductRequest productRequest) {
		return productService.updateProduct(productId, productRequest);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/name/{productName}")
	public List<ProductResponse> findByProductName(@PathVariable String productName) {
		return productService.findByProductName(productName);
	}

	@GetMapping("/searchHere/{productName}")
	public List<ProductResponse> findByProductNameContainingIgnoreCase(@PathVariable String productName) {
		return productService.findByProductNameContainingIgnoreCase(productName);
	}

	@GetMapping("/getProductsByCategory/{categoryId}")
	public List<ProductResponse> getAllProductsByCategory(@PathVariable Long categoryId) {
		return productService.getAllProductsByCategory(categoryId);
	}

	@GetMapping("/getProductsLessThan/{price}")
	public List<ProductResponse> getAllProductsLessThan(@PathVariable Double price) {
		return productService.getAllProductsLessThan(price);
	}

	@GetMapping("/getProductsAboveThan/{price}")
	public List<ProductResponse> getAllProductsAboveThan(@PathVariable Double price) {
		return productService.getAllProductsAboveThan(price);
	}

	@GetMapping("/price/{minPrice}/{maxPrice}")
	public List<ProductResponse> getProductsBetween(@PathVariable Double minPrice,@PathVariable Double maxPrice) {
		return productService.getProductsBetween(minPrice, maxPrice);
	}

	@GetMapping("/sortByAscPrice")
	public List<ProductResponse> sortProductsByPriceAsc() {
		return productService.sortProductsByPriceAsc();
	}

	@GetMapping("/sortByDescPrice")
	public List<ProductResponse> getProductsByPriceDesc() {
		return productService.sortProductsByPriceDesc();
	}

	@GetMapping("/sortByLatest")
	public List<ProductResponse> getProductsByLatest() {
		return productService.getProductsByLatest();
	}
}
