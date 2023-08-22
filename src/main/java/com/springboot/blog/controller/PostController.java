package com.springboot.blog.controller;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.springboot.blog.utils.AppConstants.*;

@Tag( // used for adding information to swagger-ui documentation
        name = "CRUD REST APIs for POST resource"
)
@RestController
@RequestMapping("api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation( // used for adding description to given endpoint in swagger-ui documentation
            summary = "Create Post Rest API",
            description = "Create Post REST API is used to save a post into the database "
    )
    @ApiResponse( // used to provide response information to swagger-ui documentation
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement( // used only to create documentation not for real Spring security functionality
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')") // Controller methods need to be public for this to work
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @Operation( // used for adding description to given endpoint in swagger-ui documentation
            summary = "Get All Post REST API",
            description = "Get all posts from the database"
    )
    @ApiResponse( // used to provide response information to swagger-ui documentation
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir));
    }

    @Operation( // used for adding description to given endpoint in swagger-ui documentation
            summary = "Get Post By Id Rest API",
            description = "Get Post By Id REST API is used to get a post by its id"
    )
    @ApiResponse( // used to provide response information to swagger-ui documentation
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }


    @Operation( // used for adding description to given endpoint in swagger-ui documentation
            summary = "Update Post REST API",
            description = "Update Post REST API is used to update an existing post"
    )
    @ApiResponse( // used to provide response information to swagger-ui documentation
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable long id, @Valid @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePost(id, postDto));
    }


    @Operation( // used for adding description to given endpoint in swagger-ui documentation
            summary = "Delete Post REST API",
            description = "Delete Post REST API is used to delete a post from the database"
    )
    @ApiResponse( // used to provide response information to swagger-ui documentation
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDto> deletePostById(@PathVariable long id) {
        return ResponseEntity.ok(postService.deleteById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable long categoryId) {
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);

        return ResponseEntity.ok(postDtos);
    }
}



















