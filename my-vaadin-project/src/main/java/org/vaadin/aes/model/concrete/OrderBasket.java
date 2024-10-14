package org.vaadin.aes.model.concrete;

import com.vaadin.flow.server.VaadinSession;
import org.vaadin.aes.enums.EnumDTO;
import org.vaadin.aes.model.dto.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderBasket {
    private List<Order> Order = new ArrayList<>();
    private double totalPrice;
    private User user = (User) VaadinSession.getCurrent().getAttribute(EnumDTO.USER_DATA.getName());

    public List<Order> getMeals() {
        return Order;
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
                "Order=" + Order +
                ", totalPrice=" + totalPrice +
                ", user=" + user +
                '}';
    }
}
