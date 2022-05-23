package model.file_parsing;

import model.Food;
import model.FoodInterface;
import org.json.JSONException;
import org.json.JSONObject;

public class IngredientBuilder {
    
    public static FoodInterface buildIngredient(String jsonFilePath) {
        JSONObject ingredientJSON = JSONParser.pathToJSON(jsonFilePath);
        try {
            return new Food(ingredientJSON.getString("Name"), ingredientJSON.getString("Serving Description"), ingredientJSON.getDouble("Calories"), ingredientJSON.getDouble("Total Fat"), ingredientJSON.getDouble("Saturated Fat"), ingredientJSON.getDouble("Protein"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
