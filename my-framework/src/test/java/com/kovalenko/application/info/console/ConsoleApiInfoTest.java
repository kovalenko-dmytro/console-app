package com.kovalenko.application.info.console;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.info.ApiInfo;
import com.kovalenko.application.info.constant.ApiInfoConstant;
import com.kovalenko.application.info.entity.Info;
import com.kovalenko.ioc.bean.factory.BeanFactory;
import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleApiInfoTest {

    private static final String INFO_ANNOTATED_REQUEST_PATH = "test {-param1} {-param2} {-param3}";
    private static final String INFO_ANNOTATED_DESC = "test api";
    private static final String INFO_NOT_ANNOTATED_REQUEST_PATH = "test {-param}";

    @BeforeAll
    static void setAll() throws BeanCreationException {
        BeanFactory.getInstance().init("bean");
    }

    @Test
    void getInfo() throws ApplicationException, BeanCreationException {
        ApiInfo apiInfo = new ConsoleApiInfo();
        List<Info> actual = apiInfo.getInfo();
        Info actualAnnotated = filterInfo(actual, INFO_ANNOTATED_REQUEST_PATH);
        Info actualDefaultInfo = filterInfo(actual, INFO_NOT_ANNOTATED_REQUEST_PATH);

        assertTrue(actual.size() > 0);

        assertNotNull(actualAnnotated);
        assertEquals(INFO_ANNOTATED_REQUEST_PATH, actualAnnotated.getApi());
        assertEquals(INFO_ANNOTATED_DESC, actualAnnotated.getDescription());
        assertFalse(actualAnnotated.getOperationParameters().isEmpty());

        assertNotNull(actualDefaultInfo);
        assertEquals(INFO_NOT_ANNOTATED_REQUEST_PATH, actualDefaultInfo.getApi());
        assertEquals(ApiInfoConstant.API_DEFAULT_DESCRIPTION.getValue(), actualDefaultInfo.getDescription());
        assertNull(actualDefaultInfo.getOperationParameters());
    }

    private Info filterInfo(List<Info> actual, String cause) {
        return actual.stream().filter(info -> info.getApi().equalsIgnoreCase(cause)).findFirst().orElse(null);
    }
}