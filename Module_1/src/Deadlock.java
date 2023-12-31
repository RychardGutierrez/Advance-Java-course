import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Deadlock {

    public void run() {
        // Create resources
        Fox robin = new Fox();
        Fox miki = new Fox();
        Food food = new Food();
        Water water = new Water();

        // Process data
        ExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(10);
            service.submit(() -> robin.eatAndDrink(food, water));
            service.submit(() -> miki.drinkAndEat(food, water));

        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    class Food {
    }

    class Water {
    }

    class Fox {
        public void eatAndDrink(Food food, Water water) {
            synchronized (food) {
                System.out.println("Robin: Got Food");
                move();
                synchronized (water) {
                    System.out.println("Robin: Got Water");
                }
            }
        }

        public void drinkAndEat(Food food, Water water) {
            synchronized (water) {
                System.out.println("Miki: Got deadlock.Water!");
                move();
                synchronized (food) {
                    System.out.println("Miki: Got deadlock.Food!");
                }
            }
        }

        public void move() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // Handle exception
                System.out.println(e.getMessage());
            }
        }
    }
}
