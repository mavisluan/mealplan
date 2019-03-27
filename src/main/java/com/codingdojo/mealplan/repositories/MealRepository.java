package com.codingdojo.mealplan.repositories;

import com.codingdojo.mealplan.models.Day;
import com.codingdojo.mealplan.models.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {
    List<Meal> findAll();
    Meal findByName(String name);
}
