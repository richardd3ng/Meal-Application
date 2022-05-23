package model;

import java.util.Map;

/**
 * Class representing an ingredient
 * All stats are in grams unless otherwise specified
 */
public class Food implements FoodInterface {
    private String name;
    private String servingDescription;
    private double calories;
    private double totalFat;
    private double saturatedFat;
    private double protein;
    private NutritionFacts nutritionFacts;
    Map<FoodInterface, Double> ingredients;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return servingDescription;
    }

    public double getCalories() {
        return calories;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public double getSaturatedFat() {
        return saturatedFat;
    }

    public double getProtein() {
        return protein;
    }

    public Food(String name, String servingDescription, double calories, double totalFat, double saturatedFat, double protein) {
        this.name = name;
        this.servingDescription = servingDescription;
        this.calories = calories;
        this.totalFat = totalFat;
        this.saturatedFat = saturatedFat;
        this.protein = protein;
        this.ingredients = Map.of(this, 1.0);
        this.nutritionFacts = new NutritionFacts(this);
    }

    public Food(String name, String servingDescription, Map<FoodInterface, Double> ingredients) {
        this.name = name;
        this.servingDescription = servingDescription;
        this.ingredients = ingredients;
        double calories = 0, totalFat = 0, saturatedFat = 0, protein = 0;
        for (FoodInterface ingredient : ingredients.keySet()) {
            double serving = ingredients.get(ingredient);
            calories += ingredient.getCalories() * serving;
            totalFat += ingredient.getTotalFat() * serving;
            saturatedFat += ingredient.getSaturatedFat() * serving;
            protein += ingredient.getProtein() * serving;
        }
        this.calories = calories;
        this.totalFat = totalFat;
        this.saturatedFat = saturatedFat;
        this.protein = protein;
        this.nutritionFacts = new NutritionFacts(this);
    }

    @Override
    public Map<FoodInterface, Double> getIngredients() {
        return Map.of(this, 1.0);
    }

    @Override
    public void printNutritionFacts() {
        nutritionFacts.printNutritionFacts();
    }
}
