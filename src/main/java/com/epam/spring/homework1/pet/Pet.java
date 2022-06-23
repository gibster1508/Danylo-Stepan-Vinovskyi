package com.epam.spring.homework1.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Pet {

    List<Animal> list;

    @Autowired
    public Pet(List<Animal> list) {
        this.list = list;
    }

    public void printPets(){
        list.forEach(name -> System.out.println(name.getClass().getSimpleName()));
    }
}
