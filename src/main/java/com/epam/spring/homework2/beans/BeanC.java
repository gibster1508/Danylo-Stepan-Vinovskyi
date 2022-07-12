package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanC extends BeanParent {

    public BeanC(String name, int value) {
        super(name, value);
    }

    public void init() {
        System.out.println("BeanC initialization");
    }

    public void destroy() {
        System.out.println("BeanC destroy");
    }
}
