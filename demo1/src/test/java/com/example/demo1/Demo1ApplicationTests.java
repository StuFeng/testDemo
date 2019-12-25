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
import java.util.Base64;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
//        BufferedInputStream bf = null;
//        String img = "http://v-huya-img.huya.com/1949/241512748/4-640x360.jpg";
////        String img = "https://blog.csdn.net/qq_33302004/article/details/80439294";
//        URL url = new URL(img);
//        // 打开restful链接
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        // 设置访问提交模式，表单提交
//        conn.setRequestProperty("User-Agent", "baiduspider");
//        conn.setConnectTimeout(2000);
//        conn.setReadTimeout(2000);
//
//        bf = new BufferedInputStream(conn.getInputStream());
//        FileTypeEnum fileType = UploadUtil.getFileType(bf);
//
//        if (!fileType.equals(FileTypeEnum.jpg) && !fileType.equals(FileTypeEnum.png)) {
//            System.out.println();
//        }

//        System.out.println(new Integer(1) == 1);
//        String aa  = "esoQIqEQghAZCgASB3pvbM7KtPAYAyUAAAAAKAEwtQpIB6oQgRB7ImV4dCI6eyJkZXNjcmlwdGlvbiI6IjfM9bvYtPCjuqG"
//                + "+zca89rTwsLihv8/Uv6i1xMf9tq+wstewtcSyu83q1fuwyaOsztK1xFdJTjEwz7XNs7C0V0lOK1C8/KOsv8nS1LWvs/bH0Lu70aHP7rXEIiwib3JpZ2luX2Rlc2NyaXB0aW9uIjoiN8z1u9i08KO6ob7Nxrz2tPCwuKG/z9S/qLXEx/22r7Cy17C1xLK7zerV+7DJo6zO0rXEV0lOMTDPtc2zsLRXSU4rULz8o6y/ydLUta+z9sfQu7vRoc/utcQiLCJvcmlnaW5fa2V5d29yZHMiOiLOqrrOV2luK1A6tPKyu7+qtuC5psTcz9TKvsPmsOUox9C7u8/Uyr7G9yIsInBhZ2VfaW1hZ2VzIjpbImh0dHBzOi8vbWVyY3J0LWZkLnpvbC1pbWcuY29tLmNuL3RfczIwMDB4MjAwMC9nNS9NMDAvMDAvMDAvQ2hNa0psM29ZMENJZnRsWkFBQzBqcUVCMFVBQUF2bFJBSm1pbjRBQUxTbTY2OC5qcGciLCJodHRwOi8vaWNvbi56b2wtaW1nLmNvbS5jbi9hc2svaGVhZGVyLzA5Ni5qcGciLCJodHRwOi8vaTkuYXNrLmZkLnpvbC1pbWcuY29tLmNuL2c1L00wMC8wMi8wQi9DaE1rSjFvSHY4bUlDMUxkQUFCQUdpcHBzNWNBQWlGaEFHbUk2SUFBRUF5NzY5LmpwZyIsImh0dHA6Ly9pY29uLnpvbC1pbWcuY29tLmNuL2Fzay9oZWFkZXIvMjU3LmpwZyIsImh0dHA6Ly9pY29uLnpvbC1pbWcuY29tLmNuL2Fzay9oZWFkZXIvMTUxLmpwZyIsImh0dHA6Ly9pY29uLnpvbC1pbWcuY29tLmNuL2Fzay9oZWFkZXIvMjI0LmpwZyIsImh0dHA6Ly9pY29uLnpvbC1pbWcuY29tLmNuL2Fzay9oZWFkZXIvMjMxLmpwZyIsImh0dHA6Ly9pY29uLnpvbC1pbWcuY29tLmNuL2Fzay9oZWFkZXIvMTAwLnBuZyIsImh0dHA6Ly9pY29uLnpvbC1pbWcuY29tLmNuL2Fzay9oZWFkZXIvMDI5LmpwZyIsImh0dHA6Ly9pY29uLnpvbC1pbWcuY29tLmNuL2Fzay9oZWFkZXIvMTU0LmpwZyIsImh0dHA6Ly9pY29uLnpvbC1pbWcuY29tLmNuL2Fzay9oZWFkZXIvMTMyLmpwZyIsImh0dHA6Ly9pY29uLnpvbC1pbWcuY29tLmNuL2Fzay9oZWFkZXIvMjA4LmpwZyIsImh0dHA6Ly9pY29uLnpvbC1pbWcuY29tLmNuL2Fzay9oZWFkZXIvMTQxLmpwZyIsImh0dHA6Ly9pY29uLnpvbC1pbWcuY29tLmNuL2Fzay9oZWFkZXIvMTcwLmpwZyIsImh0dHBzOi8vYXNrLWZkLnpvbC1pbWcuY29tLmNuL3RfczgwMHg4MDAvZzUvTTAwLzBGLzA3L0NoTWtKMTNsMEhpSVFydWpBQURMejlXdWxKRUFBdmpMZ08yTDRrQUFNdm4yOTkuanBnIiwiaHR0cDovL2k0LmFzay5mZC56b2wtaW1nLmNvbS5jbi90X3M4MDB4ODAwL2c1L00wMC8wRC8wRC9DaE1rSmwzZU4wU0lOYmZUQUFBMXI0WWQwajBBQXZjMEFMcWkza0FBRFhIMTg0LmpwZyIsImh0dHBzOi8vYXNrLWZkLnpvbC1pbWcuY29tLmNuL3RfczgwMHg4MDAvZzUvTTAwLzBELzA4L0NoTWtKMTNjNVpTSVBPbXJBQUNuemM0SExEQUFBdmJnZ0R1Y2FFQUFLZmwyNTQuanBnIiwiaHR0cHM6Ly9hc2stZmQuem9sLWltZy5jb20uY24vdF9zODAweDgwMC9nNS9NMDAvMDAvMDYvQ2hNa0oxM3FBcWlJZFVaZkFBQnB4OW84NUUwQUF2bTRBUEFsdnNBQUduZjk5OC5qcGciLCJodHRwOi8vaTQuYXNrLmZkLnpvbC1pbWcuY29tLmNuL2c1L00wMC8wRi8wQS9DaE1rSjEzbVpBT0lkeWxuQUFGdERGNkNkb3NBQXZqeVFJanB1TUFBVzBrMTk0LmpwZyIsImh0dHBzOi8vaWNvbi56b2wtaW1nLmNvbS5jbi9tcC9iYWlkdS5uZXdzL2ltZy9jb21wb25lbnRzL2JvdHRvbVRvb2xiYXIvaW1hZ2VzL2luZGV4LnBuZyIsImh0dHBzOi8vaWNvbi56b2wtaW1nLmNvbS5jbi9tcC9iYWlkdS5uZXdzL2ltZy9jb21wb25lbnRzL2JvdHRvbVRvb2xiYXIvaW1hZ2VzL3Byb2R1Y3QucG5nIiwiaHR0cHM6Ly9pY29uLnpvbC1pbWcuY29tLmNuL21wL2JhaWR1Lm5ld3MvaW1nL2NvbXBvbmVudHMvYm90dG9tVG9vbGJhci9pbWFnZXMvYXNrX2NoZWNrZWQucG5nIiwiaHR0cHM6Ly9pY29uLnpvbC1pbWcuY29tLmNuL21wL2JhaWR1Lm5ld3MvaW1nL2NvbXBvbmVudHMvYm90dG9tVG9vbGJhci9pbWFnZXMvbXkucG5nIl0sInRhZ190aXRsZSI6Is6qus5XaW4rUDq08rK7v6q24LmmxNzP1Mq+w+aw5SjH0Lu7z9TKvsb3LVpPTM7KtPAifX0qADIic3BpZGVyPWdlbmVyYWwsc21hcnRhcHBzLGNhbWJyaWFuO5J9QGh0dHBzOi8va2NnNXIxLnNtYXJ0YXBwcy5jbi9wYWdlcy9hc2svZGV0YWlseC9kZXRhaWx4P2lkPTM5MzkyNjY=";
//        byte[] decode = Base64.getDecoder().decode(aa);

        FileInputStream fileInputStream = new FileInputStream(new File("/Users/baidu/Desktop/1.txt"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        FileWriter fileWriter = new FileWriter("/tmp/fsh.txt");
        String str = bufferedReader.readLine();
        JSONObject object = JSONObject.parseObject(str);
        JSONArray array = object.getJSONArray("messages");
        array.forEach(obj -> {
            JSONObject json = (JSONObject) obj;
            try {
                fileWriter.write(json.getString("data"));
                fileWriter.write("\t");
                fileWriter.write(json.getString("id"));
                fileWriter.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        fileWriter.close();
        bufferedReader.close();
        fileInputStream.close();
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
