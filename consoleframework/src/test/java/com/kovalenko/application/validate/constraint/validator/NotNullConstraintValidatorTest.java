package com.kovalenko.application.validate.constraint.validator;

import com.kovalenko.application.validate.constraint.ConstraintValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotNullConstraintValidatorTest {

    private ConstraintValidator constraintValidator = new NotNullConstraintValidator();

    @Test
    void whenArgIsNullShouldReturnFalse() {
        boolean actual = constraintValidator.isValid(null);
        assertFalse(actual);
    }

    @Test
    void whenArgIsNotNullShouldReturnTrue() {
        boolean actual = constraintValidator.isValid("");
        assertTrue(actual);
    }
}