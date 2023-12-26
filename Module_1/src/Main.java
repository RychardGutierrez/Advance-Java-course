import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // String [] =
    public static void main(String[] args) throws InterruptedException {
//        Streams_Lamdba.streamsStudy();

//        WebScraper.server();

        Concurrence_Atomic concurrenceAtomic = new Concurrence_Atomic();
        Thread first = new Thread(concurrenceAtomic, "First");
        first.start();
        first.join(); // to join time to avoid random results

        Thread second = new Thread(concurrenceAtomic, "Second");
        second.start();
        second.join(); // to join time to avoid random results
        //Thread.sleep(5000);

//        System.out.println(concurrenceAtomic.count);
//        Concurrence_Atomic.run();
        Deadlock deadlock = new Deadlock();
        deadlock.run();
    }
}