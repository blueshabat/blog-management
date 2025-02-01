package com.bisa.blog.management.blog_management.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bisa.blog.management.blog_management.model.Author;
import com.bisa.blog.management.blog_management.repository.AuthorRepository;
import com.bisa.blog.management.blog_management.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

  @Autowired
	private AuthorRepository authorRepository;

  @Override
  public ResponseEntity<Author> addAuthor(Author author) {
    Author newAuthor = authorRepository.save(author);
    return new ResponseEntity<>(newAuthor, HttpStatus.CREATED);
  }
  
}
