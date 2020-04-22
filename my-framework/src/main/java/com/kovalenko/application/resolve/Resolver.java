package com.kovalenko.application.resolve;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.ioc.exception.BeanCreationException;

public interface Resolver<P, R> {
    R resolve(P request) throws ApplicationException, BeanCreationException;
}
