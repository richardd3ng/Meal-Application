import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Food;
import model.FoodInterface;
import model.JSONParser;
import model.MealGenerator;
import model.examples.Ingredients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import static model.examples.Dishes.*;
import static model.examples.Meals.*;

public class Main {
    private static final String path = "data/ingredients/egg.json";

    public static void main(String[] args) throws JSONException {
//        MealGenerator mealGenerator = new MealGenerator(BREAKFAST, LUNCH, DINNER, SNACK, 150, 2000, 2500);
//        mealGenerator.getRandomCombination();
        JSONObject eggJSON = JSONParser.loadFile(new File(path));
        FoodInterface egg = new Food(eggJSON.getString("Name"), eggJSON.getString("Serving Description"), eggJSON.getDouble("Calories"), eggJSON.getDouble("Total Fat"), eggJSON.getDouble("Saturated Fat"), eggJSON.getDouble("Protein"));
        egg.printNutritionFacts();
    }
}
