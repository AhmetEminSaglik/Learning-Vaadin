package org.vaadin.aes.model.concrete;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "total")
    private double total;

    @OneToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    public Payment() {
    }

    public Payment(Long id, Order order, double total, PaymentMethod paymentMethod) {
        this.id = id;
        this.order = order;
        this.total = total;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", order=" + order +
                ", total=" + total +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
