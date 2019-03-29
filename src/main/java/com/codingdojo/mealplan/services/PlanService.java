package com.codingdojo.mealplan.services;

import com.codingdojo.mealplan.models.*;
import com.codingdojo.mealplan.models.Plan;
import com.codingdojo.mealplan.repositories.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {
    //    adding the plan repo as a dependency
    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    //    return all the plans
    public List<Plan> findAll() {
        return planRepository.findAll();
    }

//    fin plans by user
    public List<Plan> findByUser(User user) {
        return planRepository.findByUser(user);
    }

    //    creates an plan
    public Plan create(Plan plan) {
        return planRepository.save(plan);
    }

    //    retrieved an plan
    public Plan findById(Long id) {
        Optional<Plan> optionalPlan = planRepository.findById(id);
        if (optionalPlan.isPresent()) {
            return optionalPlan.get();
        } else {
            return null;
        }
    }

    //    update an plan with Plan formPlan
    public Plan update(Long id, Plan formPlan) {
        Optional<Plan> optionalPlan = planRepository.findById(id);
        if (optionalPlan.isPresent()) {
            Plan plan =  optionalPlan.get();
            plan.setName(formPlan.getName());

            return planRepository.save(plan);
        } else {
            return null;
        }
    }

    //    delete an plan
    public void delete(Long id) {
        planRepository.deleteById(id);
    }
}
