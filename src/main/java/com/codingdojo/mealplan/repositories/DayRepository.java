package com.codingdojo.mealplan.repositories;

import com.codingdojo.mealplan.models.Day;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayRepository extends CrudRepository<Day, Long> {
    List<Day> findAll();
    Day findByName(String name);
}
