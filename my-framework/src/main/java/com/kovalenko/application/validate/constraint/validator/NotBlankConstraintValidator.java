package com.kovalenko.application.validate.constraint.validator;

import com.kovalenko.application.validate.constraint.ConstraintValidator;

public class NotBlankConstraintValidator implements ConstraintValidator {

    @Override
    public boolean isValid(Object param) {
        return !((String)param).isBlank();
    }
}
