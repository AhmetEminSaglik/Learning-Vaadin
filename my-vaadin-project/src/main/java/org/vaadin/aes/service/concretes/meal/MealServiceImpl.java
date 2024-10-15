package org.vaadin.aes.service.concretes.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.repository.abstracts.MealRepository;
import org.vaadin.aes.service.meal.MealService;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public Meal save(Meal meal) {
        Meal savedMeal=mealRepository.findByName(meal.getName());
        if (savedMeal == null) {
            return mealRepository.save(meal);
        }
        return meal;
    }

    @Override
    public Meal findById(Long id) {
        return mealRepository.findById(id).get();
    }
}
