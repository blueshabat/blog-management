package com.bisa.blog.management.blog_management.service;

import org.springframework.http.ResponseEntity;

import com.bisa.blog.management.blog_management.model.Blog;
import com.bisa.blog.management.blog_management.payload.request.BlogRequest;
import com.bisa.blog.management.blog_management.payload.response.BlogResponse;
import com.bisa.blog.management.blog_management.payload.response.PagedResponse;

public interface BlogService {

  ResponseEntity<Blog> addBlog(BlogRequest blogRequest);

  ResponseEntity<BlogResponse> getBlog(Long id);

  PagedResponse<BlogResponse> getAllBlogs(int page, int size);

  ResponseEntity<Blog> updateBlog(Long id, BlogRequest blogRequest);
}
