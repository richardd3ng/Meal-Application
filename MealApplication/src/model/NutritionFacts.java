package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class NutritionFacts {
    private FoodInterface food;
    private String name;
    private String servingDescription;

    private Map<String, Double> nutritionFactsMap;

    private static final ResourceBundle NUTRITION_FACT_GETTERS = ResourceBundle.getBundle("model.resources.NutritionFactGetters");
    private static final ResourceBundle NUTRITION_FACT_MAP_KEYS = ResourceBundle.getBundle("model.resources.NutritionFactMapKeys");
    private static final List<String> NUTRITION_PROPERTIES_KEYS = List.of("calories", "total_fat", "saturated_fat", "protein");

    public NutritionFacts(FoodInterface food) {
        this.food = food;
        this.name = food.getName();
        this.servingDescription = food.getDescription();
        this.nutritionFactsMap = generateNutritionFactsMap();
    }

    private Map<String, Double> generateNutritionFactsMap() {
        Map<String, Double> nutritionFactsMap = new LinkedHashMap<>();
        try {
            for (String propertiesKey : NUTRITION_PROPERTIES_KEYS) {
                Method getter =  Food.class.getDeclaredMethod(NUTRITION_FACT_GETTERS.getString(propertiesKey));
                nutritionFactsMap.put(NUTRITION_FACT_MAP_KEYS.getString(propertiesKey), (double) getter.invoke(food));
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return nutritionFactsMap;
    }

    public void printNutritionFacts() {
        System.out.println(String.format("%s\nServing Description: %s", name, servingDescription));
        for (String statName : nutritionFactsMap.keySet()) {
            System.out.println(String.format("%s: %s", statName, nutritionFactsMap.get(statName)));
        }
    }
}
