package com.bisa.blog.management.blog_management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bisa.blog.management.blog_management.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BlogHistory extends DateAudit{
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogHistory_seq")
  @SequenceGenerator(name = "blogHistory_seq", sequenceName = "blogHistory_seq", initialValue = 1, allocationSize = 1)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "blog_id")
  private Blog blog;

  @NotBlank
  private String title;

  @NotBlank
  private String topic;

  @NotBlank
  private String content;

  @NotBlank
  private String periodicity;

  @NotNull
  private Boolean allowComments;

}
