package com.kovalenko.application.validate;

import com.kovalenko.application.exception.ApplicationException;

public interface Validator<P1, P2> {
    void validate(P1 param1, P2 param2) throws ApplicationException;
}
