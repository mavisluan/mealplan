package com.codingdojo.mealplan.controllers;

import com.codingdojo.mealplan.models.*;
import com.codingdojo.mealplan.services.*;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DishesController {
    private final DishService dishService;
    private final DayService dayService;
    private final IngredientService ingredientService;
    private final MealService mealService;
    private final PlanService planService;

    public DishesController(DayService dayService, MealService mealService, DishService dishService,
                            DayService dayService1, IngredientService ingredientService, MealService mealService1,
                            PlanService planService) {
        this.dishService = dishService;
        this.dayService = dayService1;
        this.ingredientService = ingredientService;
        this.mealService = mealService1;
        this.planService = planService;
    }

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
    }

    @RequestMapping(value = "/{paramDay}/{paramMeal}/new", method = RequestMethod.POST)
    public String processSearch(
            @Valid @ModelAttribute("dish") Dish formDish,
            BindingResult result,
            @PathParam("search") String search,
            @PathVariable("paramDay") String day,
            @PathVariable("paramMeal") String meal,
            Model model
    ) throws UnirestException {

        HttpResponse<JsonNode> jsonResponse =
                Unirest.get("https://api.edamam.com/search?q={query}&app_id=0934bbd2&app_key" +
                        "=c5987cefac431d783a9abee6a0f5e252&from=0&to=9")
                        .routeParam("query", search)
                        .asJson();

        Object jsonResult = jsonResponse.getBody();

        JSONObject object1 = new JSONObject(String.format("%s", jsonResult));
        JSONArray array1 = object1.getJSONArray("hits");

        ArrayList<Dish> resultDishes = new ArrayList<Dish>();
        JSONArray jsonArray = (JSONArray) array1;

        if (jsonArray != null) {
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                Dish dish = new Dish();

                JSONObject jsonObj = (JSONObject) jsonArray.get(i);
                JSONObject jsonRecipe = jsonObj.getJSONObject("recipe");
                String imageUrl = jsonRecipe.getString("image");
                String label = jsonRecipe.getString("label");
                String url = jsonRecipe.getString("url");
                dish.setImage(imageUrl);
                dish.setName(label);
                dish.setUrl(url);
//              initiate ingredientList
                dish.setIngredientList(new ArrayList<>());

                JSONArray ingredientsArray = jsonRecipe.getJSONArray("ingredients");
//              get every ingredient from JSONArray and append it to dish.ingredientList
                if (ingredientsArray != null) {
                    int len1 = ingredientsArray.length();
                    for (int j = 0; j < len1; j++) {
                        JSONObject ingredientsObj = (JSONObject) ingredientsArray.get(j);
                        String ingredientName = ingredientsObj.getString("text");
                        Ingredient ingredient = new Ingredient();
                        ingredient.setName(ingredientName);
                        dish.getIngredientList().add(ingredient);
//                        System.out.println(ingredientName);
                    }
                }

                resultDishes.add(dish);
//                System.out.println(dish.getIngredientList().get(1).getName());
            }
        }

        model.addAttribute("resultDishes", resultDishes);

        return "/dishes/new.jsp";
    }

    @RequestMapping(value = "/{paramDay}/{paramMeal}/add", method = RequestMethod.POST)
    public String addDish(
            @Valid @ModelAttribute("dish") Dish formDish,
            BindingResult result,
            @PathVariable("paramDay") String paramDay,
            @PathVariable("paramMeal") String paramMeal,
            HttpSession session
    ) {
        if (result.hasErrors()) {
//            System.out.println("error");
//            System.out.println(result.getAllErrors());
            return "/dishes/new.jsp";
        } else {
            Day day = dayService.findByName(paramDay);
            Meal meal = mealService.findByName(paramMeal);
            Dish dish = dishService.findDayMealDish(day, meal);
            Long planId = (Long) session.getAttribute("planId");
            Plan plan = planService.findById(planId);
            dish.setPlans(new ArrayList<>());
            dish.getPlans().add(plan);
//            dish.setDay(dayService.findByName(day));
//            dish.setMeal(mealService.findByName(meal));
            dish.setName(formDish.getName());
            dish.setImage(formDish.getImage());
            dish.setUrl(formDish.getUrl());
            dishService.update( dish.getId(), dish);

            dish.setIngredientList(new ArrayList<>());
            for (int i = 0; i < formDish.getIngredientList().size(); i++) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(formDish.getIngredientList().get(i).getName());

//                List<Ingredient> ingredientList = ingredientService.findByNameContains(ingredient.getName());
//                if (ingredientList.size() > 0) {
//                    for (Ingredient ing: ingredientList) {
//                        ing.setAmount(ing.getAmount() + 1);
//                        ingredientService.create(ing);
//                    }
//                } else {
//                    ingredient.setAmount(1);
//                }

                dish.getIngredientList().add(ingredient);
                ingredient.setDish(dish);
                ingredientService.create(ingredient);
//                dishService.create(dish);

            }

            dishService.create(dish);
            return "redirect:/plans/" + planId + "/edit";
        }
    }

    @RequestMapping(value="/dishes/{dishId}/delete", method=RequestMethod.GET)
    public String destroy(
            @PathVariable("dishId") Long dishId
    ) {
        Dish dish = dishService.findById(dishId);
        dish.setUrl(null);
        dish.setImage(null);
        dish.setName(null);
        dish.setIngredientList(new ArrayList<>());
        dishService.update(dishId, dish);

        List<Ingredient> ingredients = ingredientService.findDishIngredients(dish);
        for (int i = 0; i < ingredients.size(); i++) {
            ingredientService.delete(ingredients.get(i).getId());
        }

        return "redirect:/plans/new";
    }
}
