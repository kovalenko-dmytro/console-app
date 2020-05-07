package com.kovalenko.application.validate.constraint.validator;

import com.kovalenko.application.validate.constraint.ConstraintValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotEmptyConstraintValidatorTest {

    private ConstraintValidator constraintValidator = new NotEmptyConstraintValidator();

    @Test
    void whenArgIsNullShouldReturnFalse() {
        boolean actual = constraintValidator.isValid(null);
        assertFalse(actual);
    }

    @Test
    void whenArgIsEmptyStringShouldReturnFalse() {
        boolean actual = constraintValidator.isValid("");
        assertFalse(actual);
    }

    @Test
    void whenArgIsSpaceStringShouldReturnTrue() {
        boolean actual = constraintValidator.isValid("  ");
        assertTrue(actual);
    }
}