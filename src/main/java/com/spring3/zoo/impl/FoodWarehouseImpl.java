package com.spring3.zoo.impl;

import com.spring3.zoo.FoodWarehouse;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class FoodWarehouseImpl implements FoodWarehouse {
    @Override
    public Food supplyFood() {
        Food food = null;
        Random random = new Random();
        int randomInt = random.nextInt(2999);
        int res = randomInt / 1000;
        switch (res){
            case 0:
                food = Food.builder()
                        .foodType(FoodType.MEAT)
                        .value(new BigDecimal(2))
                        .expiredDate(LocalDateTime.now().plusMinutes(1))
                        .build();
                break;
            case 1:
                food = Food.builder()
                        .foodType(FoodType.FISH)
                        .value(new BigDecimal(1))
                        .expiredDate(LocalDateTime.now().plusMinutes(2))
                        .build();
                break;
            case 2:
                food = Food.builder()
                        .foodType(FoodType.CORN)
                        .value(new BigDecimal(random.nextInt(10)))
                        .expiredDate(LocalDateTime.now().plusMinutes(3))
                        .build();
                break;
        }

        return food;
    }
}
