package com.smartinventory.service;

import java.util.List;

import com.smartinventory.dto.CategoryRequest;
import com.smartinventory.dto.CategoryResponse;

public interface CategoryService {
	CategoryResponse createCategory(CategoryRequest categoryRequest);
	CategoryResponse getCategoryById(Long categoryId);
	List<CategoryResponse> getAllCategories();
	CategoryResponse updateCategory(Long categoryId,CategoryRequest categoryRequest);
	void deleteCategory(Long categoryId);
}
