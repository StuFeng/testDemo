package com.example.demo1.service;

import com.example.demo1.util.BeanUtil;
import org.springframework.context.ApplicationContext;

public abstract class AbstractService {

    public static AbstractService getService(Integer code){
        ApplicationContext applicationContext = BeanUtil.getApplicationContext();
        return applicationContext.getBean("userService",
                AbstractService.class);


    }

    public abstract String test();
}
