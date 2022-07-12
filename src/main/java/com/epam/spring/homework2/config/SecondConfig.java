package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanA;
import com.epam.spring.homework2.beans.BeanE;
import com.epam.spring.homework2.beans.BeanF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;


@Configuration
public class SecondConfig {

    @Bean
    public BeanA beanA(@Value("${beanA.name}") final String name, @Value("${beanA.value}") final int value) {
        return new BeanA(name, value);
    }

    @Bean
    public BeanE beanE(@Value("${beanE.name}") final String name, @Value("${beanE.value}") final int value) {
        return new BeanE(name, value);
    }

    @Bean
    public BeanF beanF(@Value("${beanF.name}") final String name, @Value("${beanF.value}") final int value) {
        return new BeanF(name, value);
    }
}
