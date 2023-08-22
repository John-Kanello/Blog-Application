package com.springboot.blog.entity;

import com.springboot.blog.payload.CommentDto;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String content;

    // This is Bidirectional mapping :
    //      1: We use Set to not allow duplicates.

    //      2: We use @OneToMany because one Post maps to multiple comments.

    //      3: mappedBy attribute is used to refer to the corresponding field in the 'ChildEntity' Comment
    //         that establishes the many-to-one relationship back to the 'ParentEntity' Post. We use only post
    //         in this mappedBy because it is the only relationship in our bidirectional relationship.

    //      4: CascadeType.ALL means that when you perform any operation (persist, merge, remove, refresh, and detach)
    //         one the 'ParentEntity' Post, the corresponding operation will be cascaded to the associated 'ChildEntity'
    //         Comment.

    //      5: orphanRemoval attribute is used in combination with the @OneToMany or @OneToOne relationship
    //         mapping annotations to specify whether orphaned child entities should be automatically removed
    //         when they are no longer referenced by their parent entities.
    //         An orphaned child entity is one whose parent entity reference has been set to null or
    //         replaced with a different parent entity.

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    // FetchType.LAZY: Whenever we fetch Post Entity the category object will not load immediately
    // In OneToMany mapping we have to create a foreign key in the child table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Post(Long id, String title, String description, String content, Set<Comment> comments, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.comments = comments;
        this.category = category;
    }

    public Post() {
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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                ", category=" + category +
                '}';
    }
}
