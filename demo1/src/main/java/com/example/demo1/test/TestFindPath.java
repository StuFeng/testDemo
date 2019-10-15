package com.example.demo1.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class TestFindPath {

    public static void test() throws IOException, URISyntaxException {
//        ClassPathResource classPathResource = new ClassPathResource("static/aa.txt");

        URL resource = Thread.currentThread().getContextClassLoader().getResource("static/aa.txt");
//        String file = classPathResource.getFile().getAbsolutePath();
        List<String> lines = Files.readAllLines(Paths.get(resource.getPath()));
        System.out.println(lines.toString());
    }

    public static void main(String[] args) throws Exception {
        System.out.println((System.currentTimeMillis()/1000 - 1555381720)/60);
        System.out.println((System.currentTimeMillis()/1000 - 24*60*60));
    }
}
