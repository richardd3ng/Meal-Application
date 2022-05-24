package model.file_parsing;

import model.food.FoodInterface;

import java.io.File;
import java.util.*;

public class MealsBuilder {
    private static final String INGREDIENTS_DIRECTORY = "data/ingredients";
    private static final String BREAKFAST_DIRECTORY = "data/dishes/breakfast";
    private static final String LUNCH_DIRECTORY = "data/dishes/lunch";
    private static final String DINNER_DIRECTORY = "data/dishes/dinner";
    private static final String SNACK_DIRECTORY = "data/dishes/snack";
    private static final String JSON_EXTENSION = ".json";

    public static final Map<String, FoodInterface> INGREDIENTS = new HashMap<>(); // maps ingredient name to ingredients, note that dishes are also used as ingredients

    public static List<FoodInterface> BREAKFAST_OPTIONS = new ArrayList<>();
    public static final List<FoodInterface> LUNCH_OPTIONS = new ArrayList<>();
    public static final List<FoodInterface> DINNER_OPTIONS = new ArrayList<>();
    public static final List<FoodInterface> SNACK_OPTIONS = new ArrayList<>();

    public static void buildMeals() {
        buildIngredients(INGREDIENTS_DIRECTORY);
        collectOptions(BREAKFAST_OPTIONS, BREAKFAST_DIRECTORY);
        collectOptions(LUNCH_OPTIONS, LUNCH_DIRECTORY);
        collectOptions(DINNER_OPTIONS, DINNER_DIRECTORY);
        collectOptions(SNACK_OPTIONS, SNACK_DIRECTORY);
    }

    /**
     * Builds all ingredients/food items in the given folder
     *
     * @param path the directory or file path
     */
    private static void buildIngredients(String path) {
        if (path.endsWith(JSON_EXTENSION)) {
            FoodInterface ingredient = FoodBuilder.buildFood(path);
            INGREDIENTS.put(ingredient.getName(), ingredient);
            return;
        }
        for (String subDirectory : Objects.requireNonNull(new File(path).list())) {
            buildIngredients(String.format("%s/%s", path, subDirectory));
        }
    }

    /**
     * Collects all dish options in a given folder
     *
     * @param options the list containing the FoodInterfaces representing dishes
     * @param path    the directory or file path
     */
    private static void collectOptions(List<FoodInterface> options, String path) {
        if (path.endsWith(JSON_EXTENSION)) {
            options.add(FoodBuilder.buildFood(path));
            return;
        }
        for (String subDirectory : Objects.requireNonNull(new File(path).list())) {
            collectOptions(options, String.format("%s/%s", path, subDirectory));
        }
    }
}
