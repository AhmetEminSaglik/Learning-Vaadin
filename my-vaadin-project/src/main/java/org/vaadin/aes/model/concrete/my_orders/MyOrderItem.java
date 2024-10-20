package org.vaadin.aes.model.concrete.my_orders;

import org.vaadin.aes.model.concrete.Order;
import org.vaadin.aes.model.concrete.OrderConcept;
import org.vaadin.aes.model.concrete.Payment;
import java.sql.Timestamp;
import java.util.List;

public class MyOrderItem {
    private Order order;
//    private List<OrderConcept> orderConceptList;
    private Timestamp createdAt;
    private Payment payment;
//    private PaymentMethod paymentMethod;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

//    public List<OrderConcept> getOrderConceptList() {
//        return orderConceptList;
//    }
//
//    public void setOrderConceptList(List<OrderConcept> orderConceptList) {
//        this.orderConceptList = orderConceptList;
//    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
