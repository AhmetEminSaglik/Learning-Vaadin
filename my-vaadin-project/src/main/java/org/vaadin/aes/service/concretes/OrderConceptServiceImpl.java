package org.vaadin.aes.service.concretes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.aes.model.concrete.OrderConcept;
import org.vaadin.aes.repository.abstracts.OrderConceptRepository;
import org.vaadin.aes.service.abstracts.OrderConceptService;

import java.util.List;

@Service
public class OrderConceptServiceImpl implements OrderConceptService {
    private final OrderConceptRepository orderConceptRepository;

    private static final Logger log = LoggerFactory.getLogger(OrderConceptServiceImpl.class);

    @Autowired
    public OrderConceptServiceImpl(OrderConceptRepository orderConceptRepository) {
        this.orderConceptRepository = orderConceptRepository;
    }

    @Override
    public OrderConcept save(OrderConcept orderConcept) {
        orderConcept = orderConceptRepository.save(orderConcept);
        log.info("Order Concept data is saved: " + orderConcept);
        return orderConcept;
    }

    @Override
    public List<OrderConcept> save(List<OrderConcept> orderConceptList) {
        return orderConceptRepository.saveAll(orderConceptList);
    }

    @Override
    public OrderConcept findById(long id) {
        return orderConceptRepository.findById(id).get();
    }
}
