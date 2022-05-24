import model.MealApplicationModel;

public class Main {
    private static final int MIN_CALORIES = 2000;
    private static final int MAX_CALORIES = 2250;
    private static final int MAX_TOTAL_FAT = 65;
    private static final int MIN_PROTEIN = 180;

    public static void main(String[] args) {
        MealApplicationModel model = new MealApplicationModel(MIN_CALORIES, MAX_CALORIES, MAX_TOTAL_FAT, MIN_PROTEIN);
        model.printAllCombinations();
        model.getRandomCombination();
    }
}
