package com.epam.spring.homework2;

import com.epam.spring.homework2.beans.BeanFather;
import com.epam.spring.homework2.config.FirstConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(FirstConfig.class);
        String[] beanNames = context.getBeanDefinitionNames();
        System.out.println("ALL BEANS: -----------------");
        for (String beanName : beanNames) {
            if(context.getBean(beanName) instanceof BeanFather)
                System.out.println(context.getBean(beanName));
            else
                System.out.println(beanName);
        }
        System.out.println(" ---------------------------");
        context.close();
    }
}