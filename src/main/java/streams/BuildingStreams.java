package streams;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {

    public static void main(String[] args) {
        // Stream.of
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // Stream.empty
        Stream<String> emptyStream = Stream.empty();

        // Arrays.stream
        int[] numbers = { 2, 3, 5, 7, 11, 13 };
        System.out.println(Arrays.stream(numbers).sum());

        // Stream.iterate
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        // random stream of doubles with Stream.generate
        Stream.generate(Math::random).limit(10).forEach(System.out::println);

        // stream of 1s with Stream.generate
        IntStream.generate(() -> 1).limit(5).forEach(System.out::println);
    }
}
