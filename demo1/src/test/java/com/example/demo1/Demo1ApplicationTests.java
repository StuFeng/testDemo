package com.example.demo1;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo1.bean.BizService;
import com.example.demo1.test.TestFindPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {

    @Autowired
    BizService bizService;

//    @Test
//    public void contextLoads() throws IOException {
//
//
//    }

    @Test
    public void test2(){
        System.out.println(bizService.getBeanName());
        System.out.println(bizService.getFactory());
        System.out.println(bizService.getFactory().getBean(bizService.getBeanName()));
        System.out.println();
    }

}
