package test.common.service.impl;

import com.kovalenko.ioc.bean.factory.stereotype.Service;
import test.common.service.FirstTestService;

@Service
public class FirstTestServiceImpl implements FirstTestService {
    @Override
    public void test(String param1, String param2, String param3) {
        System.out.println(String.join(" ", param1, param2, param3));
    }
}
