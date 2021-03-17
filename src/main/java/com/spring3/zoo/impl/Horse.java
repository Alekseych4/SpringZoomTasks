package com.spring3.zoo.impl;

import com.spring3.aspect.annotationMarker.VoiceMarker;
import com.spring3.zoo.Animal;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Getter
@Setter
@VoiceMarker
public class Horse implements Animal {

    private Integer age;
    private Food food;
    private FoodType foodType = FoodType.CORN;

    @Override
    @VoiceMarker
    public void voice() {
        System.out.println("igogo");
    }

    @Override
    public void feed(Food food) {
        this.food = food;
        System.out.println("Horse was fed");
        System.out.println(food.toString());
    }

    @Override
//    @Scheduled(fixedRate = 20000)
    @Scheduled(cron = "5 * * * * *")
    public void consumeFood() {
        if (food == null || food.getValue().intValue() == 0 || food.getExpiredDate().isBefore(LocalDateTime.now())){
            voice();
        } else {
            food.setValue(food.getValue().subtract(new BigDecimal(1)));
        }
    }

    @Override
    public void throwException() {

    }
}
