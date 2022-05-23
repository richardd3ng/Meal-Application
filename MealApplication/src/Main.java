import model.Food;
import model.FoodInterface;
import model.MealGenerator;

import java.util.Map;

import static model.examples.Dishes.*;
import static model.examples.Meals.*;

public class Main {
    public static void main(String[] args) {
        MealGenerator mealGenerator = new MealGenerator(BREAKFAST, LUNCH, DINNER, SNACK, 0, 0, Integer.MAX_VALUE);
    }
}
