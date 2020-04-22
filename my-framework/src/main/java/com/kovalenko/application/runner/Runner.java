package com.kovalenko.application.runner;

import com.kovalenko.application.exception.ApplicationException;

public interface Runner {
    void run(String... args) throws ApplicationException;
}
