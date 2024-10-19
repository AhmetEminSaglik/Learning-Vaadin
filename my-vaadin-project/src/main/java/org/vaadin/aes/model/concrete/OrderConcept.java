package org.vaadin.aes.model.concrete;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "order_concepts")
public class OrderConcept implements Comparable<OrderConcept>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(cascade = {CascadeType.PERSIST})
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @ManyToOne(cascade = { CascadeType.MERGE})
    @JoinColumn(name = "order_id")
    private Order order;

    //    @ManyToOne(cascade ={ CascadeType.MERGE, CascadeType.PERSIST})
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @Column(name = "quantity")
    private int quantity;

    public OrderConcept() {
    }

    public OrderConcept(Meal meal, int quantity) {
        this.meal = meal;
        this.quantity = quantity;
    }

    public OrderConcept(Long id, Order order, Meal meal, int quantity) {
        this.id = id;
        this.order = order;
        this.meal = meal;
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {
        quantity--;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "OrderConcept{" +
                "id=" + id +
//                ", order=" + order.getId() +
                ", meal=" + meal +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public int compareTo(OrderConcept o) {
        return meal.getName().compareTo(o.meal.getName());
    }
}
