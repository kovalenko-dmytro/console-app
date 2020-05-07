package test.di.superclassfielddi.notqualifier;

import com.kovalenko.ioc.bean.factory.annotation.Autowired;
import com.kovalenko.ioc.bean.factory.stereotype.Controller;

@Controller
public class TestDINotQualifierController {

    @Autowired
    private TestDINotQualifierService testDINotQualifierService;
}
