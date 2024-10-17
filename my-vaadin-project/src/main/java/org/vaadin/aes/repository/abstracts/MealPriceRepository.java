package org.vaadin.aes.repository.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.aes.model.concrete.MealPrice;

public interface MealPriceRepository extends JpaRepository<MealPrice,Long> {

}
