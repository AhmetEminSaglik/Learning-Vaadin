package org.vaadin.aes.repository.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.aes.model.concrete.OrderConcept;

public interface OrderConceptRepository extends JpaRepository<OrderConcept,Long> {
}
