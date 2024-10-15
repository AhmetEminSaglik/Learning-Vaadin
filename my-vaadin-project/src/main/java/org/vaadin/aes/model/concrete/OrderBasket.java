package org.vaadin.aes.model.concrete;

import com.vaadin.flow.server.VaadinSession;
import org.vaadin.aes.enums.EnumDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderBasket {
    private List<OrderConcept> orderConcepts = new ArrayList<>();
    private double totalPrice;
    private User user = (User) VaadinSession.getCurrent().getAttribute(EnumDTO.USER_DATA.getName());

    public List<OrderConcept> getOrderConcepts() {
        return orderConcepts;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "OrderBasket{" +
                "OrderConcept=" + orderConcepts +
                ", totalPrice=" + totalPrice +
                ", user=" + user +
                '}';
    }
}
