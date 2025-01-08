import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ThreadExample {

    public static void main(String[] args) {
        AtomicInteger increment = new AtomicInteger(1);

        TaskExecutor firstTask = new TaskExecutor(increment, "first");
        TaskExecutor secondTask = new TaskExecutor(increment, "second");



        long start = System.currentTimeMillis();
       try ( ExecutorService executorService = Executors.newFixedThreadPool(2)) {
           executorService.submit(firstTask);
           executorService.submit(secondTask);
        }
        long end = System.currentTimeMillis();
        System.out.println("Elapsed Time in mili seconds: "+ (end-start));


    }
}