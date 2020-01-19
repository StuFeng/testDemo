package com.example.demo1;

import java.util.Map;

import com.example.demo1.service.AbstractService;
import com.example.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @Value("${fsh.map}")
    private Map<String, String> fshMap;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello world!" + aa;
    }

    @RequestMapping("/hello/map")
    public String helloMap() {
        fshMap.forEach((k, v) -> {
            System.out.println("key:" + k + ", value" + v);
        });
        return "hello";
    }

    @RequestMapping("/test-1")
    public String test() {
        AbstractService service = AbstractService.getService(1);
        return service.test();
    }
}
