package io.github.bixa4j.consumer;

@FunctionalInterface
public interface ConsumerTyped<T> {
    void notify(T t);
}
