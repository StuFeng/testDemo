package com.example.demo1.testasync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author fengsihan
 * @date 2019-04-26 15:02
 * @desc TODO
 */
@Service
public class AsyncService {

    private Logger logger = LoggerFactory.getLogger(AsyncService.class);
    private Object object = new Object();
    @Async("asyncServiceExecutor")
    public String getTest1(){
        synchronized(object){
            try {
                for (int i = 1;i <= 100;i++){
                    logger.info(Thread.currentThread().getName()+"----------异步：>"+i);
                    object.wait(100);
                }
                return "执行异步任务完毕";
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return Thread.currentThread().getName()+"执行完毕";
    }
}
