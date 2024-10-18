package org.vaadin.aes.mealdb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.vaadin.aes.mealdb.model.ApiResponse;
import org.vaadin.aes.mealdb.model.MealDTOForJson;
import org.vaadin.aes.mealdb.model.MealFromJson;
import org.vaadin.aes.model.concrete.Meal;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class ApiService {
    private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/filter.php?i=chicken_breast";
    private static final Logger log = Logger.getLogger(ApiService.class.getName());
    private static List<Meal> meals;

    public List<Meal> getMeals() {

        if (meals == null || meals.isEmpty()) {
            try {
                ApiResponse apiResponse = fetchMeals();
                meals = convertMealJsonToMeal(apiResponse.getMeals());
                meals = meals.stream().sorted().toList();
            } catch (IOException e) {
                log.severe("Meal data was not retrieved");
                log.severe(e.getMessage());
            }
        }
        return meals;
    }

    public ApiResponse fetchMeals() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(API_URL);
        CloseableHttpResponse response = httpClient.execute(request);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getEntity().getContent(), ApiResponse.class);
    }

    private List<Meal> convertMealJsonToMeal(List<MealFromJson> mealsFromJson) {
        return mealsFromJson.stream().map(MealDTOForJson::toMeal)
                .collect(Collectors.toList());
    }

}
