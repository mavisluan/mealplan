package com.codingdojo.mealplan.controllers;

import com.codingdojo.mealplan.models.Dish;
import com.codingdojo.mealplan.services.DayService;
import com.codingdojo.mealplan.services.DishService;
import com.codingdojo.mealplan.services.MealService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class DishesController {
    private final DishService dishService;

    public DishesController(DayService dayService, MealService mealService, DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping("/dishes/search")
    public String showSearch() {

        return "/dishes/search.jsp";
    }

    @RequestMapping(value="/dishes/search", method = RequestMethod.POST)
    public String processSearch(
            @PathParam("search") String search,
            Model model
            ) throws UnirestException {
//        System.out.println(search);
//        String searchUrl = String.format("https://api.edamam.com/search?q=%s&app_id=0934bbd2&app_key=c5987cefac431d783a9abee6a0f5e252", search);
//        System.out.println(searchUrl);

        HttpResponse<JsonNode> jsonResponse =
                Unirest.get("https://api.edamam.com/search?q={query}&app_id=0934bbd2&app_key=c5987cefac431d783a9abee6a0f5e252&from=0&to=12")
                .routeParam("query", search)
                .asJson();

        Object result = jsonResponse.getBody();
        JSONObject object1 = new JSONObject(String.format("%s", result));
        JSONArray array1 = object1.getJSONArray("hits");

        ArrayList<Dish> resultDishes = new ArrayList<Dish>();
        JSONArray jsonArray = (JSONArray) array1;

        if (jsonArray != null) {
            int len = jsonArray.length();
            for (int i=0;i<len;i++){
                Dish dish = new Dish();

                JSONObject jsonObj = (JSONObject) jsonArray.get(i);
                JSONObject jsonRecipe = jsonObj.getJSONObject("recipe");
                String imageUrl = jsonRecipe.getString("image");
                String label = jsonRecipe.getString("label");
                String url = jsonRecipe.getString("url");

                dish.setImage(imageUrl);
                dish.setName(label);
                dish.setUrl(url);
                resultDishes.add(dish);
            }
        }

        model.addAttribute("resultDishes", resultDishes);
//        System.out.println(dishes);

        return "/dishes/search.jsp";
    }




//    @RequestMapping("/{day}/{meal}/new")
//    public String addDish(
//            @ModelAttribute("dish") Dish formDish,
//            @PathVariable("day") String day,
//            @PathVariable("meal") String meal,
//            Model model
//            ) {
//        model.addAttribute("day", day);
//        model.addAttribute("meal", meal);
//
//        return "/dishes/new.jsp";
//    };

//    @RequestMapping(value="/{day}/{meal}/new", method = RequestMethod.POST)
//    public String addDish(
//            @ModelAttribute("dish") Dish formDish,
//            BindingResult result,
//            @PathVariable("day") String day,
//            @PathVariable("meal") String meal,
//            Model model
//    ) {
//        model.addAttribute("day", day);
//        model.addAttribute("meal", meal);
//        if (result.hasErrors()) {
//            return "/shows/edit.jsp";
//        } else {
//
//            return "redirect:/shows";
//        }
//
//        return "/dishes/new.jsp";
//    };
}
