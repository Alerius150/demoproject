package com.example.demoproject.beans;

public class FirstBean {

    private String name;
    private int age;
    public FirstBean(){
        System.out.println("Using default constructor of First Bean class");
        this.name = "No name";
        this.age = 0;
    }

    public FirstBean(String name, int age){
        System.out.println("using parametrized constructor of FIRSTBEAN!!11!1!!1!111!!11!!!");
        this.name=name;
        this.age=age;

    }
    public String getText(){
        return this.name + " " + this.age + "years old";
    }
}
