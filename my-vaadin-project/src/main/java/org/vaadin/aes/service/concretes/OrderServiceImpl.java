package org.vaadin.aes.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vaadin.aes.model.concrete.Order;
import org.vaadin.aes.repository.abstracts.OrderConceptRepository;
import org.vaadin.aes.repository.abstracts.OrderRepository;
import org.vaadin.aes.service.abstracts.OrderService;

import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderConceptRepository orderConceptRepository;
    private static final Logger log = Logger.getLogger(OrderServiceImpl.class.getName());

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderConceptRepository orderConceptRepository) {
        this.orderRepository = orderRepository;
        this.orderConceptRepository = orderConceptRepository;
    }

    @Override
    public Order save(Order order) {
        log.info("SAVE PROCESS First Line --> : " + order);
        order = orderRepository.save(order);
        log.info("Data is saved: " + order);
        return order;
    }

    /*@Transactional
    @Override
    public Order update(Order order) {
//        return orderRepository.update(order);
    }*/

}
