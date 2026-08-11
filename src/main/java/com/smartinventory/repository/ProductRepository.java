package com.smartinventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartinventory.entity.Category;
import com.smartinventory.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	List<Product> findByProductName(String productName);
	List<Product> findByCategory(Category category);
	List<Product> findByProductNameContainingIgnoreCase(String productName);
	List<Product> findByPriceLessThan(Double price);
	List<Product> findByPriceGreaterThan(Double price);
	List<Product> findByQuantityLessThanEqual(Integer Quantity);
	List<Product> findByPriceBetween(Double minPrice,Double maxPrice);
	
	//next---sorting by time,price,filter by price
}
