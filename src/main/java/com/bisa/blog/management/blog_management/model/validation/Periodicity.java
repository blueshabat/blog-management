package com.bisa.blog.management.blog_management.model.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PeriodicityValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Periodicity {
  String message() default "Only the following types are allowed: DIARIA, SEMANAL, MENSUAL";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
