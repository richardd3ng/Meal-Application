import model.MealApplicationModel;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int MIN_CALORIES = 0;
    private static final int MAX_CALORIES = 999999;
    private static final int MAX_TOTAL_FAT = 999999;
    private static final int MIN_PROTEIN = 0;

    public static void main(String[] args) {
        MealApplicationModel model = new MealApplicationModel(MIN_CALORIES, MAX_CALORIES, MAX_TOTAL_FAT, MIN_PROTEIN);
        model.printAllCombinations();
        model.getRandomCombination();
//        List<List<List<Integer>>> results = new ArrayList<>();
//        List<Integer> list1 = List.of(1, 2, 3, 4);
//        List<Integer> list2 = List.of(4, 3, 4, 5, 2, 34);
//        List<Integer> list3 = List.of(5, 3, 2, 2);
//        List<Integer> list4 = List.of(6, 34, 2, 3, 5, 3, 2, 1);
//        List<List<Integer>> sets = List.of(list1, list2, list3, list4);
//        subsets2D(sets, 0, new ArrayList<>(), results);
//        double expected = (Math.pow(2, list1.size()) - 1) * (Math.pow(2, list2.size()) - 1) * (Math.pow(2, list3.size()) - 1) * (Math.pow(2, list4.size()) - 1);
//        System.out.println("expected: " + expected);
//        System.out.println("actual: " + results.size());
    }

    private static void subsets2D(List<List<Integer>> sets, int setIdx, List<List<Integer>> curr, List<List<List<Integer>>> results) {
        if (curr.size() == 4) {
            results.add(new ArrayList<>(curr));
        }
        for (int i = setIdx; i < sets.size(); i++) {
            List<List<Integer>> setSubsets = new ArrayList<>();
            subsets(sets.get(i), 0, new ArrayList<>(), setSubsets);

            for (List<Integer> setSubset : setSubsets) {
                curr.add(new ArrayList<>(setSubset));
                subsets2D(sets, i + 1, curr, results);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private static void subsets(List<Integer> set, int idx, List<Integer> curr, List<List<Integer>> results) {
        if (!curr.isEmpty()) {
            results.add(new ArrayList<>(curr));
        }
        for (int i = idx; i < set.size(); i++) {
            curr.add(set.get(i));
            subsets(set, i + 1, curr, results);
            curr.remove(curr.size() - 1);
        }
    }


    private static void printDay(List<List<Integer>> currDay) {
        System.out.print("{");
        for (int i = 0; i < currDay.size(); i++) {
            printMeal(currDay.get(i));
            if (i != currDay.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("}");
        System.out.println();
    }

    private static void printMeal(List<Integer> meal) {
        System.out.print("{");
        for (int i = 0; i < meal.size(); i++) {
            System.out.print(meal.get(i));
            if (i != meal.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("}");
    }
}
