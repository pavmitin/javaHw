package ru.otus.collections.impl;

import ru.otus.collections.impl.iterator.ArrayIterator;
import ru.otus.collections.impl.iterator.ListItrImpl;

import java.util.*;
import java.util.stream.IntStream;

public class DIYarrayList<T> implements List<T> {

    private T[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private int size;

    public DIYarrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.elements = (T[]) new Object[capacity];
    }
    public DIYarrayList(int capacity) {
        this.capacity = capacity;
        this.elements = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(elements);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size());
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if (size >= capacity) {
            growArray();
        }
        elements[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        IntStream.range(0, size).forEach(i -> elements[i] = null);
        size = 0;
    }

    @Override
    public T get(int index) {
        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        T oldValue = elements[index];
        elements[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0) {
            return;
        }
        if (size + 1 >= capacity) {
            growArray();
        }
        if (index > size) {
            index = size;
        }
        System.arraycopy(elements, index, elements, index + 1,
                size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public T remove(int index) {
        T oldValue = elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elements, index + 1, elements, index,
                    numMoved);
        elements[--size] = null;

        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItrImpl<>(elements);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < toArray().length; i++) {
            if (toArray()[i] == null) {
                sb.append("[" + "null" + "]");
                continue;
            }
            sb.append("[" + toArray()[i].toString() + "]");
        }
        sb.append("}");
        return sb.toString();
    }

    private void growArray() {
        capacity = capacity * 2;
        elements = Arrays.copyOf(elements, capacity);
    }
}
