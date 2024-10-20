package org.vaadin.aes.repository.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.aes.model.concrete.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserIdOrderByCreatedAtDesc(long userId);
}
