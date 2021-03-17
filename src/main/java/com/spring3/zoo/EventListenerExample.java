package com.spring3.zoo;

import com.spring3.zoo.event.AnimalIsHungryEvent;
import com.spring3.zoo.impl.Cat;
import com.spring3.zoo.impl.Dog;
import com.spring3.zoo.impl.Horse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventListenerExample {

    private final FoodWarehouse foodWarehouse;
    private final AnimalService animalService;

    @Autowired
    public EventListenerExample(FoodWarehouse foodWarehouse, AnimalService animalService){
        this.foodWarehouse = foodWarehouse;
        this.animalService = animalService;
    }
//    @EventListener
//    public void catIsHungryEvent (AnimalIsHungryEvent<Cat> catAnimalIsHungryEvent){
//        System.out.println("cat event caught");
//    }
//
//    @EventListener
//    public void dogIsHungryEvent (AnimalIsHungryEvent<Dog> dogAnimalIsHungryEvent){
//        System.out.println("dog event caught");
//    }

    @EventListener
    public void anyAnimalIsHungryEvent (AnimalIsHungryEvent animalIsHungryEvent){
        //System.out.println("any event caught");
        Animal animal = animalIsHungryEvent.getAnimal();
        animalService.feedAnimal(foodWarehouse.supplyFood(), animal);
    }
}
