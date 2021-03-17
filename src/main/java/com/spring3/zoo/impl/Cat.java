package com.spring3.zoo.impl;

import com.spring3.aspect.annotationMarker.VoiceMarker;
import com.spring3.zoo.Animal;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Getter
@Setter
@Qualifier(value = "catQualifier")
@VoiceMarker
public class Cat implements Animal {

    private Food food;
    private Integer age;
    private FoodType foodType = FoodType.FISH;

    @Override
    @VoiceMarker
    public void voice() {
        System.out.println("mi");
    }

    @Override
    public void feed(Food food) {
        this.food = food;
        System.out.println("Cat was fed");
        System.out.println(food.toString());
    }

    @Override
//    @Scheduled(fixedRate = 10000)
    @Scheduled(cron = "15 * * * * *")
    public void consumeFood() {
        if (food == null || food.getValue().intValue() == 0 || food.getExpiredDate().isBefore(LocalDateTime.now())){
            this.voice();
        } else {
            food.setValue(food.getValue().subtract(new BigDecimal(1)));
        }
    }

    @Override
    public void throwException() {
        throw new RuntimeException("aaaaa");
    }
}
