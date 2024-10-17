package viewmodel.home;

import org.vaadin.aes.mealdb.service.ApiService;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.service.abstracts.meal.MealService;
import org.vaadin.aes.view.home.concretes.FoodView;

import java.util.List;
import java.util.logging.Logger;

public class FoodViewModel {
    private static final Logger log = Logger.getLogger(FoodViewModel.class.getName());
    private List<Meal> meals;
    private final FoodView view;

    private final MealService mealService;

    public FoodViewModel(FoodView foodView, MealService mealService) {
        this.view = foodView;
        this.mealService = mealService;

        fillMeals();
        if (!ApiService.isDataSaved()) {
            mealService.saveList(meals);
            ApiService.updateDataIsSaved();
        }

    }

    private void fillMeals() {
        ApiService apiService = new ApiService();
        meals = apiService.getMeals();
    }

    public List<Meal> getMeals() {
        meals.stream().forEach(mealFromJson -> log.info(mealFromJson.toString()));
        return meals;
    }

    public void addItemToOrderBasket(Meal meal) {
        view.getOrderBasketView().getViewModel().addItem(meal);
        updateCustomerMealGridData();
    }

    public void removeItemOrderBasket(Meal meal) {
        view.getOrderBasketView().getViewModel().removeItem(meal);
        updateCustomerMealGridData();
    }

    private void updateCustomerMealGridData() {
        view.getGridCustomerMeals().setItems(
                view.getOrderBasketView().getOrderConceptList().stream().sorted().toList()
        );
        view.getGridCustomerMeals().getDataProvider().refreshAll();
    }
}
