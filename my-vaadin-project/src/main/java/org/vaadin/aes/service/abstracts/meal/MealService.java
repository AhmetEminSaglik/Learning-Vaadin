package org.vaadin.aes.service.abstracts.meal;

import org.vaadin.aes.model.concrete.Meal;

import java.util.List;

public interface MealService {
    Meal save(Meal meal);
    List<Meal> saveList(List<Meal> list);
    Meal findById(Long id);
}
