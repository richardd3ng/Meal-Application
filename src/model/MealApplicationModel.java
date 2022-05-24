package model;

import model.file_parsing.MealsBuilder;
import model.food.FoodInterface;

import java.util.List;

public class MealApplicationModel {

    private MealGenerator mealGenerator;

    public MealApplicationModel(int minCalories, int maxCalories, int maxTotalFat, int minProtein) {
        MealsBuilder.buildMeals();
        this.mealGenerator = new MealGenerator(
                List.of(MealsBuilder.BREAKFAST_OPTIONS, MealsBuilder.LUNCH_OPTIONS, MealsBuilder.DINNER_OPTIONS, MealsBuilder.SNACK_OPTIONS),
                minCalories, maxCalories, maxTotalFat, minProtein
        );
    }

    public List<List<FoodInterface>> getRandomCombination() {
        return mealGenerator.getRandomCombination();
    }

    public void printAllCombinations() {
        mealGenerator.printAllCombinations();
    }
}
