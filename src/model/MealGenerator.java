package model;

import model.food.Food;
import model.food.FoodInterface;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MealGenerator {

    private class FoodPlan {
        List<FoodInterface> foodItems;
        double calories;
        double totalFat;
        double protein;

        public FoodPlan(List<FoodInterface> foodItems, double calories, double totalFat, double protein) {
            this.foodItems = foodItems;
            this.calories = calories;
            this.totalFat = totalFat;
            this.protein = protein;
        }

    }

    private static final int BREAKFAST_INDEX = 0;
    private static final int LUNCH_INDEX = 1;
    private static final int DINNER_INDEX = 2;
    private static final int SNACK_INDEX = 3;
    private static final String BREAKFAST_DESCRIPTION = "Breakfast";
    private static final String LUNCH_DESCRIPTION = "Lunch";
    private static final String DINNER_DESCRIPTION = "Dinner";
    private static final String SNACK_DESCRIPTION = "Snack";
    private static final String DAY_DESCRIPTION = "Daily Total";
    private static final String NO_COMBINATIONS_MESSAGE = "No combinations satisfy the given criteria";

    private List<List<List<FoodInterface>>> combinations;

    public MealGenerator(List<List<FoodInterface>> options, int minCalories, int maxCalories, int maxTotalFat, int minProtein) {
        this.combinations = new ArrayList<>();
        generateCombinations(options, minCalories, maxCalories, maxTotalFat, minProtein, 0, 0, 0, 0,
                new ArrayList<>());
    }

    private void generateCombinations(List<List<FoodInterface>> mealOptions, int minCalories, int maxCalories, int maxTotalFat, int minProtein,
                                      double currCalories, double currTotalFat, double currProtein, int mealIdx, List<List<FoodInterface>> currMealPlan) {
        if (currCalories > maxCalories || currTotalFat > maxTotalFat) {
            return;
        }
        if (currMealPlan.size() == 4 && currCalories >= minCalories && currCalories <= maxCalories && currTotalFat <= maxTotalFat && currProtein >= minProtein) {
            combinations.add(new ArrayList<>(currMealPlan));
        }
        for (int i = mealIdx; i < mealOptions.size(); i++) {
            List<FoodPlan> foodPlans = new ArrayList<>();
            generateSubsetsForOneMeal(mealOptions.get(i), minCalories, maxCalories, maxTotalFat, minProtein,
                    currCalories, currTotalFat, currProtein, 0, new ArrayList<>(), foodPlans);

            for (FoodPlan foodPlan : foodPlans) {
                currMealPlan.add(new ArrayList<>(foodPlan.foodItems));
                currCalories += foodPlan.calories;
                currTotalFat += foodPlan.totalFat;
                currProtein += foodPlan.protein;
                generateCombinations(mealOptions, minCalories, maxCalories, maxTotalFat, minProtein,
                        currCalories, currTotalFat, currProtein, i + 1, currMealPlan);
                currProtein -= foodPlan.protein;
                currTotalFat -= foodPlan.totalFat;
                currCalories -= foodPlan.calories;
                currMealPlan.remove(currMealPlan.size() - 1);
            }
        }
    }

    private void generateSubsetsForOneMeal(List<FoodInterface> foodOptions, int minCalories, int maxCalories, int maxTotalFat, int minProtein,
                                           double currCalories, double currTotalFat, double currProtein, int foodIdx, List<FoodInterface> currFoodItems, List<FoodPlan> foodPlans) {
        if (currCalories > maxCalories || currTotalFat > maxTotalFat) {
            return;
        }
        if (!currFoodItems.isEmpty() && currCalories >= minCalories && currCalories <= maxCalories && currTotalFat <= maxTotalFat && currProtein >= minProtein) {
            foodPlans.add(new FoodPlan(new ArrayList<>(currFoodItems), currCalories, currTotalFat, currProtein));
        }
        for (int i = foodIdx; i < foodOptions.size(); i++) {
            currFoodItems.add(foodOptions.get(i));
            currCalories += foodOptions.get(i).getCalories();
            currTotalFat += foodOptions.get(i).getTotalFat();
            currProtein += foodOptions.get(i).getProtein();
            generateSubsetsForOneMeal(foodOptions, minCalories, maxCalories, maxTotalFat, minProtein,
                    currCalories, currTotalFat, currProtein, i + 1, currFoodItems, foodPlans);
            currProtein -= foodOptions.get(i).getProtein();
            currTotalFat -= foodOptions.get(i).getTotalFat();
            currCalories -= foodOptions.get(i).getCalories();
            currFoodItems.remove(currFoodItems.size() - 1);
        }
    }

    public List<List<FoodInterface>> getRandomCombination() {
        if (combinations.isEmpty()) {
            System.out.println(NO_COMBINATIONS_MESSAGE);
            return new ArrayList<>();
        }
        List<List<FoodInterface>> randCombo = combinations.get((int) (Math.random() * combinations.size()));
        printCombination(randCombo);
        return randCombo;
    }

    public void printAllCombinations() {
        System.out.println(String.format("# combinations: %d", combinations.size()));
        for (List<List<FoodInterface>> day : combinations) {
            printDay(day);
        }
    }

    private void printDay(List<List<FoodInterface>> currDay) {
        System.out.print("{");
        for (int i = 0; i < currDay.size(); i++) {
            printMeal(currDay.get(i));
            if (i != currDay.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("}");
        System.out.println();
    }

    private void printMeal(List<FoodInterface> meal) {
        System.out.print("{");
        for (int i = 0; i < meal.size(); i++) {
            System.out.print(meal.get(i).getName());
            if (i != meal.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("}");
    }

    private void printCombination(List<List<FoodInterface>> combination) {
        FoodInterface breakfast = makeMeal(BREAKFAST_DESCRIPTION, combination.get(BREAKFAST_INDEX));
        FoodInterface lunch = makeMeal(LUNCH_DESCRIPTION, combination.get(LUNCH_INDEX));
        FoodInterface dinner = makeMeal(DINNER_DESCRIPTION, combination.get(DINNER_INDEX));
        FoodInterface snack = makeMeal(SNACK_DESCRIPTION, combination.get(SNACK_INDEX));
        FoodInterface dayTotal = makeMeal(DAY_DESCRIPTION, List.of(breakfast, lunch, dinner, snack));
        breakfast.printNutritionFacts();
        lunch.printNutritionFacts();
        dinner.printNutritionFacts();
        snack.printNutritionFacts();
        dayTotal.printNutritionFacts();
    }

    private FoodInterface makeMeal(String meal, List<FoodInterface> foodItems) {
        Map<FoodInterface, Double> foodMap = new LinkedHashMap<>();
        StringBuilder mealDescription = new StringBuilder();
        for (int i = 0; i < foodItems.size(); i++) {
            FoodInterface foodItem = foodItems.get(i);
            foodMap.put(foodItem, 1.0);
            mealDescription.append(foodItem.getName());
            if (i != foodItems.size() - 1) {
                mealDescription.append(", ");
            }
        }
        return new Food(meal, mealDescription.toString(), foodMap);
    }
}
