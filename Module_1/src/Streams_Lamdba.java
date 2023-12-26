import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Streams_Lamdba {


    public static void streamsStudy() {
        List<String> cities = new ArrayList<>();

        cities.add("London");
        cities.add("New York");
        cities.add("Tokyo");
        cities.add("Barcelona");
        cities.add("Lima");

//        for (String city : cities) {
//            System.out.println(city);
//        }

        // Lamdba is as function
        cities.stream().forEach(city -> System.out.println(city));

        // Reference method
        cities.stream().forEach(Streams_Lamdba::printCity); // :: it is reference to method

        cities.forEach(System.out::println);

        // Use parallel
        // Pattern is Pipeline as cities.stream().parallel().newMethos().newMethod().etc
        System.out.println("parallel");
        cities.stream().parallel().forEach(city -> System.out.println(city));
        System.out.println("end parallel");

        // Use filter
        cities.stream().filter(city -> city.startsWith("B")).forEach(System.out::println);
        cities.stream().filter(Streams_Lamdba::filterCity).filter(city -> city.contains("n"
        )).forEach(System.out::println);
        // Terminal and not terminal method
        // The method terminal is the method that is final in the streams
        //      e.g: forEach
        // The method not terminal is the method that use en first position and not use en final position
        //      e.g: filter
        List<String> filterCities = cities.stream().filter(city -> city.contains("n")).collect(toList());
    }

    public static boolean filterCity(String city) {
        return city.startsWith("B");
    }

    public static void printCity(String city) {
        System.out.println(city);
    }
}
