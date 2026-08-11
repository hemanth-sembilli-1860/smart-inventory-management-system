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

import com.smartinventory.dto.CategoryRequest;
import com.smartinventory.dto.CategoryResponse;
import com.smartinventory.service.CategoryService;

import jakarta.validation.Valid;

@RequestMapping("/categories")
@RestController
public class CategoryController {
	private CategoryService categoryService;
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	@PostMapping
	public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
		return categoryService.createCategory(categoryRequest);
	}
	
	@GetMapping("/{categoryId}")
	public CategoryResponse getCategoryById(@PathVariable Long categoryId) {
		return categoryService.getCategoryById(categoryId);
	}
	
	@GetMapping
	public List<CategoryResponse> getCategories(){
		return categoryService.getAllCategories();
	}
	
	@PutMapping("/{categoryId}")
	public CategoryResponse updateCategory(@PathVariable Long categoryId,@Valid @RequestBody CategoryRequest categoryRequest) {
		return categoryService.updateCategory(categoryId, categoryRequest);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
		categoryService.deleteCategory(categoryId);
		return ResponseEntity.noContent().build();
	}
}
