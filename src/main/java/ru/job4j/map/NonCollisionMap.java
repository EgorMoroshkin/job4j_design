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

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & capacity - 1;
    }

    private int indexForKey(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean equalsKeys(K key) {
        MapEntry<K, V> tempIndexTable = table[indexForKey(key)];
        return Objects.hashCode(tempIndexTable.key) == Objects.hashCode(key)
                && Objects.equals(tempIndexTable.key, key);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> element : table) {
            if (element != null) {
                newTable[indexForKey(element.key)] = element;
            }
        }
        table = newTable;
    }

    @Override
    public boolean put(K key, V value) {
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        int tempIndex = indexForKey(key);
        boolean rsl = table[tempIndex] == null;
        if (rsl) {
            table[tempIndex] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    @Override
    public V get(K key) {
        int tempIndex = indexForKey(key);
        return table[tempIndex] != null && equalsKeys(key)
                ? table[tempIndex].value : null;
    }

    @Override
    public boolean remove(K key) {
        int tempIndex = indexForKey(key);
        boolean rsl = table[tempIndex] != null && equalsKeys(key);
        if (rsl) {
            table[tempIndex] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int index = 0;
            final int expectedMod = modCount;

            @Override
            public boolean hasNext() {
                if (expectedMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}