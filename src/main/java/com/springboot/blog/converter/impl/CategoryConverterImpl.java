package com.springboot.blog.converter.impl;

import com.springboot.blog.converter.CategoryConverter;
import com.springboot.blog.entity.Category;
import com.springboot.blog.payload.CategoryDto;
import org.springframework.stereotype.Service;

@Service
public class CategoryConverterImpl implements CategoryConverter {
    @Override
    public Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return category;
    }

    @Override
    public CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());

        return categoryDto;
    }
}
