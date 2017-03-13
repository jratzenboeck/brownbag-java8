package fn;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionObjects {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        Apple apple = inventory.get(0);
        System.out.println(function(apple, a -> a.getWeight()));
        System.out.println(predicate(apple, a -> "green".equals(a.getColor())) ? "Apple is green" : "Apple is not green");
        consumer(inventory);
    }

    private static Integer function(Apple apple, Function<Apple, Integer> fn) {
        return fn.apply(apple);
    }

    private static boolean predicate(Apple apple, Predicate<Apple> predicate) {
        return predicate.test(apple);
    }

    private static void consumer(List<Apple> apples) {
        apples.forEach(apple -> System.out.println(apple));
    }
}
