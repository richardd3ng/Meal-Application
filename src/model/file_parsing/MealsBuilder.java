package model.file_parsing;

import model.Food;
import model.FoodInterface;
import model.examples.Ingredients;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MealsBuilder {
    private static final String INGREDIENTS_DIRECTORY = "data/ingredients/";
    private static final String DAIRY_DIRECTORY = "dairy";

    private static final Set<String> INGREDIENTS_DIRECTORIES = Set.of(DAIRY_DIRECTORY);


    public final Map<String, FoodInterface> INGREDIENTS = new HashMap<>();

//    public static buildMeals() {
//
//    }
    private void buildIngredients() {

    }
}
