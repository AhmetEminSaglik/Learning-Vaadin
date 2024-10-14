package org.vaadin.aes.repository.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.aes.model.concrete.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Long> {
    PaymentMethod findByName(String name);
}
