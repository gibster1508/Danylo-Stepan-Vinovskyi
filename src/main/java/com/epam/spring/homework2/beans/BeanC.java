package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanC extends BeanFather {

    public void init(){
        System.out.println("BeanC initialization");
    }

    public void destroy(){
        System.out.println("BeanC destroy");
    }
}
