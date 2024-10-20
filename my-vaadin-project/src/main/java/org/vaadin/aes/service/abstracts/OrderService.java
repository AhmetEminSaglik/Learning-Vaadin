package org.vaadin.aes.service.abstracts;

import org.vaadin.aes.model.concrete.*;

import java.util.List;

public interface OrderService {
    Order save(Order order);
    List<Order> findAll();
    List<Order> findAllByUserId(long userId);
//    Order update(Order order);
}
