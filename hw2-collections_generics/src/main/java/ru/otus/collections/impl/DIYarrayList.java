package ru.otus.collections.impl;

import ru.otus.collections.impl.arrayiterator.ArrayIteraror;

import java.util.*;

public class DIYarrayList<T> implements List<T>  {

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
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIteraror<T>(elements);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        try {
            T[] array = elements;
            elements = (T[]) new Object[array.length + 1];
            System.arraycopy(array, 0, elements, 0, array.length);
            elements[elements.length - 1] = t;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return  true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {
        try {
            T[] array = elements;
            elements = (T[]) new Object[array.length + 1];
            System.arraycopy(array, 0, elements, 0, array.length);
            elements[index] = element;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
