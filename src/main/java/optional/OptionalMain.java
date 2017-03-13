package optional;

public class OptionalMain {

    public static void main(String[] args) {
        Person person = new Person();
        person.cars.put(1L, new Car("Toyota"));
        person.getCar(1L).ifPresent(System.out::println);
    }
}
