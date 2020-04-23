package com.kovalenko.service.one;

import com.kovalenko.ioc.bean.factory.stereotype.Service;

@Service
public class FirstServiceImpl implements FirstService {

    @Override
    public void first(String param) {
        System.out.println("FirstServiceImpl first(String param) method invoke. Param is: " + param);
    }
}
