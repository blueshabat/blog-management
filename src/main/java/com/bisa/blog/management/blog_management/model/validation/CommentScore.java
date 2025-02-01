package com.bisa.blog.management.blog_management.model.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CommentScoreValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommentScore {
  String message() default "Comment score must be between 0 and 10";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
