package org.vaadin.aes.service.abstracts.meal;

import org.vaadin.aes.model.concrete.MealPrice;

import java.util.List;

public interface MealPriceService {
    MealPrice save(MealPrice mealPrice);

    List<MealPrice> save(List<MealPrice> list);

}
