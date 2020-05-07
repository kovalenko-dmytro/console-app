package test.di.superclassfielddi.qualifiernotexists;

import com.kovalenko.ioc.bean.factory.annotation.Autowired;
import com.kovalenko.ioc.bean.factory.stereotype.Controller;

@Controller
public class TestDIQualifierNotExistsController {

    @Autowired(fullQualifier = "foo.qualifier")
    private TestDIQualifierNotExistsService testDIQualifierNotExistsService;
}
