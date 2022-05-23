package model.file_parsing;

import model.Food;
import model.FoodInterface;
import model.examples.Ingredients;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class MealsBuilder {
    private static final String INGREDIENTS_DIRECTORY = "data/ingredients";
    private static final String JSON_EXTENSION = ".json";

    public static final Map<String, FoodInterface> INGREDIENTS = new HashMap<>(); // maps ingredient name to ingredient

    public static void buildMeals() {
        buildIngredients(INGREDIENTS_DIRECTORY);
        for (FoodInterface ingredient : INGREDIENTS.values()) {
            ingredient.printNutritionFacts();
        }
    }

    private static void buildIngredients(String filePath) {
        if (filePath.endsWith(JSON_EXTENSION)) {
            FoodInterface ingredient = IngredientBuilder.buildIngredient(filePath);
            INGREDIENTS.put(ingredient.getName(), ingredient);
            return;
        }
        for (String subDirectory : Objects.requireNonNull(new File(filePath).list())) {
            buildIngredients(String.format("%s/%s", filePath, subDirectory));
        }
    }
}
