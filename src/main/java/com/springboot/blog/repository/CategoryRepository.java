package com.springboot.blog.repository;

import com.springboot.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // We can omit this as JPA internally annotates JpaRepository interface with @Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


}
