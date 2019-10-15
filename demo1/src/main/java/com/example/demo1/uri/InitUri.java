/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.uri;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author fengsihan
 * @date 2019-06-06 16:02
 * @desc TODO
 */
public class InitUri {
    /**
     * web项目中所有的url集合
     */
    private static Set<String> uriList = Collections.newSetFromMap(new ConcurrentHashMap<>());

    @Autowired
    RequestMappingHandlerMapping handlerMapping;

    @PostConstruct
    public void initSiaUrl() {
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = handlerMapping.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()) {
            // 获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            for (String url : patterns) {
                System.out.println(url);
                uriList.add(url);
            }
        }
    }

    public static void main(String[] args) {
        Long a = null;
        Long b =1L;
        System.out.println(a | b);
    }
}
