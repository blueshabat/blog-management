package com.bisa.blog.management.blog_management.model.audit;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class DateAudit implements Serializable{
  
  @CreationTimestamp
  private Instant createdAt;

  @UpdateTimestamp
  private Instant modificatedAt;
}
