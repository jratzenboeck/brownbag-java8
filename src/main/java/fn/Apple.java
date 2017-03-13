package fn;

class Apple {

    private final int weight;
    private final String color;

    Apple(int weight, String color){
        this.weight = weight;
        this.color = color;
    }

    Integer getWeight() {
        return weight;
    }

    String getColor() {
        return color;
    }

    public String toString() {
        return "fn.Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
