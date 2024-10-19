package org.vaadin.aes.viewmodel.home;

import com.nimbusds.jose.shaded.gson.Gson;
import com.vaadin.flow.component.UI;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.enums.EnumSessionData;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.model.concrete.OrderConcept;
import org.vaadin.aes.view.core.OrderConceptPriceUtility;
import org.vaadin.aes.view.core.notificationn.CustomNotification;
import org.vaadin.aes.view.home.concretes.OrderBasketView;

import java.util.Optional;
import java.util.logging.Logger;

public class OrderBasketViewModel {
    private final OrderBasketView view;
    private static final Logger log = Logger.getLogger(OrderBasketViewModel.class.getName());

    public OrderBasketViewModel(OrderBasketView orderBasketView) {
        this.view = orderBasketView;
    }

    public void addItem(Meal meal) {
        Optional<OrderConcept> existingOrder = view.getOrderConceptList()
                .stream()
                .filter(e -> e.getMeal().equals(meal))
                .findFirst();
        if (existingOrder.isPresent()) {
            existingOrder.get().increaseQuantity();
        } else {
            OrderConcept newOrder = new OrderConcept(meal, 1);
            view.getOrderConceptList().add(newOrder);
        }

        updateCartItemSize();
    }

    public void removeItem(Meal meal) {
        Optional<OrderConcept> existingOrder = view.getOrderConceptList()
                .stream()
                .filter(e -> e.getMeal().equals(meal))
                .findFirst();
        if (existingOrder.isPresent()) {
            OrderConcept Order = existingOrder.get();
            if (Order.getQuantity() > 1) {
                Order.decreaseQuantity();
            } else {
                view.getOrderConceptList().remove(Order);
            }
        } else {
            CustomNotification.showShort(getClass().getSimpleName() + " Meal not found : " + meal.getName() + " - " + meal.getThumbnail());
        }
        updateCartItemSize();
    }

    private void updateCartItemSize() {
        view.setTotalPrice(calculateTotalPriceInOrderBasket());
//        view.getOrderConceptList().forEach(meal -> log.info("Current items in Customer's Cart: " + meal));
    }

    private int calculateTotalItemInOrderBasket() {
        int total = view.getOrderConceptList().stream().mapToInt(OrderConcept::getQuantity).sum();
        log.info("Calculated total item value is : " + total);
        return total;

    }

    private double calculateTotalPriceInOrderBasket() {
        double total = OrderConceptPriceUtility.calculate(view.getOrderConceptList());
//        log.info("Calculated total price in order basket is : " + total);
        return total;

    }

    public void buyItems() {
//        String orderJson = serializeOrderToJson(view.getOrderList());
        UI.getCurrent().getSession().setAttribute(EnumSessionData.ORDER_CONCEPT_LIST.getName(), view.getOrderConceptList());
        UI.getCurrent().navigate(EnumPageURL.PAYMENT_METHOD.getUrl());
    }

    private String serializeOrderToJson(OrderConcept order) {
        // Order nesnesini JSON formatına çevirme işlemini gerçekleştirin
        // (Bu işlem için bir JSON kütüphanesi kullanabilirsiniz, örneğin Gson)
        return new Gson().toJson(order);
    }
}
