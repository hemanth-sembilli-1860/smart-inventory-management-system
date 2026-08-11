package com.smartinventory.service;

import java.util.List;

import com.smartinventory.dto.ProductRequest;
import com.smartinventory.dto.ProductResponse;

public interface ProductService {
	ProductResponse createProduct(ProductRequest productRequest);
	ProductResponse findByProductId(Long productId);
	List<ProductResponse> getAllProducts();
	ProductResponse updateProduct(Long productId,ProductRequest productRequest);
	void deleteProduct(Long productId);
	List<ProductResponse> findByProductName(String productName); //May be some products have same names
	List<ProductResponse> findByProductNameContainingIgnoreCase(String productName);
	List<ProductResponse> getAllProductsByCategory(Long categoryId);
	List<ProductResponse> getLowStockProducts();
	List<ProductResponse> getAllProductsLessThan(Double price);
	List<ProductResponse> getAllProductsAboveThan(Double price);
	List<ProductResponse> getProductsBetween(Double minPrice,Double maxPrice);
	List<ProductResponse> sortProductsByPriceAsc();
	List<ProductResponse> sortProductsByPriceDesc();
	List<ProductResponse> getProductsByLatest();
 }
