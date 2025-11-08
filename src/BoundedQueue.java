import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue {
    private final Queue<Double> queue = new LinkedList<Double>();
    private final int queueSize;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public BoundedQueue(int queueSize) {
        this.queueSize = queueSize;
    }

    public void put(double value) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == queueSize) {
                notFull.await();
            }
            queue.add(value);
            notEmpty.signal();
        }
        finally {
            lock.unlock();
        }
    }

    public double take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            double value = queue.remove();
            notFull.signal();
            return value;
        }
        finally {
            lock.unlock();
        }
    }
}
