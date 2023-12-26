package com.example.demoproject.beans;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public interface ThirdBean {

    static String name = "Almein";

    String getData();
    void setData(String data);







}
