package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanD extends BeanParent {

    public void init() {
        System.out.println("BeanD initialization");
    }

    public void destroy() {
        System.out.println("BeanD destroy");
    }
}
