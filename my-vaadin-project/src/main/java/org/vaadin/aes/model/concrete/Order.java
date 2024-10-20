package org.vaadin.aes.model.concrete;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //    @OneToOne
//    @JoinColumn(name = "address_id", nullable = false)
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    //    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
//    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<OrderConcept> orderConcepts = new ArrayList<>();

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @Column(name = "createdAt")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    public Order() {
    }

    public Order(Long id, User user, Address address, List<OrderConcept> orderConcepts, Payment payment, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.orderConcepts = orderConcepts;
        this.payment = payment;
        this.createdAt = createdAt;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderConcept> getOrderConcepts() {
        return orderConcepts;
    }

    public void setOrderConcepts(List<OrderConcept> orderConcepts) {
        this.orderConcepts = orderConcepts;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
//                ", user=" + user +
//                ", address=" + address +
                ", orderConcepts=" + orderConcepts +
//                ", payment=" + payment +
                ", createdAt=" + createdAt +
                '}';
    }
}

