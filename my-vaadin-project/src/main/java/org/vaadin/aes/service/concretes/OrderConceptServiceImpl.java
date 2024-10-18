package org.vaadin.aes.service.concretes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.model.concrete.OrderConcept;
import org.vaadin.aes.repository.abstracts.OrderConceptRepository;
import org.vaadin.aes.service.abstracts.OrderConceptService;
import org.vaadin.aes.service.abstracts.meal.MealService;

import java.util.List;

@Service
public class OrderConceptServiceImpl implements OrderConceptService {
    private final OrderConceptRepository orderConceptRepository;
    private final MealService mealService;
    private static final Logger log = LoggerFactory.getLogger(OrderConceptServiceImpl.class);

    @Autowired
    public OrderConceptServiceImpl(OrderConceptRepository orderConceptRepository, MealService mealService) {
        this.orderConceptRepository = orderConceptRepository;
        this.mealService = mealService;
    }

    @Override
    public OrderConcept save(OrderConcept orderConcept) {
        log.info("OrderConcept Save Method First Line: "+orderConcept);
        Meal tmpMeal = mealService.save(orderConcept.getMeal());
        orderConcept.setMeal(tmpMeal);
        log.info("Meal: "+tmpMeal);
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
