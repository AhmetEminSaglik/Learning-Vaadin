package viewmodel.home.onlinepurchasing;

import org.vaadin.aes.model.concrete.Address;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.model.concrete.Order;
import org.vaadin.aes.model.concrete.OrderConcept;
import org.vaadin.aes.service.abstracts.OrderConceptService;
import org.vaadin.aes.service.abstracts.OrderService;
import org.vaadin.aes.service.abstracts.address.AddressService;
import org.vaadin.aes.service.abstracts.meal.MealService;
import org.vaadin.aes.view.home.concretes.onlinepurchasing.OnlinePurchasingView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OnlinePurchasingViewModel {
    private static final Logger log = Logger.getLogger(OnlinePurchasingViewModel.class.getName());

    private final OnlinePurchasingView view;
    private final OrderService orderService;
    private final OrderConceptService orderConceptService;
    private final AddressService addressService;
    private final MealService mealService;

    private Order order;

    public OnlinePurchasingViewModel(OnlinePurchasingView view
            , OrderConceptService orderConceptService
            , OrderService orderService
            , AddressService addressService
            , MealService mealService) {
        this.view = view;
        this.orderConceptService = orderConceptService;
        this.orderService = orderService;
        this.addressService = addressService;
        this.mealService = mealService;
    }

    public void saveData() {
        order = view.getOrder();
//        saveAddress();
//        saveOrderConcept();
        log.info("Order to save: " + order);
        order = saveOrder();
        saveOrderConcept();
        clearOrderData();
    }

    private void clearOrderData() {
        order = new Order();
    }

/*    private List<Meal> saveMealList() {
        List<Meal> savedMealList = new ArrayList<>();
        view.getOrder().getOrderConcepts()
                .stream()
                .forEach(e -> {
                    Meal savedMeal = mealService.save(e.getMeal());
                    savedMealList.add(savedMeal);
                    log.info("savedMeal: " + savedMeal);
                });

        List<OrderConcept> orderConceptList = view.getOrder().getOrderConcepts();
        for (int i = 0; i < orderConceptList.size(); i++) {
            Meal meal = orderConceptList.get(i).getMeal();
            meal = mealService.save(meal);
            savedMealList.add(meal);

        }
        return savedMealList;
    }*/


    private void saveOrderConcept() {
        List<OrderConcept> savedOrderConceptList = new ArrayList<>();
        order.getOrderConcepts().forEach(e -> {
            Meal tmpMeal = mealService.save(e.getMeal());
            e.setMeal(tmpMeal);
            e.setOrder(order);
            savedOrderConceptList.add(orderConceptService.save(e));
        });
        order.setOrderConcepts(savedOrderConceptList);
        List<OrderConcept> orderConceptList = orderConceptService.save(order.getOrderConcepts());
        orderConceptList.stream().forEach(e -> log.info("Data is saved: " + orderConceptList));
        /*List<Meal> existindMealList = saveMealList();
        List<OrderConcept> orderConceptList = new ArrayList<>();

        view.getOrder().getOrderConcepts().forEach(e -> {

            orderConceptList.add(meal, )

        });
        existindMealList.forEach(e -> {
            OrderConcept tmpOrderConcept = existindMealList.stream().filter(f -> f.getName().equals(f.getName())).findFirst().get();
        });

    */
    }

    private Order saveOrder() {
        order = orderService.save(order);
        log.info("saved Order: " + order);
        return order;
    }

    private void saveAddress() {
        Address address = addressService.save(order.getAddress());
        order.setAddress(address);
        log.info("Address is saved: " + address);
    }

    public void savePayment() {
//        Payment payment = pay
    }


}
