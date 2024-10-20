package org.vaadin.aes.model.concrete;

import jakarta.persistence.*;
import org.vaadin.aes.view.core.OrderConceptPriceUtility;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @OneToOne(fetch = FetchType.EAGER)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "total")
    private double total;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    public Payment() {
    }

    public Payment(Long id, User user, Order order, double total, PaymentMethod paymentMethod) {
        this.id = id;
        this.user = user;
        this.order = order;
        this.total = total;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void calculateTotal() {
        this.total = OrderConceptPriceUtility.calculate(getOrder().getOrderConcepts());
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
//                ", order=" + order +
                ", total=" + total +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
