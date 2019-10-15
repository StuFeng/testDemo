package com.example.demo1;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author fengsihan
 * @date 2019-04-19 21:02
 * @desc TODO
 */
@Service
public class AaTask {

    @Scheduled(fixedRate = 1000)
    public void aa(){
        try {
            System.out.println("aa 任务开始！" + Thread.currentThread().getName());
            Thread.sleep(2000L);
            System.out.println("aa 任务结束！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 1000)
    public void bb(){
        System.out.println("bb 任务执行" + Thread.currentThread().getName());
    }


}
