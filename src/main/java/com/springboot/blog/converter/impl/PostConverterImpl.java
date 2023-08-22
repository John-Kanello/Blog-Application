package com.springboot.blog.converter.impl;


import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.converter.CommentConverter;
import com.springboot.blog.converter.PostConverter;
import com.springboot.blog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PostConverterImpl implements PostConverter {

    private CommentConverter commentConverter;
    private CategoryRepository categoryRepository;

    public PostConverterImpl(CommentConverter commentConverter, CategoryRepository categoryRepository) {
        this.commentConverter = commentConverter;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setComments(postDto.getComments().stream()
                .map(commentDto -> commentConverter.mapToEntity(commentDto)).collect(Collectors.toSet()));

        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        post.setCategory(category);

        return post;
    }

    @Override
    public PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        postDto.setComments(post.getComments().stream()
                .map(comment -> commentConverter.mapToDto(comment)).collect(Collectors.toSet()));
        if(post.getCategory() != null) {
            postDto.setCategoryId(post.getCategory().getId());
        }
        return postDto;
    }
}
