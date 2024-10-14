package viewmodel.home;

import com.nimbusds.jose.shaded.gson.Gson;
import com.vaadin.flow.component.UI;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.enums.EnumSessionData;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.model.dto.Order;
import org.vaadin.aes.view.home.concretes.OrderBasketView;
import org.vaadin.aes.view.core.notificationn.CustomNotification;

import java.util.Optional;
import java.util.logging.Logger;

public class OrderBasketViewModel {
    private final OrderBasketView view;
    private static final Logger log = Logger.getLogger(OrderBasketViewModel.class.getName());

    public OrderBasketViewModel(OrderBasketView orderBasketView) {
        this.view = orderBasketView;
    }

    public void addItem(Meal meal) {
        Optional<Order> existingOrder = view.getOrderList()
                .stream()
                .filter(e -> e.getMeal().equals(meal))
                .findFirst();
        if (existingOrder.isPresent()) {
            existingOrder.get().increaseQuantity();
        } else {
            Order newOrder = new Order(meal, 1);
            view.getOrderList().add(newOrder);
        }

        updateCartItemSize();
    }

    public void removeItem(Meal meal) {
        Optional<Order> existingOrder = view.getOrderList()
                .stream()
                .filter(e -> e.getMeal().equals(meal))
                .findFirst();
        if (existingOrder.isPresent()) {
            Order Order = existingOrder.get();
            if (Order.getQuantity() > 1) {
                Order.decreaseQuantity();
            } else {
                view.getOrderList().remove(Order);
            }
        } else {
            CustomNotification.show(getClass().getSimpleName() + " Meal not found : " + meal.getName() + " - " + meal.getThumbnail());
        }
        updateCartItemSize();
    }

    private void updateCartItemSize() {
        view.setTotalPrice(calculateTotalPriceInOrderBasket());
        view.getOrderList().forEach(meal -> log.info("Current items in Customer's Cart: " + meal));
    }

    private int calculateTotalItemInOrderBasket() {
        int total = view.getOrderList().stream().mapToInt(Order::getQuantity).sum();
        log.info("Calculated total item value is : " + total);
        return total;

    }

    private double calculateTotalPriceInOrderBasket() {
        double total = view.getOrderList()
                .stream()
                .mapToDouble(tmp -> tmp.getMeal().getPrice() * tmp.getQuantity()).sum();
        log.info("Calculated total price in order basket is : " + total);
        return total;

    }

    public void buyItems() {
//        String orderJson = serializeOrderToJson(view.getOrderList());
        UI.getCurrent().getSession().setAttribute(EnumSessionData.ORDER_LIST.getName(),view.getOrderList());
        UI.getCurrent().navigate(EnumPageURL.PAYMENT_METHOD.getUrl());
    }
    private String serializeOrderToJson(Order order) {
        // Order nesnesini JSON formatına çevirme işlemini gerçekleştirin
        // (Bu işlem için bir JSON kütüphanesi kullanabilirsiniz, örneğin Gson)
        return new Gson().toJson(order);
    }
}
