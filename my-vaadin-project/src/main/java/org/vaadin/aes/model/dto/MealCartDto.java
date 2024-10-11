package org.vaadin.aes.model.dto;

import org.vaadin.aes.model.concrete.Meal;

public class MealCartDto implements Comparable<MealCartDto> {
    private Meal meal;
    private int quantity;


    public MealCartDto(Meal meal, int quantity) {
        this.meal = meal;
        this.quantity = quantity;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {
        quantity--;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "MealCartDto{" +
                "meal=" + meal +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public int compareTo(MealCartDto o) {
        return this.meal.getName().compareTo(o.meal.getName());
    }
}
