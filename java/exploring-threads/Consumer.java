import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final BlockingQueue<String> queue;
    private final Object lock;

    public Consumer(BlockingQueue<String> queue, Object lock) {
        this.queue = queue;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (lock) {
                    while (queue.isEmpty()) {
                        System.out.println("Queue is empty, consumer waiting...");
                        lock.wait();
                    }
                }

                // Simulate consumption
                Thread.sleep(500);
                String item = queue.take();
                System.out.println("Consumed: " + item + ". Queue size: " + queue.size());

                synchronized (lock) {
                    if (queue.isEmpty()) {
                        lock.notify(); // Notify the producer
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Consumer interrupted");
        }
    }
}

