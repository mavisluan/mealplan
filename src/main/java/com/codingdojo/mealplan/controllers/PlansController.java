package com.codingdojo.mealplan.controllers;

import com.codingdojo.mealplan.models.*;
import com.codingdojo.mealplan.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PlansController {
    private final DayService dayService;
    private final MealService mealService;
    private final DishService dishService;
    private final IngredientService ingredientService;
    private final UserService userService;
    private final PlanService planService;

    public PlansController(DayService dayService, MealService mealService, DishService dishService,
                           IngredientService ingredientService, UserService userService, PlanService planService) {
        this.dayService = dayService;
        this.mealService = mealService;
        this.dishService = dishService;
        this.ingredientService = ingredientService;
        this.userService = userService;
        this.planService = planService;
    }

    @RequestMapping("/plans")
    public String plans(
            @ModelAttribute("plan") Plan formPlan,
            BindingResult result,
            HttpSession session,
            Model model) {

            User user = userService.findUserById((Long) session.getAttribute("userId"));
            List plans = planService.findByUser(user);
            model.addAttribute("user", user);
            model.addAttribute("plans", plans);

            return "/plans/index.jsp";
    }

    @RequestMapping(value="/plans", method = RequestMethod.POST)
    public String createPlan(
            @Valid @ModelAttribute("plan") Plan formPlan,
            BindingResult result,
            HttpSession session,
            Model model) {

        if (result.hasErrors()) {
            User user = userService.findUserById((Long) session.getAttribute("userId"));
            List plans = planService.findByUser(user);
            model.addAttribute("user", user);
            model.addAttribute("plans", plans);

            return "/plans/index.jsp";
        } else {
            planService.create(formPlan);

            return "redirect:/plans";
        }
    }

    @RequestMapping("/plans/{planId}/add")
    public String createDishes(
            @PathVariable("planId") Long planId,
            HttpSession session,
            Model model
    ) {
        List<Day> days = dayService.findAll();
        Plan plan = planService.findById(planId);
        List<Dish> dishes = dishService.findByPlan(plan);

        if (dishes.size() == 0) {
            List<Meal> meals = mealService.findAll();
            for (int i = 0; i<days.size();i++) {
                for (int j = 0; j < 3; j++) {
                    Dish dish = new Dish();
                    dish.setDay(days.get(i));
                    dish.setMeal(meals.get(j));
                    dish.setPlan(plan);
                    dishService.create(dish);
                }
            }
        }

        session.setAttribute("planId", planId);
//        model.addAttribute("days", days);
//        model.addAttribute("dishes", dishes);

        return "redirect:/plans/" + planId + "/edit";
    }


    @RequestMapping("/plans/{planId}/edit")
    public String editDishes(
            @PathVariable("planId") Long planId,
            HttpSession session,
            Model model
            ) {
        List<Day> days = dayService.findAll();
        Plan plan = planService.findById(planId);
        List<Dish> dishes = dishService.findByPlan(plan);

        session.setAttribute("planId", planId);

        model.addAttribute("days", days);
        model.addAttribute("dishes", dishes);

        return "/plans/new.jsp";
    }


    @RequestMapping("/plans/{planId}/shoppinglist")
    public String showList(
            @PathVariable("planId") Long planId,
            Model model) {
        Plan plan = planService.findById(planId);
       List<Ingredient> ingredients = plan.getIngredents();
       model.addAttribute("ingredients", ingredients);

        return "/ingredients/index.jsp";
    }


    @RequestMapping("/plans/{planId}/delete")
    public String deleteDishes(
            @PathVariable("planId") Long planId,
            HttpSession session,
            Model model
    ) {
        User user = userService.findUserById((Long) session.getAttribute("userId"));
        List plans = planService.findByUser(user);

        planService.delete(planId);

        session.setAttribute("plans", plans);

        return "redirect:/plans";
    }
}
