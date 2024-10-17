package org.vaadin.aes.service.abstracts;

import org.vaadin.aes.model.concrete.OrderConcept;

import java.util.List;

public interface OrderConceptService {
    OrderConcept save(OrderConcept orderConcept);
    List<OrderConcept> save(List<OrderConcept> orderConceptList);
    OrderConcept findById(long id);

}
