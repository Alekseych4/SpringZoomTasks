package com.spring3.zoo.impl;

import com.spring3.aspect.annotationMarker.VoiceMarker;
import com.spring3.zoo.Animal;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Getter
@Setter
@Qualifier(value = "dogQualifier")
@VoiceMarker
public class Dog implements Animal {

    private Food food;
    private Integer age;
    private FoodType foodType = FoodType.MEAT;

    @Override
    @VoiceMarker
    public void voice() {
        System.out.println("gav");
    }

    @Override
    public void feed(Food food) {
        this.food = food;
        System.out.println("Dog was fed");
        System.out.println(food.toString());
    }

    @Override
//    @Scheduled(fixedRate = 15000)
    @Scheduled(cron = "25 * * * * *")
    public void consumeFood() {
        if (food == null || food.getValue().intValue() == 0 || food.getExpiredDate().isBefore(LocalDateTime.now())){
            voice();
        } else {
            food.setValue(food.getValue().subtract(new BigDecimal(1)));
        }
    }

    @Override
    public void throwException() {
        throw new RuntimeException("aaaaa");
    }
}
