package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

//    @Query(value = "SELECT * FROM comments WHERE post_id = ?1", nativeQuery = true)
//    List<Comment> getCommentsByPostId(long id);

    List<Comment> findByPostId(long postId);
}
