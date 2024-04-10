package ru.job4j.set;

import java.util.Iterator;
import java.util.Objects;

import ru.job4j.collection.SimpleArrayList;

public class SimpleArraySet<T> implements SimpleSet<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean isAdd = !contains(value);
        if (isAdd) {
            set.add(value);
        }
        return isAdd;
    }

    @Override
    public boolean contains(T value) {
        boolean isContain = value == null && set == null;
        if (!isContain) {
            for (T element : set) {
                if (Objects.equals(value, element)) {
                    isContain = true;
                    break;
                }
            }
        }
        return isContain;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
