package com.example.demo1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo1.bean.BizService;
import com.example.demo1.test.TestFindPath;

//@RunWith(SpringRunner.class)
//@SpringBootTest
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


    @Test
    public void test3() {
        try {
            BufferedReader in;
            InputStreamReader isr =
                    new InputStreamReader(new FileInputStream("/Users/baidu/Downloads/广西卫视离线数据.json"),
                            StandardCharsets.UTF_8);
            in = new BufferedReader(isr);
            FileWriter fw = new FileWriter(new File("/Users/baidu/Downloads/1.json"));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
                JSONObject object = JSONObject.parseObject(str);
                String params = object.getString("push_params");
                JSONObject map = JSONObject.parseObject(params);
                fw.write(JSON.toJSONString(map));
                fw.write("\n");
            }
            in.close();
            fw.close();
        }catch (Exception e){

        }

    }

    @Test
    public void test() throws InterruptedException {
        ABCPrint print = new ABCPrint(1);
        new Thread(() -> print.printA()).start();
        Thread.sleep(100);
        new Thread(() -> print.printB()).start();
        Thread.sleep(100);
        new Thread(print::printC).start();
    }

    class ABCPrint{
        private final Lock lock = new ReentrantLock();
        private  int num;//当前执行标记

        public ABCPrint(int num){
            super();
            this.num = num;
        }

        public void printA(){
            for(int i = 0;i<2;){
                try {
                    lock.lock();
                    while (num == 1){
                        System.out.println("A");
                        num =2;
                        i++;

                    }
                }finally {
                    lock.unlock();

                }
            }
        }
        public void printB(){
            for(int i = 0;i<2;){
                try {
                    lock.lock();
                    while (num == 2){
                        System.out.println("B");
                        num =3;
                        i++;
                    }
                }finally {
                    lock.unlock();

                }
            }
        }
        public void printC(){
            for(int i = 0;i<2;){
                try {
                    lock.lock();
                    while (num == 3){
                        System.out.println("C");
                        num =1;
                        i++;
                    }
                }finally {
                    lock.unlock();

                }
            }
        }

//        public void printA(){
//            while (true){
//                synchronized(this){
//                    while (num != 1){
//                        try {
//                            this.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    System.out.println("A");
//
//                    num =2;
//                    this.notifyAll();
//                }
//            }
//        }
//
//        public void printB(){
//            while (true) {
//                synchronized(this) {
//                    while (num != 2) {
//                        try {
//                            this.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    System.out.println("B");
//
//                    num = 3;
//                    this.notifyAll();
//                }
//            }
//        }
//
//        public void printC(){
//            while (true) {
//                synchronized(this) {
//                    while (num != 3) {
//                        try {
//                            this.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    System.out.println("C");
//
//                    num = 1;
//                    this.notifyAll();
//                }
//            }
//        }

    }


}
