package ru.otus.collections.impl.iterator;

import java.util.ListIterator;

public class ListItrImpl<E> extends ArrayIteraror<E> implements ListIterator<E> {

    public ListItrImpl(E[] elements) {
        super(elements);
    }

    @Override
    public boolean hasPrevious() {
        return getIndex() != 0;
    }

    @Override
    public E previous() {
        return super.getElements()[getIndex() - 1];
    }

    @Override
    public int nextIndex() {
        return getIndex();
    }

    @Override
    public int previousIndex() {
        return getIndex() - 1;
    }

    @Override
    public void remove() {
        new UnsupportedOperationException();
    }

    @Override
    public void set(E e) {
        super.getElements()[getIndex() - 1] = e;
    }

    @Override
    public void add(E e) {
        new UnsupportedOperationException();
    }
}
