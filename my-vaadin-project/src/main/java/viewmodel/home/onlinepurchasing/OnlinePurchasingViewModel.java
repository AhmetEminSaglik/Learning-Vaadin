package viewmodel.home.onlinepurchasing;

import org.vaadin.aes.model.concrete.Address;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.model.concrete.Order;
import org.vaadin.aes.model.concrete.Payment;
import org.vaadin.aes.service.abstracts.OrderService;
import org.vaadin.aes.service.abstracts.address.AddressService;
import org.vaadin.aes.service.meal.MealService;
import org.vaadin.aes.view.home.concretes.onlinepurchasing.OnlinePurchasingView;

import java.util.logging.Logger;

public class OnlinePurchasingViewModel {
    private static final Logger log = Logger.getLogger(OnlinePurchasingViewModel.class.getName());

    private final OnlinePurchasingView view;
    private final OrderService orderService;
    private final AddressService addressService;
    private final MealService mealService;

    private Order order;

    public OnlinePurchasingViewModel(OnlinePurchasingView view
            , OrderService orderService
            , AddressService addressService
            , MealService mealService) {
        this.view = view;
        this.orderService = orderService;
        this.addressService = addressService;
        this.mealService = mealService;
    }

    public void saveData() {
        order = view.getOrder();
        saveAddress();
        saveMeal();
        saveOrder();
    }

    private void saveMeal() {
        view.getOrder().getOrderConcepts()
                .stream()
                .forEach(e -> {
                    Meal savedMeal=mealService.save(e.getMeal());
                log.info("savedMeal: "+savedMeal);});
    }

    private void saveOrder() {
        Order order = orderService.save(view.getOrder());
        log.info("saved Order: " + order);
    }

    private void saveAddress() {
        Address address = addressService.save(order.getAddress());
        log.info("Address is saved: " + address);
    }

    public void savePayment() {
//        Payment payment = pay
    }


}
