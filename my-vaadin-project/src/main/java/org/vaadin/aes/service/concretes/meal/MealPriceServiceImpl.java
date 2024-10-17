package org.vaadin.aes.service.concretes.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.aes.model.concrete.MealPrice;
import org.vaadin.aes.repository.abstracts.MealPriceRepository;
import org.vaadin.aes.service.abstracts.meal.MealPriceService;

import java.util.List;

@Service
public class MealPriceServiceImpl implements MealPriceService {
    private final MealPriceRepository mealPriceRepository;

    @Autowired
    public MealPriceServiceImpl(MealPriceRepository mealPriceRepository) {
        this.mealPriceRepository = mealPriceRepository;
    }

    @Override
    public MealPrice save(MealPrice mealPrice) {
        return mealPriceRepository.save(mealPrice);
    }

    @Override
    public List<MealPrice> save(List<MealPrice> list) {
        return mealPriceRepository.saveAll(list);
    }
}
