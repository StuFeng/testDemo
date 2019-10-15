/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

/**
 * @author fengsihan
 * @date 2019-08-08 20:05
 * @desc TODO
 */
@Service
public class BizService implements BeanNameAware, BeanFactoryAware {
    private String beanName;

    private BeanFactory factory;

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.factory = beanFactory;
    }

    public BeanFactory getFactory() {
        return factory;
    }

    @Override
    public String toString() {
        return "----------------------->";
    }
}
