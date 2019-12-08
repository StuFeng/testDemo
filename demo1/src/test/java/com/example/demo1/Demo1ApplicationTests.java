package com.example.demo1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo1.bean.BizService;
import com.example.demo1.image.FileTypeEnum;
import com.example.demo1.image.UploadUtil;

import lombok.Data;

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
    public void test2() {
        System.out.println(bizService.getBeanName());
        System.out.println(bizService.getFactory());
        System.out.println(bizService.getFactory().getBean(bizService.getBeanName()));
        System.out.println();
    }

    @Test
    public void test4() throws IOException {
        BufferedInputStream bf = null;
        String img = "http://v-huya-img.huya.com/1949/241512748/4-640x360.jpg";
//        String img = "https://blog.csdn.net/qq_33302004/article/details/80439294";
        URL url = new URL(img);
        // 打开restful链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        // 设置访问提交模式，表单提交
        conn.setRequestProperty("User-Agent", "baiduspider");
        conn.setConnectTimeout(2000);
        conn.setReadTimeout(2000);

        bf = new BufferedInputStream(conn.getInputStream());
        FileTypeEnum fileType = UploadUtil.getFileType(bf);

        if (!fileType.equals(FileTypeEnum.jpg) && !fileType.equals(FileTypeEnum.png)) {
            System.out.println();
        }
    }

    @Data
    public static class MaterialExtVo {
        //        @JSONField(name = "card_info")
        //        private CardInfo cardInfo;
        @JSONField(name = "card_info")
        private Object cardInfo;

        @Data
        public class CardInfo {
        }
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
        } catch (Exception e) {

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

    class ABCPrint {
        private final Lock lock = new ReentrantLock();
        private int num;//当前执行标记

        public ABCPrint(int num) {
            super();
            this.num = num;
        }

        public void printA() {
            for (int i = 0; i < 2; ) {
                try {
                    lock.lock();
                    while (num == 1) {
                        System.out.println("A");
                        num = 2;
                        i++;

                    }
                } finally {
                    lock.unlock();

                }
            }
        }

        public void printB() {
            for (int i = 0; i < 2; ) {
                try {
                    lock.lock();
                    while (num == 2) {
                        System.out.println("B");
                        num = 3;
                        i++;
                    }
                } finally {
                    lock.unlock();

                }
            }
        }

        public void printC() {
            for (int i = 0; i < 2; ) {
                try {
                    lock.lock();
                    while (num == 3) {
                        System.out.println("C");
                        num = 1;
                        i++;
                    }
                } finally {
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
