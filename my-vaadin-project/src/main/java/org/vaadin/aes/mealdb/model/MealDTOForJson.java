package org.vaadin.aes.mealdb.model;

import org.vaadin.aes.model.concrete.Meal;

import java.util.Random;

public class MealDTOForJson {

    private  static Random random = new Random();

    public  static Meal toMeal(MealFromJson mealJson) {
        Meal meal = new Meal();
        meal.setName(mealJson.getStrMeal());
        meal.setThumbnail(mealJson.getStrMealThumb());
        meal.setPrice(createPrice());
        return meal;
    }

    private static double createPrice() {
        return random.nextInt(150) + 100;
    }
}
