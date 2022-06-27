package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import org.springframework.context.annotation.*;

@ComponentScan(basePackages = "com.epam.spring.homework2.beans")
@Configuration
@Import(SecondConfig.class)
public class FirstConfig {

}
