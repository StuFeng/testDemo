package com.example.demo1.testasync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengsihan
 * @date 2019-04-26 15:02
 * @desc TODO
 */
@RestController
@EnableAsync
public class TestAsyncController {

    private Logger logger = LoggerFactory.getLogger(TestAsyncController.class);

    @Autowired
    AsyncService asyncService;

    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public String test1() {
        asyncService.getTest1();
        logger.info("============>" + Thread.currentThread().getName());
        return "异步,正在解析......";
    }
}
