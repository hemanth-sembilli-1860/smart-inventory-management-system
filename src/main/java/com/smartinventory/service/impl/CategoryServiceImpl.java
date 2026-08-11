package com.smartinventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartinventory.dto.CategoryRequest;
import com.smartinventory.dto.CategoryResponse;
import com.smartinventory.entity.Category;
import com.smartinventory.exceptions.CategoryNotFoundException;
import com.smartinventory.repository.CategoryRepository;
import com.smartinventory.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
	private CategoryRepository categoryRepository;
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	@Override
	public CategoryResponse createCategory(CategoryRequest categoryRequest) {
		Category category = new Category();
		category.setCategoryName(categoryRequest.getCategoryName());
		category.setDescription(categoryRequest.getDescription());
		Category savedCategory = categoryRepository.save(category);
		return convertToCategoryResponse(savedCategory);
	}
	@Override
	public CategoryResponse getCategoryById(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
		        .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
		return convertToCategoryResponse(category);
	}
	private CategoryResponse convertToCategoryResponse(Category category) {
		CategoryResponse categoryResponse  = new CategoryResponse();
		categoryResponse.setCategoryId(category.getCategoryId());
		categoryResponse.setCategoryName(category.getCategoryName());
		categoryResponse.setDescription(category.getDescription());
		return categoryResponse;
	}
	@Override
	public List<CategoryResponse> getAllCategories(){
		return categoryRepository.findAll()
				.stream()
				.map(this::convertToCategoryResponse)
				.toList();
	}
	@Override
	public CategoryResponse updateCategory(Long categoryId,CategoryRequest categoryRequest) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException("Category Not Found"));
		category.setCategoryName(categoryRequest.getCategoryName());
		category.setDescription(categoryRequest.getDescription());
		Category updatedCategory = categoryRepository.save(category);
		return convertToCategoryResponse(updatedCategory);
	}
	@Override
	public void deleteCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException("Category Not Found"));
		categoryRepository.delete(category);
		log.info("Category '{}' is deleted", category.getCategoryName());
	}
}
