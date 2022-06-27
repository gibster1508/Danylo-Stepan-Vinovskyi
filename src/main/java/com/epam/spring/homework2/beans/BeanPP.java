package com.epam.spring.homework2.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanPP implements BeanPostProcessor {
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof BeanFather)
            if(((BeanFather) bean).getName() != null && ((BeanFather) bean).getValue() >= 0)
                System.out.println(beanName + " validated");
            else
                System.out.println(beanName + " not validated");
        return bean;
    }
}
