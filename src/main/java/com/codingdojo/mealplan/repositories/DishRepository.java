package com.codingdojo.mealplan.repositories;

import com.codingdojo.mealplan.models.Day;
import com.codingdojo.mealplan.models.Dish;
import com.codingdojo.mealplan.models.Meal;
import com.codingdojo.mealplan.models.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {
    List<Dish> findAll();
    Dish findFirstByDayAndMeal(Day day, Meal meal);
    List<Dish> findByDay(Day day);
    List<Dish> findByPlan(Plan plan);
}
