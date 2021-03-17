package com.spring3.aspect;

import com.spring3.zoo.Animal;
import com.spring3.zoo.event.AnimalIsHungryEvent;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.impl.AnimalServiceImpl;
import com.spring3.zoo.impl.Cat;
import com.spring3.zoo.impl.Dog;
import com.spring3.zoo.impl.Horse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AnimalAspect {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public AnimalAspect(ApplicationEventPublisher eventPublisher){
        this.eventPublisher = eventPublisher;
    }

    @Pointcut("@within(com.spring3.aspect.annotationMarker.VoiceMarker)")
    public void voicePoint(){
    }

    @After(value = "voicePoint()")
    public void afterVoice(JoinPoint joinPoint){
        Animal hungryAnimal = (Animal) joinPoint.getTarget();
        eventPublisher.publishEvent(new AnimalIsHungryEvent<Animal>(hungryAnimal));
    }

    @Pointcut(value = "@within(com.spring3.aspect.annotationMarker.Marker) && args(food, animal)", argNames = "food,animal")
    public void feedPointcut(Food food, Animal animal){}

    @Around(value = "feedPointcut(food, animal)", argNames = "joinPoint,food,animal")
    public Object checkFoodType(ProceedingJoinPoint joinPoint, Food food, Animal animal){
        Object res = null;

        if (food.getFoodType().equals(animal.getFoodType()) && LocalDateTime.now().isBefore(food.getExpiredDate())){
            try {
                res = joinPoint.proceed();
            } catch (Throwable e){
                e.printStackTrace();
            }
        } else {
            System.out.println("We tried to put " + food.getFoodType() + " in " + animal.getClass().getTypeName() + "'s mouth");
        }

        return res;
    }
}
