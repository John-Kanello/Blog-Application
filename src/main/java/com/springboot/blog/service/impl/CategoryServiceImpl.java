package com.springboot.blog.service.impl;

import com.springboot.blog.converter.CategoryConverter;
import com.springboot.blog.entity.Category;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryConverter categoryConverter;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Category category = categoryConverter.mapToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);

        return categoryConverter.mapToDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return categoryConverter.mapToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> categoryConverter.mapToDto(category))
                .toList();
    }

    @Override
    public CategoryDto updateCategory(long categoryId, CategoryDto categoryDto) {

        Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        category = categoryConverter.mapToEntity(categoryDto);
        category.setId(categoryId);

        Category updatedCategory = categoryRepository.save(category);
        return categoryConverter.mapToDto(updatedCategory);
    }

    @Override
    public CategoryDto deleteCategory(long categoryId) {
        Category deletedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        categoryRepository.deleteById(categoryId);

        return categoryConverter.mapToDto(deletedCategory);
    }
}






