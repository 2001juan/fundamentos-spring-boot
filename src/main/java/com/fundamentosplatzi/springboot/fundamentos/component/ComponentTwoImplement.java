package com.fundamentosplatzi.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

import javax.swing.*;
@Component
public class ComponentTwoImplement implements ComponentDependency{

    @Override
    public void saludar() {
        System.out.println("Hola Cerote desde mi componente dos");
    }
}
