import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Starvation {

    public void run() {

        Fox2 robin = new Fox2();
        Fox2 miki = new Fox2();
        Food2 food = new Food2();
        Elephant dumbo = new Elephant();


        // Process data
        ExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(10);
            service.submit(() -> dumbo.eat(food));
            service.submit(() -> robin.eat(food));
            service.submit(() -> miki.eat(food));


        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}

class Food2 {
}

class Fox2 {
    public void eat(Food2 food) {
        move();
        synchronized (food) {
            System.out.println("Robin: Got Food");
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

class Elephant {
    public void eat(Food2 food) {
        synchronized (food) {
            System.out.println("Elephant: Got Food");
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}