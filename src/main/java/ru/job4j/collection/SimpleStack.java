package ru.job4j.collection;


public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();
    private T value;
    private SimpleStack<T> head;

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }


}