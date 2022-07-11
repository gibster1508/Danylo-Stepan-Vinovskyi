package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanA;
import com.epam.spring.homework2.beans.BeanE;
import com.epam.spring.homework2.beans.BeanF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;


@Configuration
public class SecondConfig {
    @Autowired
    Environment env;

    @Bean
    public BeanA beanA() {
        BeanA beanA = new BeanA();
        beanA.setName(env.getProperty("BeanA.name"));
        beanA.setValue(Integer.parseInt(Objects.requireNonNull(env.getProperty("BeanA.value"))));
        return beanA;
    }

    @Bean
    public BeanE beanE() {
        BeanE beanE = new BeanE();
        beanE.setName(env.getProperty("BeanE.name"));
        beanE.setValue(Integer.parseInt(Objects.requireNonNull(env.getProperty("BeanE.value"))));
        return beanE;
    }

    @Bean
    public BeanF beanF() {
        BeanF beanF = new BeanF();
        beanF.setName(env.getProperty("BeanF.name"));
        beanF.setValue(Integer.parseInt(Objects.requireNonNull(env.getProperty("BeanF.value"))));
        return beanF;
    }
}
