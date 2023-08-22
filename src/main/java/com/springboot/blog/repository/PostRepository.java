package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // This annotation is not needed because SimpleJpaRepository internally implements JpaRepository and is @Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
