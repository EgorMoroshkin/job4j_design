package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    int index = 0;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (index == data.size() && !data.isEmpty()) {
            index = 0;
        }
        return index < data.size();
    }

    @Override
    public T next() {
        while (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data.get(index++);
    }
}