package com.example.demo1.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 反射工具类
 *
 * @author humin@didichuxing.com
 * @since 2017/11/6
 */
@Slf4j
public class ReflectUtil {

    private ReflectUtil() {
    }

    /**
     * 获取被代理的对象
     *
     * @param candidate aop后的对象
     * @return 原始对象
     */
    public static Object getTargetObject(Object candidate) {
        try {
            if (AopUtils.isAopProxy(candidate) && (candidate instanceof Advised)) {
                return ((Advised) candidate).getTargetSource().getTarget();
            }
        } catch (Exception ex) {
            throw new IllegalStateException("获取被代理的对象失败", ex);
        }
        return candidate;
    }

    /**
     * 从代理对象获取实际对象
     *
     * @param proxy 被代理对象
     * @return 实际对象
     * @throws Exception 异常
     */
    public static Object getTarget(Object proxy) throws Exception {
        if (!AopUtils.isAopProxy(proxy)) {
            return proxy;
        }
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy);
        } else { //cglib
            return getCglibProxyTargetObject(proxy);
        }
    }

    /**
     * 从cglib代理对象获取实际对象
     *
     * @param proxy 被代理对象
     * @return 实际对象
     * @throws Exception 异常
     */
    public static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }

    /**
     * 从jdk懂得他代理获得被代理的实际对象
     *
     * @param proxy 被代理的对象
     * @return 实际对象
     * @throws Exception 异常
     */
    public static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
    }

    /**
     * 根据方法名称获得方法列表
     *
     * @param clazz 类
     * @param name  方法名
     * @return 方法列表
     */
    public static List<Method> getMethodsByName(Class<?> clazz, String name) {
        Method[] methods = clazz.getDeclaredMethods();
        if (methods == null) {
            return new ArrayList<>(0);
        }
        List<Method> results = new ArrayList<>(methods.length);
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                results.add(method);
            }
        }
        return results;
    }

    /**
     * 修改目标class的注解
     *
     * @param targetClass      目标class
     * @param targetAnnotation 目标注解
     * @param targetValue      修改后的注解
     */
    public static void modifyAnnotation(Class<?> targetClass, Class<? extends Annotation> targetAnnotation,
                                        Annotation targetValue) {
        try {
            Method method = Class.class.getDeclaredMethod("annotationData");
            method.setAccessible(true);

            Object annotationData = method.invoke(targetClass);

            Field annotations = annotationData.getClass().getDeclaredField("annotations");
            annotations.setAccessible(true);

            Map<Class<? extends Annotation>, Annotation> map =
                    (Map<Class<? extends Annotation>, Annotation>) annotations.get(annotationData);
            map.put(targetAnnotation, targetValue);
        } catch (Exception e) {
            log.debug("reflection access exception", e);
        }
    }

    /**
     * 通过class名字返回class
     *
     * @param name class的名字
     * @return class
     */
    public static Optional<Class<?>> forName(String name) {
        try {
            return Optional.of(Class.forName(name));
        } catch (ClassNotFoundException e) {
            log.debug("unable to find class:{}", name, e);
            return Optional.empty();
        }
    }

    /**
     * 通过class 产生一个实例
     *
     * @param clazz class
     * @return 实例
     */
    public static <T> Optional<T> newInstance(Class<T> clazz) {
        try {
            return Optional.of(clazz.newInstance());
        } catch (Exception e) {
            log.debug("unable to instance class:{}", clazz.getSimpleName(), e);
            return Optional.empty();
        }
    }

    /**
     * 判断一个class是否有对应的注解,会取其父类的注解
     *
     * @param clazz      class
     * @param annotation 注解
     * @return 一个class是否有对应的注解
     */
    public static <A extends Annotation> boolean isAnnotationPresent(Class<?> clazz, Class<A> annotation) {
        Class<?> superClass = clazz;
        while (superClass != Object.class) {
            if (superClass.isAnnotationPresent(annotation)) {
                return true;
            } else {
                superClass = superClass.getSuperclass();
            }
        }
        return false;
    }

    /**
     * 获得一个对象的成员,和官方不一样的是,会去取super的成员
     *
     * @param object    对象
     * @param fieldName 成员名称
     * @return 对象的成员
     */
    public static Field getDeclaredField(Object object, String fieldName) {
        Class superClass = object.getClass();
        while (superClass != Object.class) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException var4) {
                superClass = superClass.getSuperclass();
            }
        }

        return null;
    }

    /**
     * 设置一个成员变量的值, 和官方不一样的是,会去取super的成员
     *
     * @param object    对象
     * @param fieldName 成员名称
     * @param value     要设置的值
     */
    public static void setFieldValue(Object object, String fieldName, Object value) {
        Field field = getDeclaredField(object, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("从对象 [" + object + "] 无法找到该成员 [" + fieldName + "]");
        } else {
            makeAccessible(field);
            try {
                field.set(object, value);
            } catch (IllegalAccessException var5) {
                //just ignore
            }
        }
    }

    /**
     * 使一个成员变量可被访问
     *
     * @param field 成员
     */
    private static void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }


    /**
     * 获取一个对象的成员变量值
     *
     * @param object    对象
     * @param fieldName 成员变量名
     * @return 成员变量值
     */
    public static Object getFieldValue(Object object, String fieldName) {
        Field field = getDeclaredField(object, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("无法找到成员 [" + fieldName + "] 从对象 [" + object + "]");
        } else {
            makeAccessible(field);
            try {
                return field.get(object);
            } catch (IllegalAccessException var5) {
                return null;

            }
        }
    }

    /**
     * 调用指定名称的方法,和官方不一样的是能获取super的方法
     *
     * @param object         对象
     * @param methodName     方法名称
     * @param parameterTypes 参数类型
     * @param parameters     参数
     * @return 方法执行的结果
     * @throws InvocationTargetException 调用异常
     */
    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters)
            throws InvocationTargetException {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException("无法找到方法 [" + methodName + "] 从对象 [" + object + "]");
        } else {
            method.setAccessible(true);
            try {
                return method.invoke(object, parameters);
            } catch (IllegalAccessException e) {
                return null;
            }
        }
    }

    /**
     * 获取声明的方法,和官方不一样的是,能获得super的方法
     *
     * @param object         对象
     * @param methodName     方法名
     * @param parameterTypes 参数类型
     * @return 声明的方法
     */
    public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
        Class superClass = object.getClass();
        while (superClass != Object.class) {
            try {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException var5) {
                superClass = superClass.getSuperclass();
            }
        }

        return null;
    }

    /**
     * 获得父类的泛型
     *
     * @param clazz 子类class
     * @return 父类的泛型
     */
    public static Class getSuperClassGenericType(Class clazz) {
        return getSuperClassGenericType(clazz, 0);
    }

    /**
     * 获得父类的泛型
     *
     * @param clazz 子类class
     * @param index index
     * @return 父类泛型的class
     */
    public static Class getSuperClassGenericType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            log.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        } else {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            if (index < params.length && index >= 0) {
                if (!(params[index] instanceof Class)) {
                    log.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
                    return Object.class;
                } else {
                    return (Class) params[index];
                }
            } else {
                log.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                        + params.length);
                return Object.class;
            }
        }
    }
}
