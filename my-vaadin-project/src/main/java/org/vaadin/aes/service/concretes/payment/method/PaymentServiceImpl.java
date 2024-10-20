package org.vaadin.aes.service.concretes.payment.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.aes.model.concrete.Payment;
import org.vaadin.aes.repository.abstracts.PaymentRepository;
import org.vaadin.aes.service.abstracts.payment.PaymentService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;

    private static final Logger log = Logger.getLogger(PaymentServiceImpl.class.getName());

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment save(Payment payment) {
        payment = repository.save(payment);
        log.info("Data is saved: " + payment);
        return payment;
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> paymentList = repository.findAll();
        log.info("Data is retrieved: Size= " + paymentList.size());
        return paymentList;
    }

    @Override
    public List<Payment> findAllByUserId(long id) {
        return repository.findAllByUserId(id);
    }

    @Override
    public Payment findByOrderId(long orderId) {
        Payment payment = repository.findByOrderId(orderId);
        log.info("Found Payment by orderId: " + orderId + " Payment: " + payment);
        return payment;
    }

}
