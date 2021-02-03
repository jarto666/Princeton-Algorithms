package com.jarto.symboltables;

public interface SymbolTable<Key extends Comparable, Value> {
    void put(Key key, Value value);

    Value get(Key key);

    void delete(Key key);

    boolean contains(Key key);

    boolean isEmpty();

    int size();

    Iterable<Key> keys();
}
