import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Concurrence_Atomic extends Thread {

    public AtomicInteger count = new AtomicInteger(0);

    public static void runConcurrence() throws InterruptedException {
        System.out.println("Start");
        Thread.sleep(2000);
        System.out.println("Stop");
    }

    public void run() {

        for (int i = 0; i < 100_000_000; i++) {
            count.addAndGet(1);
        }

//        System.out.println(count);
    }


}
