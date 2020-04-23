package com.kovalenko.application.validate.constraint.validator;

import com.kovalenko.application.validate.constraint.ConstraintValidator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FilePathConstraintValidator implements ConstraintValidator {

    @Override
    public boolean isValid(Object param) {
        if (Objects.isNull(param)) { return false; }
        Path path = Paths.get((String) param);
        return Files.isDirectory(path) || Files.isRegularFile(path);
    }
}
