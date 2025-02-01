package com.bisa.blog.management.blog_management.model.validation;

import static com.bisa.blog.management.blog_management.utils.AppConstants.ALLOWED_PERIODICITY_TYPES;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeriodicityValidator implements ConstraintValidator<Periodicity, String> {

  @Override
  public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
    return Arrays.asList(ALLOWED_PERIODICITY_TYPES).contains(arg0);
  }
}