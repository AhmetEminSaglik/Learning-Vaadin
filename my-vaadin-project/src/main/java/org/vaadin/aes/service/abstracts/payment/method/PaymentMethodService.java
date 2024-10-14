package org.vaadin.aes.service.abstracts.payment.method;

import org.vaadin.aes.enums.EnumPaymentMethod;
import org.vaadin.aes.model.concrete.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethod save(EnumPaymentMethod enumPaymentMethod);

    PaymentMethod findByName(EnumPaymentMethod enumPaymentMethod);

    List<PaymentMethod> findAll();
}
