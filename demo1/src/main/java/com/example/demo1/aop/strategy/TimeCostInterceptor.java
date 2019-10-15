///*
// * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
// */
//package com.example.demo1.aop.strategy;
//
//import java.util.Date;
//import java.util.Enumeration;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartRequest;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.baidu.bjf.util.DateUtils;
//import com.google.common.collect.Maps;
//
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * TimeCostInterceptor
// *
// * @author : maduo
// * @date : 2018/9/25 4:10 PM
// */
//@Component
//@Slf4j
//@Order(1)
//public class TimeCostInterceptor implements HandlerInterceptor {
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        TimeCost.cleanUp();
//        TimeCost timeCost = TimeCost.getTimeCost();
//        timeCost.setStart(System.currentTimeMillis());
//        timeCost.setPath(request.getRequestURI());
//        Enumeration<String> enu = request.getParameterNames();
//        Map<String, Object> param = new ConcurrentHashMap();
//        while (enu.hasMoreElements()) {
//            String paraName = enu.nextElement();
//            param.put(paraName, request.getParameter(paraName));
//        }
//        if (request instanceof MultipartRequest) {
//            Map<String, MultipartFile> fileMap = ((MultipartRequest) request).getFileMap();
//            param.putAll(fileMap);
//        }
//        timeCost.setParam(param);
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//        TimeCost timeCost = TimeCost.getTimeCost();
//        timeCost.setEnd(System.currentTimeMillis());
//        log.info(timeCost.toString());
//        TimeCost.cleanUp();
//    }
//
//    @Data
//    public static class TimeCost {
//        public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
//
//        Long start;
//
//        Long end;
//
//        String path;
//
//        Map<String, Object> param;
//
//        private static final ThreadLocal<TimeCost> CURRENT_TIME_COST = ThreadLocal.withInitial(TimeCost::new);
//
//        public static TimeCost getTimeCost() {
//            return CURRENT_TIME_COST.get();
//        }
//
//        public static void cleanUp() {
//            CURRENT_TIME_COST.remove();
//        }
//
//        public Long getCost() {
//            if (end == null || start == null) {
//                return 0L;
//            }
//            return end - start;
//        }
//
//        @Override
//        public String toString() {
//            return "TimeCost{" +
//                    "path='" + path + '\'' +
//                    ", start=" + DateUtils.formatDate(new Date(start), DEFAULT_PATTERN) +
//                    ", end=" + DateUtils.formatDate(new Date(end), DEFAULT_PATTERN) +
//                    ", cost=" + getCost() +
//                    ", param=" + param +
//                    '}';
//        }
//    }
//}
