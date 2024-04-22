package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean isPut = false;
        if (table[getIndex(key)] == null) {
            table[getIndex(key)] = new MapEntry<>(key, value);
            count++;
            modCount++;
            isPut = true;
        }
        return isPut;
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                newTable[getIndex(entry.key)] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        return isaBoolean(key, index) ? table[index].value : null;
    }

    private boolean isaBoolean(K key, int index) {
        return table[index] != null && Objects.equals(table[index].key, key);
    }

    @Override
    public boolean remove(K key) {
        boolean isRemoved = false;
        int index = getIndex(key);
        if (isaBoolean(key, index)) {
            table[index] = null;
            count--;
            modCount++;
            isRemoved = true;
        }
        return isRemoved;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int currentIndex;
            private int nextNullIndex = findNextNullIndex(0);
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                while (currentIndex < table.length
                        && (table[currentIndex] == null || table[currentIndex].key == null)) {
                    currentIndex++;
                }

                return currentIndex < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                if (nextNullIndex != -1) {
                    currentIndex = nextNullIndex;
                    nextNullIndex = findNextNullIndex(nextNullIndex + 1);
                    return null;
                }

                while (currentIndex < table.length
                        && (table[currentIndex] == null || table[currentIndex].key == null)) {
                    currentIndex++;
                }

                if (currentIndex < table.length) {
                    return table[currentIndex++].key;
                }

                throw new NoSuchElementException();
            }

            private int findNextNullIndex(int start) {
                for (int i = start; i < table.length; i++) {
                    if (table[i] != null && table[i].key == null) {
                        return i;
                    }
                }
                return -1;
            }
        };
    }

    public static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
