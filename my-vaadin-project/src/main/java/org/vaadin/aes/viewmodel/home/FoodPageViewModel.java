package org.vaadin.aes.viewmodel.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.service.abstracts.meal.MealService;
import org.vaadin.aes.view.home.concretes.FoodPageView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class FoodPageViewModel {
    private static final Logger log = Logger.getLogger(FoodPageViewModel.class.getName());
    private List<Meal> meals= new ArrayList<>();
    //    private final FoodPageView view;
    private final MealService mealService;

    @Autowired
    public FoodPageViewModel(MealService mealService) {
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

    public void addItemToOrderBasket(FoodPageView view, Meal meal) {
        view.getOrderBasketView().getViewModel().addItem(meal);
//        orderBasketViewModel.addItem(meal);
//        orderBasketView.getViewModel().addItem(meal);
        updateCustomerMealGridData(view);
    }

    public void removeItemOrderBasket(FoodPageView view, Meal meal) {
        view.getOrderBasketView().getViewModel().removeItem(meal);
//        orderBasketView.getViewModel().removeItem(meal);
        updateCustomerMealGridData(view);
    }

    private void updateCustomerMealGridData(FoodPageView view) {
        view.getGridCustomerMeals().setItems(
                view.getOrderBasketView().getOrderConceptList().stream().sorted().toList()
        );
        view.getGridCustomerMeals().getDataProvider().refreshAll();
    }
}
