package com.kovalenko.application.validate.constraint.annotation;

import com.kovalenko.application.validate.annotation.Constraint;
import com.kovalenko.application.validate.annotation.Messaged;
import com.kovalenko.application.validate.constraint.validator.NotBlankConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotBlankConstraintValidator.class)
@Messaged(message = "cannot be blank")
public @interface NotBlank {
}
