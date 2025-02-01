package com.bisa.blog.management.blog_management.payload.request;

import java.sql.Date;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AuthorRequest {

  @NotBlank
  @Size(min = 3, max = 50)
  private String name;

  @NotBlank
  @Size(min = 3, max = 50)
  private String firstLastName;

  @NotBlank
  @Size(min = 3, max = 50)
  private String secondLastName;

  @NotNull
  private Date birthdate;

  @NotBlank
  @Size(max = 50)
  private String country;

  @NotBlank
  @Size(min = 3, max = 50)
  @Email
  private String email;
}
