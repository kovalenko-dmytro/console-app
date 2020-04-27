package com.kovalenko.service.one;

import com.kovalenko.ioc.bean.factory.stereotype.Service;

@Service
public class ServiceWithoutInterface {

    public void doSomething() {
        System.out.println("invoke ServiceWithoutInterface.doSomething()");
    }
}
