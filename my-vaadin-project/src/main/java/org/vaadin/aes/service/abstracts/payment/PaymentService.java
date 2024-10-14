package org.vaadin.aes.service.abstracts.payment;

import org.vaadin.aes.model.concrete.Payment;

import java.util.List;

public interface PaymentService {
    Payment save(Payment payment);

    List<Payment> findAll();

    List<Payment> findAllByUserId(long id);
}
