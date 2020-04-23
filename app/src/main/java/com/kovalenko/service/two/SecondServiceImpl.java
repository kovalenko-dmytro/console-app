package com.kovalenko.service.two;

import com.kovalenko.ioc.bean.factory.stereotype.Service;

@Service
public class SecondServiceImpl implements SecondService {
    @Override
    public void second1(String param1, String param2) {
        System.out.println("SecondServiceImpl second1(String param1, String param2) method invoke. Param is: " + param1 + ", " + param2);
    }

    @Override
    public void second2(String filePath) {
        System.out.println("FirstServiceImpl second2(String filePath) method invoke. Param is: " + filePath);
    }
}
