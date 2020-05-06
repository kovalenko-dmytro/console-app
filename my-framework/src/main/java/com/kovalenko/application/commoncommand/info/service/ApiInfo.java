package com.kovalenko.application.commoncommand.info.service;

import com.kovalenko.application.commoncommand.info.entity.Info;
import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.ioc.exception.BeanCreationException;

import java.util.List;

public interface ApiInfo {
    List<Info> getInfo() throws BeanCreationException, ApplicationException;
}
