package org.vaadin.aes.view.core;

import org.vaadin.aes.model.concrete.OrderConcept;

import java.util.List;

public class OrderConceptPriceUtility {
    public static double calculate(List<OrderConcept> list) {
        return list
                .stream()
                .mapToDouble(tmp -> tmp.getMeal().getPrice() * tmp.getQuantity())
                .sum();
    }
}
