package ru.otus.collections.impl.iterator;

import java.util.Iterator;

public class ArrayIterator<E> implements Iterator<E> {

    public E[] getElements() {
        return elements;
    }

    private E[] elements;
    private int index = 0;

    public ArrayIterator(E[] elements) {
        this.elements = elements;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean hasNext() {
        return elements.length > getIndex();
    }

    @Override
    public E next() {
        return elements[index++];
    }
}
