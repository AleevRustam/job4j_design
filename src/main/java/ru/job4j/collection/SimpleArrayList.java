package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        grow(size + 1);
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        validateIndex(index);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T oldValue = container[index];
        System.arraycopy(container,
                index + 1,
                container,
                index,
                size - index - 1);
        container[--size] = null;
        modCount++;
        return oldValue;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T element = container[currentIndex++];
                expectedModCount = modCount;
                return element;
            }
        };
    }

    private void grow(int minCapacity) {
        if (minCapacity > container.length) {
            int newCapacity = container.length * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            container = Arrays.copyOf(container, newCapacity);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
