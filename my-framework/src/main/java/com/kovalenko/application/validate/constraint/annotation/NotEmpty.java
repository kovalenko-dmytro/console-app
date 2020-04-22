package com.kovalenko.application.validate.constraint.annotation;

import com.kovalenko.application.validate.annotation.Constraint;
import com.kovalenko.application.validate.annotation.Messaged;
import com.kovalenko.application.validate.constraint.validator.NotEmptyConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyConstraintValidator.class)
@Messaged(message = "cannot be empty")
public @interface NotEmpty {
}
