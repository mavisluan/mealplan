package com.codingdojo.mealplan.services;

import com.codingdojo.mealplan.models.Meal;
import com.codingdojo.mealplan.repositories.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {
//    adding the item repo as a dependency
    private final MealRepository mealRepository;
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    public Meal findByName(String name) {
        return mealRepository.findByName(name);
    }
}
