package com.spring3.zoo;

import com.spring3.zoo.event.AnimalIsHungryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Zoo {
    private final Animal animal1;
    private final Animal animal2;
    private final List<Animal> animals;
    private final ApplicationEventPublisher eventPublisher;
    @Value(value = "${name}")
    private String name;

    @Autowired
    public Zoo(@Qualifier(value = "catQualifier") Animal animal1,
               @Qualifier(value = "dogQualifier") Animal animal2,
               List<Animal> animals, ApplicationEventPublisher eventPublisher) {
        this.animal1 = animal1;
        this.animal2 = animal2;
        this.animals = animals;
        this.eventPublisher = eventPublisher;
    }

    public void doAllAnimalsHungry(){
        animals.forEach(animal -> eventPublisher.publishEvent(new AnimalIsHungryEvent<>(animal)));
    }

    public Animal getAnimal1() {
        return animal1;
    }

    public Animal getAnimal2() {
        return animal2;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public String getName() {
        return name;
    }
}
