package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Schema( // used to add information to the documentation of the swagger-ui
        description = "PostDto Model Information"
)
public class PostDto {

    private Long id;

    // title should not be empty or null
    // title should have at least 2 characters
    @Schema( // Used to add information to the swagger-ui documentation
            description = "Blog Post Title"
    )
    @NotEmpty // This annotation does not allow null or empty object
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    // description should be not null or empty
    // description should have at least 10 characters
    @Schema(
            description = "Blog Post Description"
    )
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    //content should be not null or empty
    @Schema(
            description = "Blog Post Content"
    )
    @NotEmpty
    private String content;

    private Set<CommentDto> comments = new HashSet<>();

    @Schema(
            description = "Blog Post Category"
    )
    private Long categoryId;

    public PostDto(Long id, String title, String description, String content, Set<CommentDto> comments, Long categoryId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.comments = comments;
        this.categoryId = categoryId;
    }

    public PostDto() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                ", categoryId=" + categoryId +
                '}';
    }
}
