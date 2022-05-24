package model.file_parsing;

import model.Food;
import model.FoodInterface;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static model.file_parsing.MealsBuilder.INGREDIENTS;

public class FoodBuilder {

    private static final String JSON_KEY_NAME = "Name";
    private static final String JSON_KEY_DESCRIPTION = "Serving Description";
    private static final String JSON_KEY_CALORIES = "Calories";
    private static final String JSON_KEY_TOTAL_FAT = "Total Fat";
    private static final String JSON_KEY_SATURATED_FAT = "Saturated Fat";
    private static final String JSON_KEY_PROTEIN = "Protein";
    private static final String JSON_KEY_INGREDIENTS = "Ingredients";


    public static FoodInterface buildFood(String jsonFilePath) {
        JSONObject foodJSON = JSONParser.pathToJSON(jsonFilePath);
        if (foodJSON.has(JSON_KEY_INGREDIENTS)) {
            return buildDish(foodJSON);
        }
        return buildIngredient(foodJSON);
    }

    private static FoodInterface buildIngredient(JSONObject ingredientJSON) {
        try {
            return new Food(
                    ingredientJSON.getString(JSON_KEY_NAME),
                    ingredientJSON.getString(JSON_KEY_DESCRIPTION),
                    ingredientJSON.getDouble(JSON_KEY_CALORIES),
                    ingredientJSON.getDouble(JSON_KEY_TOTAL_FAT),
                    ingredientJSON.getDouble(JSON_KEY_SATURATED_FAT),
                    ingredientJSON.getDouble(JSON_KEY_PROTEIN)
            );
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static FoodInterface buildDish(JSONObject dishJSON) {
        try {
            return new Food(
                    dishJSON.getString(JSON_KEY_NAME),
                    dishJSON.getString(JSON_KEY_DESCRIPTION),
                    ingredientsJSONtoMap(dishJSON.getJSONObject(JSON_KEY_INGREDIENTS))
            );
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map<FoodInterface, Double> ingredientsJSONtoMap(JSONObject ingredientsJSON) {
        Map<FoodInterface, Double> ingredientsMap = new HashMap<>();
        Iterator<String> keys = ingredientsJSON.keys();
        while (keys.hasNext()) {
            String ingredientName = keys.next();
            if (INGREDIENTS.containsKey(ingredientName)) {
                try {
                    ingredientsMap.put(INGREDIENTS.get(ingredientName), ingredientsJSON.getDouble(ingredientName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                printNoIngredientsError(ingredientName);
            }
        }
        return ingredientsMap;
    }

    private static void printNoIngredientsError(String missingIngredient) {
        System.out.println(String.format("Couldn't find ingredient: %s", missingIngredient));
    }
}
