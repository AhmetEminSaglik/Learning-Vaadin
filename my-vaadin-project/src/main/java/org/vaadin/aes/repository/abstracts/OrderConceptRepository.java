package org.vaadin.aes.repository.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.aes.model.concrete.OrderConcept;

import java.util.List;

public interface OrderConceptRepository extends JpaRepository<OrderConcept,Long> {
    List<OrderConcept> findAllByOrderId(long orderId);
}
