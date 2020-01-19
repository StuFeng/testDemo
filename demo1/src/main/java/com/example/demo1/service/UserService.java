package com.example.demo1.service;

import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService{

    @Override
    public String test(){
        return "test";
    }
}
