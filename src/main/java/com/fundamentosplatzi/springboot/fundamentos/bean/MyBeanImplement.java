package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanImplement implements MyBean{

    @Override
    public void print() {
        System.out.println("Tome desde mi implementación propia del bean");
    }
}
