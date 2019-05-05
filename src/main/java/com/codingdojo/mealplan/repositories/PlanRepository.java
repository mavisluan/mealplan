package com.codingdojo.mealplan.repositories;

import com.codingdojo.mealplan.models.Plan;
import com.codingdojo.mealplan.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Long> {
    List<Plan> findAll();
    Optional<Plan> findById(Long id);
    List<Plan> findByUser(User user);
}
