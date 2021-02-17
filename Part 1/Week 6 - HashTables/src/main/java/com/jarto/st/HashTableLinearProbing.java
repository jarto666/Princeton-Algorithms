package com.jarto.st;

import java.util.ArrayList;

public class HashTableLinearProbing<Key, Value> implements SymbolTable<Key, Value> {

    private int M;
    private ArrayList<Key> keys;
    private ArrayList<Value> values;
    private int size = 0;

    public HashTableLinearProbing() {
        M = 10;
        keys = new ArrayList<>(M);
        values = new ArrayList<>(M);

        for (int i = 0; i < M; i++) {
            keys.add(null);
            values.add(null);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Value get(Key key) {
        int idx = hash(key);
        for (int i = idx; keys.get(i) != null; i = (i + 1) % M) {
            if (key.equals(keys.get(i)))
                return values.get(i);
        }
        return null;
    }

    @Override
    public void put(Key key, Value value) {
        int idx = hash(key);
        for (; keys.get(idx) != null; idx = (idx + 1) % M)
            if (key.equals(keys.get(idx))) {
                keys.set(idx, key);
                values.set(idx, value);
                return;
            }

        keys.set(idx, key);
        values.set(idx, value);

        size++;
        if ((float)size/M > 0.5) {
            M*=2;
            rehash();
        }
    }

    @Override
    public boolean containsKey(Key key) {
        for (int idx = hash(key); keys.get(idx) != null; idx = (idx + 1) % M)
            if (key.equals(keys.get(idx))) {
                return true;
            }

        return false;
    }

    @Override
    public Value remove(Key key) {
        if (!containsKey(key))
            throw new IllegalArgumentException();

        int idx = hash(key);
        while (!key.equals(keys.get(idx)))
            idx = (idx + 1) % M;

        Value val = values.get(idx);
        keys.set(idx, null);
        values.set(idx, null);

        size--;
        if ((float)size/M < 0.5) {
            M /= 1.5;
        }

        rehash();

        return val;
    }

    private void rehash() {
        var tmpKeys = keys;
        var tmpVals = values;

        keys = new ArrayList<>(M);
        values = new ArrayList<>(M);

        for (int i = 0; i < M; i++) {
            keys.add(null);
            values.add(null);
        }

        for (int i = 0; i < tmpKeys.size(); i++) {
            var tKey = tmpKeys.get(i);
            if (tKey != null)
                put(tKey, tmpVals.get(i));
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }
}
