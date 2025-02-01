package com.bisa.blog.management.blog_management.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CommentScoreValidator implements ConstraintValidator<CommentScore, Integer> {

  @Override
  public boolean isValid(Integer arg0, ConstraintValidatorContext arg1) {
    return arg0 >= 0 && arg0 <=10;
  }
}