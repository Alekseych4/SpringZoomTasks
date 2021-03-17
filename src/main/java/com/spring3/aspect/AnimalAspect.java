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
//
//    @Pointcut("execution(* com.spring3.zoo.*.*(..))")
//    public void anyAnimalMethod(){
//    }
//
//    @Pointcut("within(com.spring3.zoo.*)")
//    public void anyAnimalMethodWithWithin(){
//    }
//
//    @Pointcut("@annotation(com.spring3.aspect.annotationMarker.Marker)")
//    public void annotationPointcut(){
//
//    }
//
//    @Pointcut("@within(com.spring3.aspect.annotationMarker.Marker)")
//    public void annotationWithinPointcut(){
//
//    }
//
//    @Pointcut("args(food)")
//    public void withArg(Food food){
//    }
//
//    @Before(value = "voicePoint()")
//    public void beforeVoice(JoinPoint joinPoint){
//        System.out.println("beforeVoice");
//    }
//
//    @Before(value = "anyAnimalMethod() && withArg(food)", argNames = "food")
//    public void beforeSetFood(Food food){
//        System.out.println(food.toString());
//    }
//
//    @Before(value = "annotationPointcut()")
//    public void beforeServiceCall(JoinPoint joinPoint){
//        System.out.println("beforeServiceCall");
//    }
//
//    @AfterReturning(value = "execution(* com.spring3.zoo.Animal.getAge())", returning = "age")
//    public void afterReturningExample(Integer age){
//        System.out.println(age);
//    }
//
//    @AfterThrowing(value = "execution(* com.spring3.zoo.Animal.throwException())", throwing = "e")
//    public void afterThrowing(Throwable e){
//        System.out.println(e.getMessage());
//    }
//
//    @Around(value = "anyAnimalMethod() && withArg(food)", argNames = "proceedingJoinPoint, food")
//    public Object around(ProceedingJoinPoint proceedingJoinPoint, Food food) throws Throwable {
//        Object result = null;
//        //before
//        try {
//            result = proceedingJoinPoint.proceed();
//            //afterReturning
//        } catch (Throwable throwable){
//            throwable.printStackTrace();
//            //afterThrowing
//            throw throwable;
//        }finally {
//            //after
//        }
//        return result;
//    }

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
