package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        ForwardLinked.Node<T> last = head;
        ForwardLinked.Node<T> newNode = new ForwardLinked.Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        ForwardLinked.Node<T> last = head;
        ForwardLinked.Node<T> newNode = new ForwardLinked.Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            head = newNode;
            head.next = last;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        ForwardLinked.Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T element = head.item;
        Node<T> next = head.next;
        head.next = null;
        head.item = null;
        head = next;
        size--;
        modCount++;
        return element;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private ForwardLinked.Node<T> node = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                ForwardLinked.Node<T> rsl = node;
                node = node.next;
                return rsl.item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private ForwardLinked.Node<T> next;

        Node(T element, ForwardLinked.Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}