package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanD extends BeanParent {

    public BeanD(String name, int value) {
        super(name, value);
    }

    public void init() {
        System.out.println("BeanD initialization");
    }

    public void destroy() {
        System.out.println("BeanD destroy");
    }
}
