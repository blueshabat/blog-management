package com.bisa.blog.management.blog_management.service;

import org.springframework.http.ResponseEntity;

import com.bisa.blog.management.blog_management.model.Comment;
import com.bisa.blog.management.blog_management.payload.request.CommentRequest;

public interface CommentService {
  ResponseEntity<Comment> addComment(CommentRequest commentRequest);  
}
