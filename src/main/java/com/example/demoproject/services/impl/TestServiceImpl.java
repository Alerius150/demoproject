package com.example.demoproject.services.impl;

import com.example.demoproject.services.TestService;
import org.springframework.stereotype.Service;


@Service
public class TestServiceImpl implements TestService {

    private String testData;
    private int testDataInt;


    @Override
    public String getTestData() {
        return "Some test data" + this.testData;
    }

    @Override
    public int getTestDataInt() {
        return this.testDataInt;
    }

    @Override
    public void setTestData(){
        this.testData=testData;
    }

    @Override
    public void setTestDataInt(int testDataInt) {
        this.testDataInt=testDataInt;
    }


    @Override
    public boolean auth(String email, String password) {
        return false;
    }
}
