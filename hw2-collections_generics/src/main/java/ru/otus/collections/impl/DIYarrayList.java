package ru.otus.collections.impl;

import ru.otus.collections.impl.iterator.ArrayIteraror;
import ru.otus.collections.impl.iterator.ListItrImpl;

import java.util.*;
import java.util.stream.IntStream;

public class DIYarrayList<T> implements List<T> {

    private T[] elements;

    public DIYarrayList() {
        this.elements = (T[]) new Object[0];
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIteraror<T>(elements);
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
        try {
            T[] array = elements;
            elements = (T[]) new Object[size() + 1];
            System.arraycopy(array, 0, elements, 0, array.length);
            elements[elements.length - 1] = t;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
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
        IntStream.range(0, elements.length).forEach(i -> elements[i] = null);
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
        try {
            T[] array = elements;
            elements = (T[]) new Object[array.length + 1];
            System.arraycopy(array, 0, elements, 0, index);
            System.arraycopy(array, index, elements, index + 1, size() - index - 1);
            elements[index] = element;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public T remove(int index) {
        T oldValue = elements[index];
        try {
            T[] array = elements;
            elements = (T[]) new Object[array.length - 1];
            System.arraycopy(array, 0, elements, 0, index);
            System.arraycopy(array, index + 1, elements, index, size() - index);
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = elements.length - 1; i >= 0; i--)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = 0; i < elements.length; i++)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size() - 1; i >= 0; i--)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = size() - 1; i >= 0; i--)
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
}
