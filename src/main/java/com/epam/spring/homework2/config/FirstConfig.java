package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.Objects;

@ComponentScan(basePackages = "com.epam.spring.homework2.beans")
@Configuration
@Import(SecondConfig.class)
public class FirstConfig {
    @Autowired
    Environment env;

    @DependsOn("beanD")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanB beanB() {
        BeanB beanB = new BeanB();
        beanB.setName(env.getProperty("BeanB.name"));
        beanB.setValue(Integer.parseInt(Objects.requireNonNull(env.getProperty("BeanB.value"))));
        return beanB;
    }

    @DependsOn("beanB")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanC beanC() {
        BeanC beanC = new BeanC();
        beanC.setName(env.getProperty("BeanC.name"));
        beanC.setValue(Integer.parseInt(Objects.requireNonNull(env.getProperty("BeanC.value"))));
        return beanC;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanD beanD() {
        BeanD beanD = new BeanD();
        beanD.setName(env.getProperty("BeanD.name"));
        beanD.setValue(Integer.parseInt(Objects.requireNonNull(env.getProperty("BeanD.value"))));
        return beanD;
    }
}
