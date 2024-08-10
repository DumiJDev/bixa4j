package io.github.bixa4j.manager;

import io.github.bixa4j.consumer.ConsumerTyped;
import io.github.bixa4j.consumer.ConsumerUnTyped;
import io.github.bixa4j.port.ProducerPort;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueManager {
    private static QueueManager instance;

    //Queues
    private final Map<String, Queue<Object>> queueUntyped = new HashMap<>();
    private final Map<Class<?>, Queue<Class<?>>> queueTyped = new HashMap<>();

    //Consumers
    private final Map<String, Set<ConsumerUnTyped>> consumerUnTyped = new HashMap<>();
    private final Map<Class<?>, Set<ConsumerTyped<?>>> consumerTyped = new HashMap<>();

    private QueueManager() {}

    public static QueueManager getManager() {
        if (instance == null) {
            instance = new QueueManager();
        }

        return instance;
    }

    public ProducerPort getPort(String queueName) {
        if (!queueUntyped.containsKey(queueName)) {
            queueUntyped.put(queueName, new ArrayBlockingQueue<>(5));
            consumerUnTyped.put(queueName, new LinkedHashSet<>());
        }

        return new ProducerPort();
    }

    public ProducerPort getPort(Class<?> clazz) {
        if (!queueTyped.containsKey(clazz)) {
            queueTyped.put(clazz, new ArrayBlockingQueue<>(5));
            consumerTyped.put(clazz, new LinkedHashSet<>());
        }

        return new ProducerPort();
    }

    public boolean addListener(String queueName, ConsumerUnTyped listener) {
        if (queueUntyped.containsKey(queueName)) {
            consumerUnTyped.get(queueName).add(listener);
            return true;
        }

        return false;
    }

    public <T> boolean addListener(Class<T> clazz, ConsumerTyped<T> listener) {
        if (queueTyped.containsKey(clazz)) {
            consumerTyped.get(clazz).add(listener);
            return true;
        }

        return false;
    }

}
