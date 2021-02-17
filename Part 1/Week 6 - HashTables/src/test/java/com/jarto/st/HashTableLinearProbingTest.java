package com.jarto.st;

import static org.junit.jupiter.api.Assertions.*;

class HashTableLinearProbingTest extends HashTableTestBase<HashTableLinearProbing<String, Integer>> {

    @Override
    protected HashTableLinearProbing createInstance() {
        return new HashTableLinearProbing();
    }
}