package com.codingdojo.mealplan.services;

import com.codingdojo.mealplan.models.Dish;
import com.codingdojo.mealplan.models.Ingredient;
import com.codingdojo.mealplan.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IngredientService {
    //    adding the ingredient repo as a dependency
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    //    return all the ingredients
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }


    //    delete an ingredient
    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }
}
