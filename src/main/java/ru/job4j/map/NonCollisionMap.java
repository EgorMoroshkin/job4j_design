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
        return hashCode == 0 ? 0 : hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & table.length - 1;
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        int index = 0;
        int count = 0;
        while (count < table.length) {
            if (table[index] != null) {
                newTable[hash(Objects.hashCode(table[index].key) & newTable.length - 1)]
                        = new MapEntry<>(table[index].key, table[index].value);
            }
            index++;
            count++;
        }
        table = newTable;
    }

    @Override
    public boolean put(K key, V value) {
        float load = (float) count / table.length;
        if (load >= LOAD_FACTOR) {
            expand();
        }
        int hash = hash(Objects.hashCode(key));
        int b = indexFor(hash);
        boolean rsl = false;
        if (table[b] == null) {
            table[b] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        return table[index] != null && Objects.equals(Objects.hashCode(table[index].key),
                Objects.hashCode(key)) && Objects.equals(table[index].key, key) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] != null && Objects.equals(Objects.hashCode(table[index].key),
                Objects.hashCode(key)) && Objects.equals(table[index].key, key)) {
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
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