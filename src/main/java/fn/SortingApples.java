package fn;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

public class SortingApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        System.out.println(sortApplesOld(inventory));
        System.out.println(sortApplesFirst(inventory));
        System.out.println(sortApplesSecond(inventory));
    }

    private static List<Apple> sortApplesOld(List<Apple> inventory) {
        Collections.sort(inventory, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        return inventory;
    }

    private static List<Apple> sortApplesFirst(List<Apple> inventory) {
        inventory.sort((a1, a2) -> a1.getWeight() - a2.getWeight());
        return inventory;
    }

    private static List<Apple> sortApplesSecond(List<Apple> inventory) {
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        return inventory;
    }
}
