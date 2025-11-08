public static void main(String[] args) throws InterruptedException {
    int vectorSize = 1000000;
    int queueSize = 100;
    double[] vector1 = new double[vectorSize];
    double[] vector2 = new double[vectorSize];

    Random rand = new Random();
    for (int i = 0; i < vectorSize; i++) {
        vector1[i] = rand.nextDouble();
        vector2[i] = rand.nextDouble();
    }

    BoundedQueue queue = new BoundedQueue(queueSize);
    Producer producer = new Producer(vector1, vector2, queue);
    Consumer consumer = new Consumer(queue);

    long startTime = System.nanoTime();
    producer.start();
    consumer.start();
    producer.join();
    consumer.join();
    long endTime = System.nanoTime();

    System.out.println("Dot product = " + consumer.getResult());
    System.out.println("Execution time = " + (double)(endTime - startTime) / 1000000000.0 + " s");
}
