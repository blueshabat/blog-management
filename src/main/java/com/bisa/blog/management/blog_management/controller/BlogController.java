package com.bisa.blog.management.blog_management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bisa.blog.management.blog_management.model.Blog;
import com.bisa.blog.management.blog_management.payload.request.BlogRequest;
import com.bisa.blog.management.blog_management.payload.response.BlogResponse;
import com.bisa.blog.management.blog_management.payload.response.PagedResponse;
import com.bisa.blog.management.blog_management.service.BlogService;
import com.bisa.blog.management.blog_management.utils.AppConstants;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

  @Autowired
  BlogService blogService;

  @PostMapping
  public ResponseEntity<Blog> addBlog(@Valid @RequestBody BlogRequest blogRequest) {
    return blogService.addBlog(blogRequest);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BlogResponse> getBlog(@PathVariable(name = "id") Long id) {
    return blogService.getBlog(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Blog> updateBlog(@PathVariable(name = "id") Long id,
      @Valid @RequestBody BlogRequest blogRequest) {
    return blogService.updateBlog(id, blogRequest);
  }

  @GetMapping
  public ResponseEntity<PagedResponse<BlogResponse>> getAllBlogs(
      @RequestParam(value = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
      @RequestParam(value = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
    PagedResponse<BlogResponse> response = blogService.getAllBlogs(page, size);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
