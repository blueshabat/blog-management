package com.bisa.blog.management.blog_management.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bisa.blog.management.blog_management.model.audit.DateAudit;
import com.bisa.blog.management.blog_management.model.validation.Periodicity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Blog extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_seq")
  @SequenceGenerator(name = "blog_seq", sequenceName = "blog_seq", initialValue = 1, allocationSize = 1)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private Author author;

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

  @JsonIgnore
  @OneToMany(mappedBy = "blog")
  private List<BlogHistory> blogHistory;

  @OneToMany(mappedBy = "blog")
  private List<Comment> comments;

  public List<Comment> getComments() {
    return comments == null ? null : new ArrayList<>(comments);
  }

  public void setComments(List<Comment> comments) {
    if (comments == null) {
      this.comments = null;
    } else {
      this.comments = Collections.unmodifiableList(comments);
    }
  }

  public List<BlogHistory> getBlogHistory() {
    return blogHistory == null ? null : new ArrayList<>(blogHistory);
  }

  public void setBlogHistory(List<BlogHistory> blogHistory) {
    if (blogHistory == null) {
      this.blogHistory = null;
    } else {
      this.blogHistory = Collections.unmodifiableList(blogHistory);
    }
  }
}
