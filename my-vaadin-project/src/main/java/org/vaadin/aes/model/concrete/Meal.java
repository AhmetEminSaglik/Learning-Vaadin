package org.vaadin.aes.model.concrete;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meals")
public class Meal implements Comparable<Meal> {

    private static final Logger log = LoggerFactory.getLogger(Meal.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "meal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MealPrice> priceList;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Override
    public int compareTo(Meal o) {
        return this.getName().compareTo(o.getName());
    }

    public double getPrice() {
        if (priceList != null) {
            return priceList.get(priceList.size() - 1).getPrice();
        }
        return -1.00;
    }

    public void setPrice(double price) {
        log.info("Price (" + price + ") will be set To " + this);
        if (priceList == null) {
            priceList = new ArrayList<>();
        }
        MealPrice mealPrice = new MealPrice();
        mealPrice.setMeal(this);
        mealPrice.setPrice(price);
        priceList.add(mealPrice);
        log.info("Price is added: " + getPrice());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + getPrice() +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
