package com.kovalenko.application.validate.constraint.validator;

import com.kovalenko.application.validate.constraint.ConstraintValidator;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilePathConstraintValidatorTest {

    private static final String DIRECTORY_PATH = Paths.get("src", "test", "resources").toString();
    private static final String REGULAR_FILE_PATH = Paths.get("src", "test", "resources", "testFile.txt").toString();

    private ConstraintValidator constraintValidator = new FilePathConstraintValidator();

    @Test
    void whenArgIsNullShouldReturnFalse() {
        boolean actual = constraintValidator.isValid(null);
        assertFalse(actual);
    }

    @Test
    void whenArgIsNotFilePathShouldReturnFalse() {
        boolean actual = constraintValidator.isValid("fail/path/to/file");
        assertFalse(actual);
    }

    @Test
    void whenArgIsDirectoryPathShouldReturnTrue() {
        boolean actual = constraintValidator.isValid(DIRECTORY_PATH);
        assertTrue(actual);
    }

    @Test
    void whenArgIsRegularFilePathShouldReturnTrue() {
        boolean actual = constraintValidator.isValid(REGULAR_FILE_PATH);
        assertTrue(actual);
    }
}