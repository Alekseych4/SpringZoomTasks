package com.spring3.zoo.impl;

import com.spring3.aspect.annotationMarker.Marker;
import com.spring3.zoo.Animal;
import com.spring3.zoo.AnimalService;
import com.spring3.zoo.food.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Marker
public class AnimalServiceImpl implements AnimalService {

    @Marker
    @Override
    public void feedAnimal(Food food, Animal animal) {
        animal.feed(food);
    }
}
