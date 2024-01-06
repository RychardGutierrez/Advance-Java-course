import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class VariablesAtomic {
    static Map<String, Double> priceByAirline = new HashMap<>();
    String from = "BCN";
    String to = "JFK";

    Double lowestPrice = 0.0;
    Double avgPrice = 0.0;

    public static void run() {
        init();

//        Double result = getPriceTrip("American Airlines", "BCN", "JFK");
//        System.out.println(result);

        Double lowestPrice = getLowestPrice("BCN", "JFK");
        System.out.println(lowestPrice);

        Double avgPrice = getAVGPrice("asd", "asd");
        System.out.println(avgPrice);
    }

    public static void init() {
        priceByAirline.put("American Airlines", 550.0);
        priceByAirline.put("US Airlines", 450.0);
        priceByAirline.put("Watar Airlines", 650.0);
        priceByAirline.put("BOA Airlines", 1350.0);
    }

    public static Double getLowestPrice(String from, String to) {
        AtomicReference<Double> lowestPrice = new AtomicReference<>(null);
        priceByAirline.keySet().forEach(airline -> {
            Double price = getPriceTrip(airline, from, to);
            if (lowestPrice.get() == null || price < lowestPrice.get()) {
                lowestPrice.set(price);
            }
        });
        return lowestPrice.get();
    }

    public static Double getAVGPrice(String from, String to) {
        AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);
        AtomicInteger count = new AtomicInteger(0);
        priceByAirline.keySet().forEach(airline -> {
            Double price = getPriceTrip(airline, from, to);
            Double result = totalPrice.get() + price;
            totalPrice.set(result);
            count.incrementAndGet();
        });

        return totalPrice.get() / count.get();
    }

    public static Double getPriceTrip(String airline, String from, String to) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return priceByAirline.get(airline);
    }
}
