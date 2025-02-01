package com.bisa.blog.management.blog_management.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bisa.blog.management.blog_management.model.validation.Periodicity;

import lombok.Data;

@Data
public class BlogRequest {

  @NotNull
  private Long authorId;

  @NotBlank
  private String title;

  @NotBlank
  private String topic;

  @NotBlank
  private String content;

  @NotBlank
  @Periodicity
  private String periodicity;
  
  @NotNull
  private Boolean allowComments;
}
