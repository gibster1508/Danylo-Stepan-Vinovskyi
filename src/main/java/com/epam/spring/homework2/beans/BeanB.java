package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanB extends BeanParent {

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
