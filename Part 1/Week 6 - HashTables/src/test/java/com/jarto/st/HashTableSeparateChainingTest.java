package com.jarto.st;

class HashTableSeparateChainingTest extends HashTableTestBase<HashTableSeparateChaining<String, Integer>> {

    @Override
    protected HashTableSeparateChaining createInstance() {
        return new HashTableSeparateChaining();
    }
}