package org.vaadin.aes.service.concretes.payment.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.aes.enums.EnumPaymentMethod;
import org.vaadin.aes.model.concrete.PaymentMethod;
import org.vaadin.aes.repository.abstracts.PaymentMethodRepository;
import org.vaadin.aes.service.abstracts.payment.method.PaymentMethodService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    private static final Logger log = Logger.getLogger(PaymentMethodServiceImpl.class.getName());
    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        log.info("Class is initialized.");
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public PaymentMethod save(EnumPaymentMethod enumPaymentMethod) {
        PaymentMethod paymentMethod = paymentMethodRepository.save(new PaymentMethod(enumPaymentMethod.getName()));
        log.info("Data is saved: " + paymentMethod);
        return paymentMethod;
    }

    @Override
    public PaymentMethod findByName(EnumPaymentMethod enumPaymentMethod) {
        PaymentMethod paymentMethod = paymentMethodRepository.findByName(enumPaymentMethod.getName());
        log.info("Data is found: " + paymentMethod);
        return paymentMethod;
    }

    @Override
    public List<PaymentMethod> findAll() {
        List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll();
        log.info("All Data is found: --> Size = " + paymentMethodList.size());
        return paymentMethodList;
    }
}
