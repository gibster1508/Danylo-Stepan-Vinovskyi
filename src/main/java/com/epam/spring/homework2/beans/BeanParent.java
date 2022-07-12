package com.epam.spring.homework2.beans;

import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:BeansValues.properties")
public class BeanParent {
    private String name;
    private int value;

    public BeanParent(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" + "name='" + name + '\'' + ", value=" + value + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
