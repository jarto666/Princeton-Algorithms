package com.jarto.st;

public interface SymbolTable<Key, Value> {
    int size();

    Value get(Key key);

    void put(Key key, Value value);

    boolean containsKey(Key key);

    Value remove(Key key);
}
