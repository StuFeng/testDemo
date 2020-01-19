package com.example.demo1.advice;

import com.example.demo1.TestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 操作成功时，添加操作日志
 * 获取到方法具体的返回对象，根据对象判断是否进行操作日志的插入
 *
 */
@ControllerAdvice(basePackageClasses = {TestController.class})
@Slf4j
public class OperateLogsAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 直接判断操作日志类型，插入表中做记录
//        if (o instanceof BaseResponse && ((BaseResponse) o).isSuccess()) {
//            ActionEnum actionEnum = ActionEnum.fromMethodName(methodParameter.getMethod().getName());
//            if (actionEnum != null) {
//                addOperateLogs(serverHttpRequest, actionEnum);
//            } else {
//                log.error("addOperateLogs error:: ActionEnum not found for methodName:{}", methodParameter.getMethod().getName());
//            }
//        }
        return o;
    }

    private void addOperateLogs(ServerHttpRequest serverHttpRequest) {
        if (serverHttpRequest != null) {
            HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
            String taskId = request.getParameter("taskId");
            String operateUser = request.getParameter("operateUser");
            String remark = request.getParameter("remark");
            System.out.println("插入操作日志");
        }
    }

}

