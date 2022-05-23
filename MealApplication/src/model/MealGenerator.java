package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MealGenerator {
    private static final int BREAKFAST_INDEX = 0;
    private static final int LUNCH_INDEX = 1;
    private static final int DINNER_INDEX = 2;
    private static final int SNACK_INDEX = 3;
    private static final String BREAKFAST_DESCRIPTION = "Breakfast";
    private static final String LUNCH_DESCRIPTION = "Lunch";
    private static final String DINNER_DESCRIPTION = "Dinner";
    private static final String SNACK_DESCRIPTION = "Snack";
    private static final String DAY_DESCRIPTION = "Daily Total";
    private static final String SERVING_DESCRIPTION = "Serves 1";

    private List<List<List<FoodInterface>>> combinations;

    public MealGenerator(List<FoodInterface> breakfastOptions, List<FoodInterface> lunchOptions, List<FoodInterface> dinnerOptions, List<FoodInterface> snackOptions, int minProtein, int minCalories, int maxCalories) {
        this.combinations = new ArrayList<>();
        generateCombinations(breakfastOptions, lunchOptions, dinnerOptions, snackOptions,
                minProtein, minCalories, maxCalories, 0, 0,
                0, 0, 0, 0, BREAKFAST_INDEX,
                new ArrayList<>(), new ArrayList<>());
        System.out.println("--------------------------------------------------------------------");
        System.out.println(combinations.size());
        for (List<List<FoodInterface>> day : combinations) {
            printDay(day);
        }
    }

    private void generateCombinations(List<FoodInterface> breakfastOptions, List<FoodInterface> lunchOptions, List<FoodInterface> dinnerOptions, List<FoodInterface> snackOptions,
                                      int minProtein, int minCalories, int maxCalories, int currProtein, int currCalories,
                                      int breakfastIdx, int lunchIdx, int dinnerIdx, int snackIdx, int mealIdx,
                                      List<FoodInterface> currMeal, List<List<FoodInterface>> currDay) {
        System.out.println(String.format("b: %d, l: %d, d: %d, s: %d, m: %d", breakfastIdx, lunchIdx, dinnerIdx, snackIdx, mealIdx));
        printDay(currDay);
        if (mealIdx > SNACK_INDEX + 1 || currDay.size() > 4) {
            return;
        }
        if (currProtein >= minProtein && currCalories >= minCalories && currCalories <= maxCalories && currDay.size() == 4) {
            System.out.println("ADDING COMBO");
            this.combinations.add(new ArrayList<>(currDay));
            return;
        }
        List<FoodInterface> options = new ArrayList<>();
        int startingIdx = 0;
        switch (mealIdx) {
            case BREAKFAST_INDEX -> {
                options = breakfastOptions;
                startingIdx = breakfastIdx;
            }
            case LUNCH_INDEX -> {
                options = lunchOptions;
                startingIdx = lunchIdx;
            }
            case DINNER_INDEX -> {
                options = dinnerOptions;
                startingIdx = dinnerIdx;
            }
            case SNACK_INDEX -> {
                options = snackOptions;
                startingIdx = snackIdx;
            }
        }
        if (startingIdx == options.size()) {
            currDay.add(new ArrayList<>(currMeal));
            generateCombinations(options, lunchOptions, dinnerOptions, snackOptions,
                    minProtein, minCalories, maxCalories, currProtein, currCalories,
                    breakfastIdx, lunchIdx, dinnerIdx, snackIdx, mealIdx + 1,
                    new ArrayList<>(), currDay);
        }
        for (int i = startingIdx; i < options.size(); i++) {
            // add current meal item
            FoodInterface foodItem = options.get(i);
            currMeal.add(foodItem);
            currProtein += foodItem.getProtein();
            currCalories += foodItem.getCalories();
            int nextBreakFastIdx = mealIdx == BREAKFAST_INDEX ? i + 1 : i;
            int nextLunchIdx = mealIdx == LUNCH_INDEX ? i + 1 : i;
            int nextDinnerIdx = mealIdx == DINNER_INDEX ? i + 1 : i;
            int nextSnackIdx = mealIdx == SNACK_INDEX ? i + 1 : i;
            generateCombinations(options, lunchOptions, dinnerOptions, snackOptions,
                    minProtein, minCalories, maxCalories, currProtein, currCalories,
                    nextBreakFastIdx, nextLunchIdx, nextDinnerIdx, nextSnackIdx, mealIdx,
                    currMeal, currDay);
            currCalories -= foodItem.getCalories();
            currProtein -= foodItem.getProtein();
            currMeal.remove(currMeal.size() - 1);
            if (currMeal.size() > 0) {
                // finish meal, move onto next meal
                currDay.add(new ArrayList<>(currMeal));
                generateCombinations(options, lunchOptions, dinnerOptions, snackOptions,
                        minProtein, minCalories, maxCalories, currProtein, currCalories,
                        breakfastIdx, lunchIdx, dinnerIdx, snackIdx, mealIdx + 1,
                        new ArrayList<>(), currDay);
            }
        }

    }

    private void printDay(List<List<FoodInterface>> currDay) {
        System.out.print("{");
        for (List<FoodInterface> meal : currDay) {
            printMeal(meal);
            System.out.print(", ");
        }
        System.out.print("}");
        System.out.println();
    }

    private void printMeal(List<FoodInterface> meal) {
        System.out.print("{");
        for (FoodInterface food : meal) {
            System.out.print(food.getName() + ", ");
        }
        System.out.print("}");
    }

    public List<List<FoodInterface>> getRandomCombination() {
        List<List<FoodInterface>> randCombo = combinations.get((int) (Math.random() * combinations.size()));
        printCombination(randCombo);
        return randCombo;
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
        dayTotal.printNutritionFacts();
    }

    private FoodInterface makeMeal(String description, List<FoodInterface> foodItems) {
        Map<FoodInterface, Double> foodMap = new LinkedHashMap<>();
        for (FoodInterface foodItem : foodItems) {
            foodMap.put(foodItem, 1.0);
        }
        return new Food(description, SERVING_DESCRIPTION, foodMap);
    }
}
