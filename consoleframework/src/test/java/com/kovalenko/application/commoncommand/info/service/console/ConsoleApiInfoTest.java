package com.kovalenko.application.commoncommand.info.service.console;

import com.kovalenko.application.BaseTest;
import com.kovalenko.application.commoncommand.info.entity.Info;
import com.kovalenko.application.commoncommand.info.service.ApiInfo;
import com.kovalenko.application.commoncommand.info.service.constant.ApiInfoConstant;
import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleApiInfoTest extends BaseTest {

    private static final String INFO_ANNOTATED_REQUEST_PATH = "test {-param1} {-param2} {-param3}";
    private static final String INFO_ANNOTATED_DESC = "test api";
    private static final String INFO_NOT_ANNOTATED_REQUEST_PATH = "test {-param}";

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