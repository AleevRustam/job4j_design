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
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
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
        return isKeyMatched(key, index) ? table[index].value : null;
    }


    private V getForNullKey() {
        for (MapEntry<K, V> entry : table) {
            if (entry != null && entry.key == null) {
                return entry.value;
            }
        }
        return null;
    }

    private int keyHashCode(K key) {
        return key == null ? 0 : key.hashCode();
    }

    private boolean isKeyMatched(K key, int index) {
        MapEntry<K, V> entry = table[index];
        return entry != null
                && keyHashCode(key) == keyHashCode(entry.key)
                && Objects.equals(key, entry.key);

    }

    @Override
    public boolean remove(K key) {
        boolean isRemoved = false;
        int index = getIndex(key);
        if (isKeyMatched(key, index)) {
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
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                while (currentIndex < table.length && (table[currentIndex] == null)) {
                    currentIndex++;
                }
                return currentIndex < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[currentIndex++].key;
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
