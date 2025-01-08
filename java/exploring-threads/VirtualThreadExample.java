import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreadExample {

    public static void main(String[] args) {
        AtomicInteger increment = new AtomicInteger(1);

        TaskExecutor firstTask = new TaskExecutor(increment, "first");
        TaskExecutor secondTask = new TaskExecutor(increment, "second");

        long start = System.currentTimeMillis();
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            executorService.submit(firstTask);
            executorService.submit(secondTask);
        }
        long end = System.currentTimeMillis();
        System.out.println("Elapsed Time in mili seconds: "+ (end-start));
    }
}
