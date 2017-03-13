package optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Person {

    Map<Long, Car> cars = new HashMap<>();

    public Optional<Car> getCar(Long id) {
        return Optional.ofNullable(cars.get(id));
    }
}
