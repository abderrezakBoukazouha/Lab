import java.util.concurrent.atomic.AtomicInteger;

public class TaskExecutor implements Runnable {

    private final AtomicInteger increment;

    private final String name;

    public TaskExecutor(AtomicInteger increment, String name) {
        this.increment = increment;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (increment) { // Synchronize access to the shared counter
                if (increment.intValue() >= 500) {
                    break;
                }
                System.out.println(name + " - " + increment.intValue());
                increment.incrementAndGet();
            }
            try {
                Thread.sleep(20); // Simulate processing time
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}