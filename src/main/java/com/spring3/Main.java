package com.spring3;

import com.spring3.configuration.AnnotationConfiguration;
import com.spring3.configuration.BeanConfiguration;
import com.spring3.zoo.Animal;
import com.spring3.zoo.AnimalService;
import com.spring3.zoo.Zoo;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import com.spring3.zoo.impl.AnimalServiceImpl;
import com.spring3.zoo.impl.Cat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = getAnnotationContext("annotationConfiguration");
        Zoo setterZoo = context.getBean("zoo", Zoo.class);
    }

    private static ApplicationContext getAnnotationContext(String profile) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment().setActiveProfiles(profile);
        applicationContext.register(AnnotationConfiguration.class, BeanConfiguration.class);
        applicationContext.refresh();
        return applicationContext;
    }

    private static ApplicationContext getXmlContext() {
        return new ClassPathXmlApplicationContext("XmlApplicationContext.xml");
    }
}
