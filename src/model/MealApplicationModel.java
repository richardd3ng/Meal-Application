package model;

import model.file_parsing.MealsBuilder;

import java.util.List;

public class MealApplicationModel {

    private MealGenerator mealGenerator;

    public MealApplicationModel(int minCalories, int maxCalories, int minProtein) {
        MealsBuilder.buildMeals();
        System.out.println(MealsBuilder.BREAKFAST_OPTIONS.size());
        System.out.println(MealsBuilder.LUNCH_OPTIONS.size());
        System.out.println(MealsBuilder.DINNER_OPTIONS.size());
        System.out.println(MealsBuilder.SNACK_OPTIONS.size());

        this.mealGenerator = new MealGenerator(
                MealsBuilder.BREAKFAST_OPTIONS, MealsBuilder.LUNCH_OPTIONS, MealsBuilder.DINNER_OPTIONS, MealsBuilder.SNACK_OPTIONS,
                minCalories, maxCalories, minProtein
        );
    }

    public List<List<FoodInterface>> getRandomCombination() {
        return mealGenerator.getRandomCombination();
    }

    public void printAllCombinations() {
        mealGenerator.printAllCombinations();
    }
}
