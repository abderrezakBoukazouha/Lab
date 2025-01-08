import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Factory {

    public static void main(String[] args) {
        final BlockingQueue<String> queue = new LinkedBlockingQueue<>(25);
        final Object lock = new Object();

        Producer producer = new Producer(queue, lock);
        Consumer consumer = new Consumer(queue, lock);

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            executorService.submit(producer);
            executorService.submit(consumer);
        }
    }
}

