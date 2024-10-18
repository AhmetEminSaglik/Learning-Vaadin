package org.vaadin.aes.repository.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.aes.model.concrete.Meal;

public interface MealRepository extends JpaRepository<Meal,Long> {
    Meal findByName(String name);

}
