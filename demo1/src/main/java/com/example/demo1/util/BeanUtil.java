package com.example.demo1.util;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.*;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


/**
 * 获取bean工具类
 *
 * @author humin@didichuxing.com
 * @since 2017/9/11
 */
@Component("beanUtil")
@Slf4j
public class BeanUtil implements BeanFactoryPostProcessor, ApplicationContextAware, ApplicationEventPublisherAware {
    private static ApplicationEventPublisher applicationEventPublisher;

    private static ConfigurableListableBeanFactory beanFactory;

    private static BeanFactoryResolver beanFactoryResolver;

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    public static BeanFactoryResolver getBeanFactoryResolver() {
        return beanFactoryResolver;
    }


    private static void init(BeanFactory beanFactory) {
        BeanUtil.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
        beanFactoryResolver = new BeanFactoryResolver(beanFactory);

    }

    public static void reset(String beanName, Object bean) {
        unregister(beanName);
        register(beanName, bean);
    }

    /**
     * 重置一个bean并且应用
     *
     * @param beanName bean的名称
     * @param bean     bean
     */
    public static void resetAndApply(String beanName, Object bean) {
        unregister(beanName);
        register(beanName, bean);
        autowire();
    }

    /**
     * 注册一个bean
     *
     * @param beanName bean的名称
     * @param bean     bean
     */
    public static void register(String beanName, Object bean) {
        beanFactory.registerSingleton(beanName, bean);
    }

    /**
     * 移除注册一个bean
     *
     * @param beanName bean的名称
     */
    public static void unregister(String beanName) {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
        registry.removeBeanDefinition(beanName);
    }

    /**
     * 发射spring event
     *
     * @param event 事件
     */
    public static void publishEvent(ApplicationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    /**
     * 应用
     */
    public static void autowire() {
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object bean = beanFactory.getBean(beanName);
            if (bean == null) {
                continue;
            }
            if (AopUtils.isCglibProxy(bean)) {
                bean = ReflectUtil.getTargetObject(bean);
            }
            try {
                beanFactory.autowireBean(bean);
            } catch (Exception e) {
                log.debug("刷新Bean异常", e);
            }
        }
    }

    private static void setContext(ApplicationContext applicationContext) {
        BeanUtil.applicationContext = applicationContext;
    }

    private static void setEventPublisher(ApplicationEventPublisher publisher) {
        BeanUtil.applicationEventPublisher = publisher;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        init(beanFactory);
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        setContext(applicationContext);
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        setEventPublisher(applicationEventPublisher);
    }
}

