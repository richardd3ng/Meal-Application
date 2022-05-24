import model.MealApplicationModel;

public class Main {
    private static final int MIN_CALORIES = 1000;
    private static final int MAX_CALORIES = 2500;
    private static final int MIN_PROTEIN = 150;

    public static void main(String[] args) {
        MealApplicationModel model = new MealApplicationModel(MIN_CALORIES, MAX_CALORIES, MIN_PROTEIN);
        model.getRandomCombination();
    }
}
