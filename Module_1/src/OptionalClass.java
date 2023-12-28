import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalClass {

    public static void listOptionalExample() {
        List<String> countries = new ArrayList<>();
        countries.add("India");
        countries.add("USA");
        countries.add("Mexico");

        Optional<String> country = countries.stream()
                .filter(c -> c.contains("test")).findFirst();
        country.ifPresent(System.out::println);
    }

    public static Optional<Double> averageScore(Double... scores) {
        if (scores.length == 0) {
            return Optional.empty();
        }

        double sum = 0;
        for (Double score : scores) {
            sum += score;
        }
        return Optional.of(sum / scores.length);
    }
}
