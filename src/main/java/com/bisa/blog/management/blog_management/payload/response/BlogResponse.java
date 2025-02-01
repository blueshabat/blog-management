package com.bisa.blog.management.blog_management.payload.response;

import java.util.List;

import com.bisa.blog.management.blog_management.model.Author;
import com.bisa.blog.management.blog_management.model.Comment;

import lombok.Data;

@Data
public class BlogResponse {
  private Long id;
  private Author author;
  private String title;
  private String topic;
  private String content;
  private String periodicity;
  private Boolean allowComments;
  private int minScore;
  private int maxScore;
  private double averageScore;
  private List<Comment> comments;
}
