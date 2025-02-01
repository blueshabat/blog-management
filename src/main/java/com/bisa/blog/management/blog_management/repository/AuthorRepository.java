package com.bisa.blog.management.blog_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bisa.blog.management.blog_management.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
}
