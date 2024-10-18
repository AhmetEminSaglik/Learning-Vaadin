package org.vaadin.aes.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.vaadin.aes.enums.EnumPaymentMethod;
import org.vaadin.aes.mealdb.service.ApiService;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.model.concrete.PaymentMethod;
import org.vaadin.aes.service.abstracts.meal.MealService;
import org.vaadin.aes.service.abstracts.payment.method.PaymentMethodService;

import java.util.List;
import java.util.logging.Logger;

@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {
    private static final Logger log = Logger.getLogger(ApplicationCommandLineRunner.class.getName());
    private final PaymentMethodService paymentMethodService;
    private final MealService mealService;

    @Autowired
    public ApplicationCommandLineRunner(PaymentMethodService paymentMethodService, MealService mealService) {
        this.paymentMethodService = paymentMethodService;
        this.mealService = mealService;
    }

    @Override
    public void run(String... args) throws Exception {
        savePaymentMethodData();
        retrieveAndSaveFoodsFromFreeApi();
    }

    private void savePaymentMethodData() {
        List<PaymentMethod> paymentMethodList = paymentMethodService.findAll();
        if (!paymentMethodList.isEmpty()) {
            log.info("Payment Method list data is already saved.");
            return;
        }
        log.info("Payment Method list data is empty. Enums will be saved");
        for (EnumPaymentMethod enums : EnumPaymentMethod.values()) {
            paymentMethodService.save(enums);
        }
    }

    private void retrieveAndSaveFoodsFromFreeApi() {
        ApiService apiService = new ApiService();
        List<Meal> meals = apiService.getMeals();
        mealService.saveList(meals);
    }
}
