package com.smartinventory.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.smartinventory.dto.CategoryProductResponse;
import com.smartinventory.dto.ProductRequest;
import com.smartinventory.dto.ProductResponse;
import com.smartinventory.entity.Category;
import com.smartinventory.entity.Product;
import com.smartinventory.exceptions.CategoryNotFoundException;
import com.smartinventory.exceptions.ProductNotFoundException;
import com.smartinventory.repository.CategoryRepository;
import com.smartinventory.repository.ProductRepository;
import com.smartinventory.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	public ProductServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
	private ProductResponse convertToProductResponse(Product product) {
		ProductResponse productResponse = new ProductResponse();
		CategoryProductResponse categoryProductResponse = new CategoryProductResponse();
		productResponse.setProductId(product.getProductId());
		productResponse.setProductName(product.getProductName());
		productResponse.setProductDesc(product.getProductDesc());
		categoryProductResponse.setCategoryId(product.getCategory().getCategoryId());
		categoryProductResponse.setCategoryName(product.getCategory().getCategoryName());
		productResponse.setCategoryProductResponse(categoryProductResponse);
		productResponse.setQuantity(product.getQuantity());
		productResponse.setPrice(product.getPrice());
		productResponse.setCreatedAt(product.getCreatedAt());
		productResponse.setUpdatedAt(product.getUpdatedAt());
		if (product.getQuantity() == 0) {
			productResponse.setStockStatus("OUT_OF_STOCK");
		}
		else if (product.getQuantity()<=10) {
			 productResponse.setStockStatus("LOW_STOCK");
		}
		else {
			productResponse.setStockStatus("AVAILABLE");
		}
		return productResponse;
	}
	@Override
	public ProductResponse createProduct(ProductRequest productRequest) {
		Category category = categoryRepository.findById(productRequest.getCategoryId())
				.orElseThrow(()-> new CategoryNotFoundException("Category Not Found"));
		Product product = new Product();
		product.setProductName(productRequest.getProductName());
		product.setProductDesc(productRequest.getProductDesc());
		product.setCategory(category);
		product.setQuantity(productRequest.getQuantity());
		product.setPrice(productRequest.getPrice());
		Product savedEntity = productRepository.save(product);
		return convertToProductResponse(savedEntity);
	}

	@Override
	public ProductResponse findByProductId(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(()-> new ProductNotFoundException("Product Not Found"));
		return convertToProductResponse(product);
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		return productRepository.findAll()
				.stream()
				.map(this::convertToProductResponse)
				.toList();
	}
	@Override
	public List<ProductResponse> getLowStockProducts() {
		return productRepository.findByQuantityLessThanEqual(10)
				.stream()
				.map(this::convertToProductResponse)
				.toList();
	}
	@Override
	public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
		Category category = categoryRepository.findById(productRequest.getCategoryId())
				.orElseThrow(()-> new CategoryNotFoundException("Category Not Found"));
		product.setProductName(productRequest.getProductName());
		product.setProductDesc(productRequest.getProductDesc());
		product.setCategory(category);
		product.setQuantity(productRequest.getQuantity());
		product.setPrice(productRequest.getPrice());
		Product savedEntity = productRepository.save(product);
		return convertToProductResponse(savedEntity);
	}

	@Override
	public void deleteProduct(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
		productRepository.delete(product);
		log.info("Product '{}' is deleted", product.getProductName());
	}

	@Override
	public List<ProductResponse> findByProductName(String productName) {
		return productRepository.findByProductName(productName)
				.stream()
				.map(this::convertToProductResponse)
				.toList();
	}
	@Override
	public List<ProductResponse> findByProductNameContainingIgnoreCase(String productName) {
		return productRepository.findByProductNameContainingIgnoreCase(productName)
				.stream()
				.map(this::convertToProductResponse)
				.toList();
	}

	@Override
	public List<ProductResponse> getAllProductsByCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(()-> new CategoryNotFoundException("Category Not Found"));
		return productRepository.findByCategory(category)
				.stream()
				.map(this::convertToProductResponse)
				.toList();
	}

	@Override
	public List<ProductResponse> getAllProductsLessThan(Double price) {
		return productRepository.findByPriceLessThan(price)
				.stream()
				.map(this::convertToProductResponse)
				.toList();
	}

	@Override
	public List<ProductResponse> getAllProductsAboveThan(Double price) {
		return productRepository.findByPriceGreaterThan(price)
				.stream()
				.map(this::convertToProductResponse)
				.toList();
	}

	@Override
	public List<ProductResponse> getProductsBetween(Double minPrice, Double maxPrice) {
		return productRepository.findByPriceBetween(minPrice, maxPrice)
				.stream()
				.map(this::convertToProductResponse)
				.toList();
	}

	@Override
	public List<ProductResponse> sortProductsByPriceAsc() {
		return productRepository.findAll(Sort.by("price").ascending())
				.stream()
				.map(this::convertToProductResponse)
				.toList();
	}

	@Override
	public List<ProductResponse> sortProductsByPriceDesc() {
		return productRepository.findAll(Sort.by("price").descending())
				.stream()
				.map(this::convertToProductResponse)
				.toList();
	}

	@Override
	public List<ProductResponse> getProductsByLatest() {
		return productRepository.findAll(Sort.by("createdAt").descending())
				.stream()
				.map(this::convertToProductResponse)
				.toList();
	}
}
