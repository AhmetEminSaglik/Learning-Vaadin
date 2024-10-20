package org.vaadin.aes.viewmodel.home.onlinepurchasing;

import com.vaadin.flow.component.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.aes.enums.EnumPaymentMethod;
import org.vaadin.aes.enums.EnumSessionData;
import org.vaadin.aes.model.concrete.*;
import org.vaadin.aes.service.abstracts.OrderConceptService;
import org.vaadin.aes.service.abstracts.OrderService;
import org.vaadin.aes.service.abstracts.address.AddressService;
import org.vaadin.aes.service.abstracts.meal.MealService;
import org.vaadin.aes.service.abstracts.payment.PaymentService;
import org.vaadin.aes.service.abstracts.payment.method.PaymentMethodService;
import org.vaadin.aes.view.home.concretes.onlinepurchasing.OnlinePurchasingView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class OnlinePurchasingViewModel {
    private static final Logger log = Logger.getLogger(OnlinePurchasingViewModel.class.getName());

    private final OrderService orderService;
    private final OrderConceptService orderConceptService;
    private final AddressService addressService;
    private final MealService mealService;
    private final PaymentService paymentService;
    private final PaymentMethodService paymentMethodService;

    private Order order;
    private Payment payment;

    @Autowired
    public OnlinePurchasingViewModel(
            OrderConceptService orderConceptService
            , OrderService orderService
            , AddressService addressService
            , MealService mealService
            , PaymentService paymentService
            , PaymentMethodService paymentMethodService) {
        this.orderConceptService = orderConceptService;
        this.orderService = orderService;
        this.addressService = addressService;
        this.mealService = mealService;
        this.paymentService = paymentService;
        this.paymentMethodService = paymentMethodService;


    }

    public void saveData(OnlinePurchasingView view) {
        order = (Order) UI.getCurrent().getSession().getAttribute(EnumSessionData.ORDER.getName());
        payment = (Payment) UI.getCurrent().getSession().getAttribute(EnumSessionData.PAYMENT.getName());

        log.info("Gelen Order " + order);
        saveAddress();
        order = saveOrder();
        saveOrderConcept();
        savePayment();
        clearOrderData(view);
    }

    private void clearOrderData(OnlinePurchasingView view) {
//        order = new Order();
//        order = new Order();
        view.clearOrder();
        log.info("Before clear: " + UI.getCurrent().getSession().getAttribute(EnumSessionData.ORDER_CONCEPT_LIST.getName()));
        List<OrderConcept> orderConceptList = (List<OrderConcept>) UI.getCurrent().getSession().getAttribute(EnumSessionData.ORDER_CONCEPT_LIST.getName());
        orderConceptList.clear();
        log.info("After clear: " + UI.getCurrent().getSession().getAttribute(EnumSessionData.ORDER_CONCEPT_LIST.getName()));
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
//            Meal tmpMeal = mealService.save(e.getMeal());
//            e.setMeal(tmpMeal);
            e.setOrder(order);
            log.info("OrderConcept data: " + e);
            OrderConcept orderConcept = orderConceptService.save(e);
            savedOrderConceptList.add(orderConcept);
        });
        order.setOrderConcepts(savedOrderConceptList);
        List<OrderConcept> orderConceptList = orderConceptService.save(order.getOrderConcepts());
        orderConceptList.stream().forEach(e -> log.info("Data is saved: " + e));
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
    /*private Order updateOrderConceptInOrder() {

        order = orderService.save(order);
        log.info("Updated Order: " + order);
        return order;
    }*/

    private void saveAddress() {
        Address address = addressService.save(order.getAddress());
        order.setAddress(address);
        log.info("Address is saved: " + address);
    }

    public void savePayment() {
//        log.info("payment.getPaymentMethod().getName(): "+payment.getPaymentMethod().getName());
//        log.info("EnumPaymentMethod.values: "+EnumPaymentMethod.values());
        PaymentMethod paymentMethod = paymentMethodService.findByName(EnumPaymentMethod.valueOf(payment.getPaymentMethod().getName().toUpperCase()));
        payment.setPaymentMethod(paymentMethod);
        payment.setUser(order.getUser());
        log.info("Payment before save; " + payment);
        payment = paymentService.save(payment);
        log.info("Payment After save; " + payment);

//        Payment payment = pay
    }


}
