package com.jarto.uf;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickFindTest extends UnionFindTestBase<QuickFind> {

    @Override
    protected QuickFind createInstance() {
        return new QuickFind(10);
    }

//    // {0, 5} {1, 2, 6, 7} {3, 4, 8, 9}
//    @Test
//    void Should_ReturnNumberOfSubsets_When_MergedMultipleTimes() {
//        var qf = new QuickFind(10);
//        qf.union(0,5);
//        qf.union(1,6);
//        qf.union(1,2);
//
//        assertEquals(7, qf.count());
//
//        qf.union(2,7);
//        qf.union(3,8);
//        qf.union(4,3);
//        qf.union(4,9);
//
//        assertEquals(3, qf.count());
//    }
//
//    // {1, 2} {0, 3}
//    @Test
//    void Should_NotBeConnected_When_InDifferentSubsets() {
//        var qf = new QuickFind(4);
//        qf.union(1,2);
//        qf.union(0,3);
//
//        assertFalse(qf.connected(1, 3));
//    }
//
//    // {0, 1, 2, 3}
//    @Test
//    void Should_BeConnected_When_InSameSubset() {
//        var qf = new QuickFind(4);
//        qf.union(1,2);
//        qf.union(0,3);
//        qf.union(0,2);
//
//        assertTrue(qf.connected(1, 3));
//    }
}