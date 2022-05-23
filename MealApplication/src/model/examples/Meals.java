package model.examples;

import model.Food;
import model.FoodInterface;

import java.util.List;

import static model.examples.Ingredients.*;
import static model.examples.Dishes.*;

public class Meals {

    public static final List<FoodInterface> BREAKFAST = List.of(
            OMELETTE,
            TOAST
    );
    
    public static final List<FoodInterface> LUNCH = List.of(
            TURKEY_SANDWICH
            //MILK
    );

    public static final List<FoodInterface> DINNER = List.of(
            GRILLED_CHICKEN
            //GREEK_YOGURT
    );
    
    public static final List<FoodInterface> SNACK = List.of(
            //GREEK_YOGURT,
            BANANA
            //TANGERINE
            //MILK
    );
}

