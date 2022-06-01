package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyMessageWithDependencyImplement implements MyMessageWithDependency {

    private MyMessage myMessage;

    public MyMessageWithDependencyImplement(MyMessage myMessage) {
        this.myMessage = myMessage;
    }

    @Override
    public void msgWithDependency() {
        String msg=" que gracias";
        System.out.println(myMessage.msg(msg));
        System.out.println("Hola desde mi mensaje con dependencia implement " + msg);

    }
}
