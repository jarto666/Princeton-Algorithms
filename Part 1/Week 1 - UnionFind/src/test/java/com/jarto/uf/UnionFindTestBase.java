package com.jarto.uf;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class UnionFindTestBase<T extends UnionFind> {

    private T instance;

    protected abstract T createInstance();

    @BeforeEach
    public void setUp() {
        instance = createInstance();
    }

    // {0, 5, 6}, {1, 2}, {7}, {3, 4, 8, 9}
    @Test
    void Should_ReturnNumberOfSubsets_When_MergedMultipleTimes() {
        instance.union(3,4);
        instance.union(3,8);

        assertEquals(8, instance.count());

        instance.union(5,6);
        instance.union(4,9);
        instance.union(1,2);
        instance.union(0,5);

        assertEquals(4, instance.count());
    }

    // {1, 2} {0, 3}
    @Test
    void Should_NotBeConnected_When_InDifferentSubsets() {
        instance.union(1,2);
        instance.union(0,3);

        assertFalse(instance.connected(1, 3));
    }

    // {0, 1, 2, 3}
    @Test
    void Should_BeConnected_When_InSameSubset() {
        instance.union(1,2);
        instance.union(0,3);
        instance.union(0,2);

        assertTrue(instance.connected(1, 3));
    }
}
