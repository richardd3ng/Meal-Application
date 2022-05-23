import model.Food;
import model.FoodInterface;
import model.file_parsing.IngredientBuilder;
import model.file_parsing.JSONParser;
import model.file_parsing.MealsBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class Main {

    private static final String path = "data/ingredients/dairy/egg.json/";


    public static void main(String[] args) throws JSONException {
//        MealGenerator mealGenerator = new MealGenerator(BREAKFAST, LUNCH, DINNER, SNACK, 150, 2000, 2500);
//        mealGenerator.getRandomCombination();
//        IngredientBuilder.buildIngredient(path).printNutritionFacts();
        MealsBuilder.buildMeals();
    }
}
