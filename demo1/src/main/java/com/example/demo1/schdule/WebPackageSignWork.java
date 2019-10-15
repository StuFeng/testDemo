package com.example.demo1.schdule;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * web包签名
 *
 * @author yangdongdong
 * @date 2018-12-13
 */
@Component
public class WebPackageSignWork implements InitializingBean {

    private static final String WEB_PACKAGE_SIGN_WORK_POLL = "web_package_sign_work_poll_thread";

    /**
     * 延迟时间
     */
    private static final int DELAY_TIME = 10;

    /**
     * 定时任务线程池
     */
    private ScheduledExecutorService webPackageSignWorkExecutorService = new ScheduledThreadPoolExecutor(1,
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, WEB_PACKAGE_SIGN_WORK_POLL);
                }
            });

    private static final String DEV_TYPE = "dev";

    private static final String REVIEW_TYPE = "review";

    @Override
    public void afterPropertiesSet() {

        // 延迟90秒执行
        webPackageSignWorkExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                WebPackageSignWork.this.processWebPackage();
            }
        }, DELAY_TIME, TimeUnit.SECONDS);
    }

    private void processWebPackage() {
        System.out.println("定时任务已经启动");
    }
}
