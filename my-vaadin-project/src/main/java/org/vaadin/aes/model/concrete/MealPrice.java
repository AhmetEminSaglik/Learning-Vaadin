package org.vaadin.aes.model.concrete;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "meal_prices")
public class MealPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @Column(name = "price")
    private double price;

    @Column(name = "createdAt")
    private Timestamp createdAt;

    public MealPrice() {
    }

    public MealPrice(Long id, Meal meal, double price, Timestamp createdAt) {
        this.id = id;
        this.meal = meal;
        this.price = price;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "MealPrice{" +
                "id=" + id +
                ", meal=" + meal +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }
}
