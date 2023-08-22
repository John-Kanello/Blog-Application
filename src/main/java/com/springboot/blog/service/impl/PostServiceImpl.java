package com.springboot.blog.service.impl;

import com.springboot.blog.converter.CategoryConverter;
import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.converter.PostConverter;
import com.springboot.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private PostConverter postConverter;
    private CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, PostConverter postConverter, CategoryConverter categoryConverter, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.postConverter = postConverter;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        // convert DTO to entity
        Post post = postConverter.mapToEntity(postDto);
        post.setCategory(category);

        Post newPost = postRepository.save(post);

        // convert the entity back to DTO
        return postConverter.mapToDto(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<Post> posts = postRepository.findAll(pageable);

        // get content from page object
        List<Post> postList = posts.getContent();

        List<PostDto> content = postList.stream()
                .map(post -> postConverter.mapToDto(post))
                .toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;

    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        return postConverter.mapToDto(post);
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
       Post post = postRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);
        Post updatedPost = postRepository.save(post);

        return postConverter.mapToDto(updatedPost);
    }

    @Override
    public PostDto deleteById(long id) {
        if(!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post", "id", id);
        }

        Post post = postRepository.findById(id).orElseThrow();
        postRepository.deleteById(id);

        return postConverter.mapToDto(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        List<Post> postsWithCategory = postRepository.findAll()
                .stream()
                .filter(post -> post.getCategory() != null && post.getCategory().getId().equals(category.getId()))
                .toList();

        return postsWithCategory.stream()
                .map(post -> postConverter.mapToDto(post))
                .toList();
    }
}

















