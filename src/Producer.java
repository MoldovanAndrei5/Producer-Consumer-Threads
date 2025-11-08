public class Producer extends Thread {
    private final double[] vector1, vector2;
    private final BoundedQueue queue;

    public Producer(double[] vector1, double[] vector2, BoundedQueue queue) {
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.queue = queue;
    }

    public void run() {
        try {
            for (int i = 0; i < vector1.length; i++) {
                double product = vector1[i] * vector2[i];
                queue.put(product);
            }
            queue.put(Double.NaN);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
