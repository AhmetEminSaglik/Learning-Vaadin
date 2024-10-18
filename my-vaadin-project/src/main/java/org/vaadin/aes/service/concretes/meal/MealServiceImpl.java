package org.vaadin.aes.service.concretes.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.aes.model.concrete.Meal;
import org.vaadin.aes.model.concrete.MealPrice;
import org.vaadin.aes.repository.abstracts.MealRepository;
import org.vaadin.aes.service.abstracts.meal.MealPriceService;
import org.vaadin.aes.service.abstracts.meal.MealService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private static final Logger log = LoggerFactory.getLogger(MealServiceImpl.class);
    private final MealPriceService mealPriceService;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository, MealPriceService mealPriceService) {
        this.mealRepository = mealRepository;
        this.mealPriceService = mealPriceService;
    }

    @Override
    public Meal save(Meal meal) {
        log.info("gelen meal: " + meal);
        Meal savedMeal = mealRepository.findByName(meal.getName());
        log.info("savedMeal : " + savedMeal);
        if (savedMeal == null) {
            log.info("savedMeal null oldugu icin savlendi: " + savedMeal);
            savedMeal = mealRepository.save(meal);
            log.info("savedMeal son durum: " + savedMeal);
        } else {
            log.info("Meal is registered already: " + savedMeal);
            if (savedMeal.getPrice() != meal.getPrice()) {
                log.info("Meal Price will be added as a new price");
                MealPrice mealPrice = new MealPrice();
                mealPrice.setMeal(savedMeal);
                mealPrice.setPrice(meal.getPrice());
                mealPriceService.save(mealPrice);
            }
        }
        return savedMeal;

    }

    @Override
    public List<Meal> saveList(List<Meal> list) {
//        return mealRepository.saveAll(list);
        List<Meal> savedMealList = new ArrayList<>();
        for (Meal meal : list) {
            savedMealList.add(save(meal));
        }
        return savedMealList;
    }

    @Override
    public Meal findById(Long id) {
        return mealRepository.findById(id).get();
    }

    @Override
    public List<Meal> findAll() {
        return mealRepository.findAll();
    }
}
