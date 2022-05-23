import model.Food;
import model.FoodInterface;
import model.file_parsing.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class Main {

    private static final String path = "data/ingredients/dairy/egg.json";


    public static void main(String[] args) throws JSONException {
//        MealGenerator mealGenerator = new MealGenerator(BREAKFAST, LUNCH, DINNER, SNACK, 150, 2000, 2500);
//        mealGenerator.getRandomCombination();
//        JSONObject eggJSON = JSONParser.loadFile(new File(path));
//        FoodInterface egg = new Food(eggJSON.getString("Name"), eggJSON.getString("Serving Description"), eggJSON.getDouble("Calories"), eggJSON.getDouble("Total Fat"), eggJSON.getDouble("Saturated Fat"), eggJSON.getDouble("Protein"));
//        egg.printNutritionFacts();
        File f = new File("data/ingredients/");
        for (String file : f.list()) {
            System.out.println(file);
        }
    }
}
