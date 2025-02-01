package com.bisa.blog.management.blog_management.service;

import org.springframework.http.ResponseEntity;

import com.bisa.blog.management.blog_management.model.Author;

public interface AuthorService {
  
  ResponseEntity<Author> addAuthor(Author author);
}
