Computing the dot product of 2 vectors using 2 vectors: a producer and a consumer, with the producer feeding the consumer.

The app uses a configurable-sized queue, which holds the elements between the producer and the consumer. The consumer is cleared to use each product as soon as it is computed by the producer thread, and the producer is put to wait if the queue reaches the maximum allowed size.

The threads are synchronized with 2 conditional variables ("notFull" and "notEmpty") and a mutex.
