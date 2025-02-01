package com.bisa.blog.management.blog_management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bisa.blog.management.blog_management.model.Author;
import com.bisa.blog.management.blog_management.service.AuthorService;


@RestController
@RequestMapping("/api/authors")
public class AuthorController {
  
  @Autowired
  private AuthorService authorService;

  @PostMapping
  public ResponseEntity<Author> addAuthor(@Valid @RequestBody Author author) {
      return authorService.addAuthor(author);
  }

}
