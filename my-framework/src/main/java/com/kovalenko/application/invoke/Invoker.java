package com.kovalenko.application.invoke;

import com.kovalenko.application.exception.ApplicationException;

public interface Invoker<P1, P2> {
    Object invoke(P1 param1, P2 param2) throws ApplicationException;
}
