package com.codingdojo.mealplan.controllers;

import com.codingdojo.mealplan.models.Day;
import com.codingdojo.mealplan.models.Dish;
import com.codingdojo.mealplan.models.Meal;
import com.codingdojo.mealplan.services.DayService;
import com.codingdojo.mealplan.services.DishService;
import com.codingdojo.mealplan.services.MealService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PlansController {
    private final DayService dayService;
    private final MealService mealService;
    private final DishService dishService;

    public PlansController(DayService dayService, MealService mealService, DishService dishService) {
        this.dayService = dayService;
        this.mealService = mealService;
        this.dishService = dishService;
    }

    @RequestMapping("/plans")
    public String plans(Model model) {
        return "/plans/index.jsp";
    }

    @RequestMapping("/plans/new")
    public String createPlan(Model model) {
        List<Day> days = dayService.findAll();
        model.addAttribute("days", days);

        return "/plans/new.jsp";
    }


//      THIS WORKS
    @RequestMapping("/{paramDay}/{paramMeal}/new")
    public String searchDish(
            @ModelAttribute("dish") Dish formDish,
            BindingResult result,
            @PathVariable("paramDay") String day,
            @PathVariable("paramMeal") String meal,
            Model model
            ) {
        model.addAttribute("day", day);
        model.addAttribute("meal", meal);

        return "/dishes/new.jsp";
    };

    @RequestMapping(value="/{paramDay}/{paramMeal}/new", method = RequestMethod.POST)
    public String addDish(
            @Valid @ModelAttribute("dish") Dish formDish,
            BindingResult result,
            @PathVariable("paramDay") String day,
            @PathVariable("paramMeal") String meal
    ) {
        if (result.hasErrors()) {
            return "/dishes/new.jsp";
        } else {
            Dish dish = new Dish();
            dish.setDay(dayService.findByName(day));
            dish.setName(formDish.getName());
            dish.setImage(formDish.getImage());
            dish.setMeal(mealService.findByName(meal));
            dish.setUrl(formDish.getUrl());

            dishService.create(dish);
            return "redirect:/plans/new";
        }
    }
}
