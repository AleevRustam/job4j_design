package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int outputCount;
    private int inputCount;

    public T poll() {
        if (outputCount == 0 && inputCount == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outputCount == 0) {
            while (inputCount != 0) {
                output.push(input.pop());
                inputCount--;
                outputCount++;
            }
        }

        if (outputCount == 0 && inputCount == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        T value = output.pop();
        outputCount--;
        return value;
    }

    public void push(T value) {
        input.push(value);
        inputCount++;
    }
}
