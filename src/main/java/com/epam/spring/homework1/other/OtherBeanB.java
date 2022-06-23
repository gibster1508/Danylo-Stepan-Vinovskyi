package com.epam.spring.homework1.other;

import com.epam.spring.homework1.beans.BeanB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtherBeanB {
    BeanB b;
    @Autowired
    public void setBeanB(BeanB beanB) {
        this.b = beanB;
        System.out.println(this.getClass().getSimpleName() + ". " + b.getClass().getSimpleName() + " was injected through the setter");
    }
}
