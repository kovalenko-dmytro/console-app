package com.kovalenko.service.one;

import com.kovalenko.ioc.bean.factory.stereotype.Service;

@Service
public class FirstServiceAnotherImpl implements FirstService {

    @Override
    public void first(String param) {
        System.out.println("FirstServiceAnotherImpl first(String param) method invoke. Param is: " + param);
    }
}
