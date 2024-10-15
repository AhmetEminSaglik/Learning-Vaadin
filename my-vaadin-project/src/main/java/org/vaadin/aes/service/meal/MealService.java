package org.vaadin.aes.service.meal;

import org.vaadin.aes.model.concrete.Meal;

public interface MealService {
    Meal save(Meal meal);

    Meal findById(Long id);
}
