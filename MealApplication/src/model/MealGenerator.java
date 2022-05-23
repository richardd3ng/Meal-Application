package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MealGenerator {
    private static final boolean DEBUG = false;

    private static final int BREAKFAST_INDEX = 0;
    private static final int LUNCH_INDEX = 1;
    private static final int DINNER_INDEX = 2;
    private static final int SNACK_INDEX = 3;
    private static final String BREAKFAST_DESCRIPTION = "Breakfast";
    private static final String LUNCH_DESCRIPTION = "Lunch";
    private static final String DINNER_DESCRIPTION = "Dinner";
    private static final String SNACK_DESCRIPTION = "Snack";
    private static final String DAY_DESCRIPTION = "Daily Total";

    private List<List<List<FoodInterface>>> combinations;

    public MealGenerator(List<FoodInterface> breakfastOptions, List<FoodInterface> lunchOptions, List<FoodInterface> dinnerOptions, List<FoodInterface> snackOptions, int minProtein, int minCalories, int maxCalories) {
        this.combinations = new ArrayList<>();
        generateCombinations(breakfastOptions, lunchOptions, dinnerOptions, snackOptions,
                maxCalories, minCalories, minProtein, 0, 0,
                0, 0, 0, 0, BREAKFAST_INDEX,
                new ArrayList<>(), new ArrayList<>());
        if (DEBUG) {
            System.out.println(String.format("# combinations: %d", combinations.size()));
            for (List<List<FoodInterface>> day : combinations) {
                printDay(day);
            }
        }
    }

    private void generateCombinations(List<FoodInterface> breakfastOptions, List<FoodInterface> lunchOptions, List<FoodInterface> dinnerOptions, List<FoodInterface> snackOptions,
                                      int maxCalories, int minCalories, int minProtein, int currProtein, int currCalories,
                                      int breakfastIdx, int lunchIdx, int dinnerIdx, int snackIdx, int mealIdx,
                                      List<FoodInterface> currMeal, List<List<FoodInterface>> currDay) {
        if (mealIdx > SNACK_INDEX + 1 || currDay.size() > 4) {
            return;
        }
        if (currProtein >= minProtein && currCalories >= minCalories && currCalories <= maxCalories && currDay.size() == 4) {
            this.combinations.add(new ArrayList<>(currDay));
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
        for (int i = startingIdx; i < options.size(); i++) {
            FoodInterface foodItem = options.get(i);
            currMeal.add(foodItem);
            currProtein += foodItem.getProtein();
            currCalories += foodItem.getCalories();
            int nextBreakFastIdx = mealIdx == BREAKFAST_INDEX ? i + 1 : i;
            int nextLunchIdx = mealIdx == LUNCH_INDEX ? i + 1 : i;
            int nextDinnerIdx = mealIdx == DINNER_INDEX ? i + 1 : i;
            int nextSnackIdx = mealIdx == SNACK_INDEX ? i + 1 : i;
            generateCombinations(options, lunchOptions, dinnerOptions, snackOptions,
                    maxCalories, minCalories, minProtein, currProtein, currCalories,
                    nextBreakFastIdx, nextLunchIdx, nextDinnerIdx, nextSnackIdx, mealIdx,
                    currMeal, currDay);
            if (currMeal.size() > 0) {
                currDay.add(new ArrayList<>(currMeal));
                generateCombinations(options, lunchOptions, dinnerOptions, snackOptions,
                        maxCalories, minCalories, minProtein, currProtein, currCalories,
                        breakfastIdx, lunchIdx, dinnerIdx, snackIdx, mealIdx + 1,
                        new ArrayList<>(), currDay);
                currDay.remove(currDay.size() - 1);
            }
            currCalories -= foodItem.getCalories();
            currProtein -= foodItem.getProtein();
            currMeal.remove(currMeal.size() - 1);
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
        System.out.println();
        lunch.printNutritionFacts();
        System.out.println();
        dinner.printNutritionFacts();
        System.out.println();
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
