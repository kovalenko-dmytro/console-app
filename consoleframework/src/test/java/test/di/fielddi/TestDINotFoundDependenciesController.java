package test.di.fielddi;

import com.kovalenko.ioc.bean.factory.annotation.Autowired;
import com.kovalenko.ioc.bean.factory.stereotype.Controller;

@Controller
public class TestDINotFoundDependenciesController {

    @Autowired
    private TestDINotFoundDependenciesService testDINotFoundDependenciesService;
}
