package com.codingdojo.mealplan.services;

import com.codingdojo.mealplan.models.Day;
import com.codingdojo.mealplan.repositories.DayRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DayService {
//    adding the item repo as a dependency
    private final DayRepository dayRepository;
    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }
//    return all the items
    public List<Day> findAll() {
        return dayRepository.findAll();
    }

    public Day findByName(String name) {
        return dayRepository.findByName(name);
    }
}
