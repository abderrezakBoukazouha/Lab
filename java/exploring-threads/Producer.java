import java.util.concurrent.BlockingQueue;


public class Producer implements Runnable {

    private final BlockingQueue<String> queue;
    private final Object lock;

    public Producer(BlockingQueue<String> queue, Object lock) {
        this.queue = queue;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (lock) {
                    while (queue.size() >= 15) {
                        System.out.println("Queue is full, producer waiting...");
                        lock.wait();
                    }
                }

                // Simulate production
                Thread.sleep(300);
                queue.put("produced...");
                System.out.println("Produced item. Queue size: " + queue.size());

                synchronized (lock) {
                    if (queue.size() == 1) {
                        lock.notifyAll(); // Notify the consumer
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Producer interrupted");
        }
    }
}

