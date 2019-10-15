/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.aop;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.example.demo1.aop.annotation.Router;
import com.example.demo1.aop.strategy.ModStrategy;
import com.example.demo1.aop.strategy.StrategyEnum;

/**
 * 分表
 *
 * @author magenm
 * @date 2018/8/8 15:39
 */
@Component
@Aspect
public class TbRouteInterceptor extends BaseAspect {

    @Pointcut("@annotation(com.example.demo1.aop.annotation.Router)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroud(ProceedingJoinPoint jp) throws Throwable {

        Method method = getMethod(jp);
        Router router = method.getAnnotation(Router.class);
        String field = router.field();

        Object[] args = jp.getArgs();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                String fieldValue = BeanUtils.getProperty(arg, field);
                // 分表字段内容未设置，抛出异常
                if (StringUtils.isEmpty(fieldValue)) {
                    throw new Exception("分表参数异常");
                }

                if (router.strategy().getType() == StrategyEnum.MODE.getType()) {
                    String tableName = ModStrategy.calculate(router, fieldValue);
                    BeanUtils.setProperty(arg, "tableName", tableName);
                } else {
                    throw new Exception("不支持的分表策略");
                }

            }
        }

        return jp.proceed(args);
    }

}
