package com.kovalenko.application.validate.constraint.validator;

import com.kovalenko.application.validate.constraint.ConstraintValidator;

public class NotEmptyConstraintValidator implements ConstraintValidator {

    @Override
    public boolean isValid(Object param) {
        return !((String)param).isEmpty();
    }
}
