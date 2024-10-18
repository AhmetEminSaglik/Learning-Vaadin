package org.vaadin.aes.viewmodel.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.service.abstracts.meal.MealService;
import org.vaadin.aes.view.home.concretes.FoodView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class FoodViewModel {
    private static final Logger log = Logger.getLogger(FoodViewModel.class.getName());
    private List<Meal> meals= new ArrayList<>();
    //    private final FoodView view;
    private final MealService mealService;

    @Autowired
    public FoodViewModel(MealService mealService) {
//        this.view = view;
        this.mealService = mealService;
    }

    public List<Meal> getMeals() {
        if (meals.isEmpty()) {
            meals = mealService.findAll();
        }
        meals.stream().forEach(mealFromJson -> log.info("Found Meal from Database: "+mealFromJson.toString()));
        return meals;
    }

    public void addItemToOrderBasket(FoodView view, Meal meal) {
        view.getOrderBasketView().getViewModel().addItem(meal);
//        orderBasketViewModel.addItem(meal);
//        orderBasketView.getViewModel().addItem(meal);
        updateCustomerMealGridData(view);
    }

    public void removeItemOrderBasket(FoodView view, Meal meal) {
        view.getOrderBasketView().getViewModel().removeItem(meal);
//        orderBasketView.getViewModel().removeItem(meal);
        updateCustomerMealGridData(view);
    }

    private void updateCustomerMealGridData(FoodView view) {
        view.getGridCustomerMeals().setItems(
                view.getOrderBasketView().getOrderConceptList().stream().sorted().toList()
        );
        view.getGridCustomerMeals().getDataProvider().refreshAll();
    }
}
