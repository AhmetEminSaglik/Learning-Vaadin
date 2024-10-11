package org.vaadin.aes.mealdb.model;

import java.util.List;

public class ApiResponse {
    private List<MealFromJson> mealFromJsons;

    public List<MealFromJson> getMeals() {
        return mealFromJsons;
    }

    public void setMeals(List<MealFromJson> mealFromJsons) {
        this.mealFromJsons = mealFromJsons;
    }
}