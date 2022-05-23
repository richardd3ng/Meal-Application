package model.examples;

import model.FoodInterface;
import model.Food;

/**
 * sources include: USDA
 */
public class Ingredients {
    // DIARY
    public static final FoodInterface EGG = new Food("Egg", "1 Large (50 g)", 78, 5, 1.6, 6);
    public static final FoodInterface SWISS_CHEESE = new Food("Swiss Cheese", "1 Slice (21 g)", 50, 2.5, 1.5, 7);
    public static final FoodInterface EGG_WHITE = new Food("Egg Whites", "1 Carton (30 Tbsp)", 250, 0.0, 0, 50);
    public static final FoodInterface MILK = new Food("2% Milk", "1 cup", 140, 5, 3, 10);
    public static final FoodInterface GREEK_YOGURT = new Food("Chobani Plain Yogurt", "1 container", 80, 0, 0, 14);

    // FATS
    public static final FoodInterface OLIVE_OIL = new Food("Extra Virgin Olive Oil", "1 Tbsp", 120, 14, 2, 0);

    // FRUIT
    public static final FoodInterface BANANA = new Food("Banana", "1 Medium (118 g)", 105, 0.4, 0.1, 1.3);
    public static final FoodInterface TANGERINE = new Food("Tangerine", "1 Medium (88 g)", 47, 0.3, 0, 0.7);

    // VEGETABLES
    public static final FoodInterface SPRING_ONION = new Food("Spring Onion", "1 Medium (15g)", 5, 0, 0, 0.3);
    public static final FoodInterface TOMATO = new Food("Tomato", "1 Medium Whole (123 g)", 22, 0.2, 0, 1.1);
    public static final FoodInterface MUSHROOM = new Food("White Mushroom", "1 Medium (18 g)", 4, 0.1, 0, 0.6);
    public static final FoodInterface LETTUCE = new Food("Lettuce", "1 Cup Shredded (36 g)", 5, 0.1, 0, 0.5);

    // GRAINS
    public static final FoodInterface TOAST = new Food("Wheat Toast", "1 Slice", 120, 1.5, 0, 7);

    // MEAT
    public static final FoodInterface CHICKEN_BREAST = new Food("Chicken Breast", "1 Breast (6 oz)", 284, 6.2, 1.8, 54);
    public static final FoodInterface TURKEY_BREAST = new Food("Turkey Breast", "1 Slice (2 oz)", 60, 1, 0, 14);
}
