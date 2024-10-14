package org.vaadin.aes.repository.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.aes.model.concrete.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByUserId(long userId);
}
