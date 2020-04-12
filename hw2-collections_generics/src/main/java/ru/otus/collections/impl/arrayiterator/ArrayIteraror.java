package ru.otus.collections.impl.arrayiterator;

import java.util.Iterator;

public class ArrayIteraror<E> implements Iterator<E> {
    private E[] elements;
    private int index = 0;

    public ArrayIteraror( E[] elements){
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
