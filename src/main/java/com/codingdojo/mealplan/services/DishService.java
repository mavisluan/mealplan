package com.codingdojo.mealplan.services;

import com.codingdojo.mealplan.models.Day;
import com.codingdojo.mealplan.models.Dish;
import com.codingdojo.mealplan.models.Meal;
import com.codingdojo.mealplan.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    //    adding the dish repo as a dependency
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    //    return all the dishs
    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

//    find one dish by day and meal
    public Dish findDayMealDish(Day day, Meal meal) {
        return dishRepository.findFirstByDayAndMeal(day, meal);
    }

//    find dishes by day
    public List<Dish> findDayDishes(Day day) {
        return dishRepository.findByDay(day);
    }

    //    creates an dish
    public Dish create(Dish dish) {
        return dishRepository.save(dish);
    }

    //    retrieved an dish
    public Dish findById(Long id) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isPresent()) {
            return optionalDish.get();
        } else {
            return null;
        }
    }

    //    update an dish with Dish formDish
    public Dish update(Long id, Dish formDish) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isPresent()) {
            Dish dish =  optionalDish.get();
            dish.setName(formDish.getName());
            dish.setUrl(formDish.getUrl());
            dish.setImage(formDish.getImage());

            return dishRepository.save(dish);
        } else {
            return null;
        }
    }

    //    delete an dish
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}
