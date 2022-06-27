package com.epam.spring.homework2.config;
import com.epam.spring.homework2.beans.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.Objects;


@Configuration
public class SecondConfig {

    @Autowired
    Environment env;

    @Bean
    public BeanA beanA(){
        BeanA beanA = new BeanA();
        beanA.setName(env.getProperty("BeanA.name"));
        beanA.setValue(Integer.parseInt(Objects.requireNonNull(env.getProperty("BeanA.value"))));
        return beanA;
    }

    @DependsOn("beanD")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanB beanB(){
        BeanB beanB = new BeanB();
        beanB.setName(env.getProperty("BeanD.name"));
        beanB.setValue(Integer.parseInt(Objects.requireNonNull(env.getProperty("BeanD.value"))));
        return beanB;
    }
    @DependsOn("beanB")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanC beanC(){
        BeanC beanC = new BeanC();
        beanC.setName(env.getProperty("BeanB.name"));
        beanC.setValue(Integer.parseInt(Objects.requireNonNull(env.getProperty("BeanB.value"))));
        return beanC;
    }
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanD beanD(){
        BeanD beanD = new BeanD();
        beanD.setName(env.getProperty("BeanD.name"));
        beanD.setValue(Integer.parseInt(Objects.requireNonNull(env.getProperty("BeanD.value"))));
        return beanD;
    }

    @Bean
    public BeanE beanE(){
        BeanE beanE = new BeanE();
        beanE.setName(env.getProperty("BeanE.name"));
        beanE.setValue(Integer.parseInt(Objects.requireNonNull(env.getProperty("BeanE.value"))));
        return beanE;
    }
    @Bean
    public BeanF beanF(){
        BeanF beanF = new BeanF();
        beanF.setName(env.getProperty("BeanF.name"));
        beanF.setValue(Integer.parseInt(Objects.requireNonNull(env.getProperty("BeanF.value"))));
        return beanF;
    }
}
