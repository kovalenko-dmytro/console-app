package com.kovalenko.application.info;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.ioc.exception.BeanCreationException;

public interface ApiInfo {
    void getInfo() throws BeanCreationException, ApplicationException;
}
