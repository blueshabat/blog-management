package com.bisa.blog.management.blog_management.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bisa.blog.management.blog_management.model.validation.CommentScore;

import lombok.Data;

@Data
public class CommentRequest {
  
  @NotNull
  private Long blogId;

  @NotNull
  @CommentScore
  private int score;

  @NotBlank
  private String text;

  @NotBlank
  private String authorName;

  @NotBlank
  @Email
  private String authorEmail;

  @NotBlank
  private String authorCountry;

}
