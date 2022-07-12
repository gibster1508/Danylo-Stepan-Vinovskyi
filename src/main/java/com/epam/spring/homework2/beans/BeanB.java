package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanB extends BeanParent {

    public BeanB(String name, int value) {
        super(name, value);
    }

    public void init() {
        System.out.println("BeanB initialization");
    }

    public void destroy() {
        System.out.println("BeanB destroy");
    }

    public void initAfterBFPP() {
        System.out.println("BeanB initialization after BFPP");
    }
}
