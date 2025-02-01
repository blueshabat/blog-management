package com.bisa.blog.management.blog_management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bisa.blog.management.blog_management.model.Comment;
import com.bisa.blog.management.blog_management.payload.request.CommentRequest;
import com.bisa.blog.management.blog_management.service.CommentService;


@RestController
@RequestMapping("/api/comments")
public class CommentController {

  @Autowired
  CommentService commentService;

  @PostMapping
  public ResponseEntity<Comment> addComment(@Valid @RequestBody CommentRequest commentRequest) {
      return commentService.addComment(commentRequest);
  }
}
