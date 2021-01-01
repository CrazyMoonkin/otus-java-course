package ru.crazymoonkin.otus.collection;

import java.util.*;

public class DIYarrayList<T> implements List<T> {
    DIYarrayList(int size) {
        if (size < 1) {
            throw new RuntimeException("Size can't be less than 1");
        }

        elements = (T[]) new Object[size];
    }

    DIYarrayList() {
        this(DEFAULT_CAPACITY);
    }


    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int currentSize = 0;

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Iterator<T> iterator() {
        return new DiyIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, currentSize);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean add(T t) {
        add(currentSize++, t);
        return true;
    }

    private void grow() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public T get(int index) {
        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        add(index, element);
        return element;
    }

    @Override
    public void add(int index, T element) {
        if (index >= elements.length) {
            throw new IndexOutOfBoundsException();
        }
        if (currentSize >= elements.length) {
            grow();
        }
        elements[index] = element;
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DiyIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private class DiyIterator implements ListIterator<T> {
        private int cursor = 0;
        private int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor < currentSize;
        }

        @Override
        public T next() {
            if (cursor >= currentSize) {
                throw new NoSuchElementException();
            }
            int i = cursor;
            cursor++;
            return elements[lastRet = i];
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public T previous() {
            return elements[lastRet];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(T t) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            DIYarrayList.this.set(lastRet, t);
        }

        @Override
        public void add(T t) {
            set(t);
        }
    }
}
