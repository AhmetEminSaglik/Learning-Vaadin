package org.vaadin.aes.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.vaadin.aes.enums.EnumPaymentMethod;
import org.vaadin.aes.model.concrete.PaymentMethod;
import org.vaadin.aes.service.abstracts.payment.method.PaymentMethodService;
import org.vaadin.aes.service.concretes.payment.method.PaymentMethodServiceImpl;

import java.util.List;
import java.util.logging.Logger;

@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {
    private static final Logger log = Logger.getLogger(ApplicationCommandLineRunner.class.getName());
    private final PaymentMethodService paymentMethodService;

    @Autowired
    public ApplicationCommandLineRunner(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @Override
    public void run(String... args) throws Exception {
        savePaymentMethodData();
    }

    private void savePaymentMethodData() {
        List<PaymentMethod> paymentMethodList = paymentMethodService.findAll();
        if (!paymentMethodList.isEmpty()) {
            log.info("Payment Method list data is already saved.");
            return;
        }
        log.info("Payment Method list data is empty. Enums will be saved");
        for (EnumPaymentMethod enums : EnumPaymentMethod.values()) {
            paymentMethodService.save(enums);
        }
    }
}
