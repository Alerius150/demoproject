package com.example.demoproject.beans;


import org.springframework.stereotype.Component;

@Component
public class TestBean {

    private String text="Hello";

    public TestBean(){
        System.out.println("creating a test bean");
    }
    public void setText(String text){
        this.text=text;
    }
    public String getData(){
        return "This is" + this.text + " data";
    }
}
