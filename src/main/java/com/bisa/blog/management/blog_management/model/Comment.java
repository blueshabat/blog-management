package com.bisa.blog.management.blog_management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bisa.blog.management.blog_management.model.audit.DateAudit;
import com.bisa.blog.management.blog_management.model.validation.CommentScore;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Comment extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
  @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq", initialValue = 1, allocationSize = 1)
  private Long id;

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

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "blog_id")
  private Blog blog;

  public Blog getBlog() {
    return blog;
  }

  public void setBlog(Blog blog) {
    this.blog = blog;
  }
}
