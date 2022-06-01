package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyMessageImplement implements MyMessage{
    @Override
    public String msg(String msg) {
        return "Toma tu caldo desde mi implementación de mensaje " + msg;
    }
}
