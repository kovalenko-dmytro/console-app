package com.kovalenko.application.info;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.info.entity.Info;
import com.kovalenko.ioc.exception.BeanCreationException;

import java.util.List;

public interface ApiInfo {
    List<Info> getInfo() throws BeanCreationException, ApplicationException;
}
