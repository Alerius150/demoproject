package com.example.demoproject.services;






public interface TestService {


    String getTestData();
    int getTestDataInt();

    void setTestData();

    void setTestDataInt(int testDataInt);

    boolean auth(String email, String password);

}
