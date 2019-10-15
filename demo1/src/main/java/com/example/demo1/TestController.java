package com.example.demo1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengsihan
 * @date 2019-04-29 10:55
 * @desc TODO
 */
@RestController
public class TestController {

    @Value("${fsh.aa}")
    private String aa = "abc";

    @RequestMapping("/hello")
    public String hello(){
        return "Hello world!" + aa;
    }

    public static void main(String[] args) {
        String format = String.format("图片尺寸必须为%d*%d", 1, 2);
        System.out.println(format);
    }
}
