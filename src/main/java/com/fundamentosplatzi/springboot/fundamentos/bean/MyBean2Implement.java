package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBean2Implement implements MyBean{

    @Override
    public void print() {
        System.out.println("Tome desde mi implementación propia del bean 2");
    }
}
