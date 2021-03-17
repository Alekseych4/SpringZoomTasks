package com.spring3.zoo;

import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;

public interface Animal {

    void consumeFood();

    FoodType getFoodType();

    void voice();

    void feed(Food food);

    Integer getAge();

    void throwException();
}
