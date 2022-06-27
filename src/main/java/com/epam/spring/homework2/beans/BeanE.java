package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanE extends BeanFather {



    @PostConstruct
    public void postConstruct(){
        System.out.println("PostConstruct method");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("PreDestroy method");
    }

}
