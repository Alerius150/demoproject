package com.example.demoproject.beans;

public class ThirdBeanImpl implements ThirdBean{

    private String data;


    @Override
    public String getData() {
        return "Almein";
    }

    @Override
    public void setData(String data) {
        this.data=data;
    }
}
