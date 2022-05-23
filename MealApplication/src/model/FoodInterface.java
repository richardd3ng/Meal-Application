package model;

import java.util.Map;

public interface FoodInterface {
    String getName();

    String getDescription();

    double getCalories();

    double getTotalFat();

    double getSaturatedFat();

    double getProtein();

    Map<FoodInterface, Double> getIngredients();

    void printNutritionFacts();
}
