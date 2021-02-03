package com.jarto.symboltables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class OrderedST<Key extends Comparable, Value> implements SymbolTable<Key, Value> {

    private Key[] keys;
    private Value[] vals;
    private int count;

    public OrderedST() {
        keys = (Key[])new Comparable[4];
        vals = (Value[])new Comparable[4];
    }

    @Override
    public void put(Key key, Value value) {
        if (count == keys.length)
            resize(keys.length * 2);

        int i;
        for (i = 0; i < count; i++) {
            if (keys[i].compareTo(key) > 0) {
                for (int j = count; j > i; j--) {
                    keys[j] = keys[j - 1];
                    vals[j] = vals[j - 1];
                }
                break;
            }
        }
        count++;
        keys[i] = key;
        vals[i] = value;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()) return null;

        int i = rank(key);
        if (i < size() && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    @Override
    public void delete(Key key) {
        if (count < keys.length / 4)
            resize(keys.length / 2);

        if (!contains(key))
            throw new NoSuchElementException();

        int i = rank(key);
        for (int j = i + 1; j < count; j++) {
            keys[j - 1] = keys[j];
            vals[j - 1] = vals[j];
        }
        count--;
    }

    @Override
    public boolean contains(Key key) {
        return Arrays.stream(keys).anyMatch(k -> k == key);
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterable<Key> keys() {
        return Arrays.asList(Arrays.copyOf(keys, count));
    }

    private int rank(Key key) {
        int lo = 0;
        int hi = count - 1;

        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (key.compareTo(keys[mid]) < 0)
                hi = mid - 1;
            else if (key.compareTo(keys[mid]) > 0)
                lo = mid + 1;
            else
                return mid;
        }

        return lo;
    }

    private void resize(int newCapacity) {
        keys = Arrays.copyOf(keys, newCapacity);
        vals = Arrays.copyOf(vals, newCapacity);
    }
}
