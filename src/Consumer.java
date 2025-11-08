public class Consumer extends Thread {
    private double result = 0.0;
    private final BoundedQueue queue;

    public Consumer(BoundedQueue queue) {
        this.queue = queue;
    }

    public double getResult() {
        return result;
    }

    public void run() {
        try {
            while (true) {
                double product = queue.take();
                if (Double.isNaN(product))
                    break;
                result += product;
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
