package com.jarto.uf;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickUnionTest extends UnionFindTestBase<QuickUnion> {

    @Override
    protected QuickUnion createInstance() {
        return new QuickUnion(10);
    }

//    // {0, 5, 6}, {1, 2}, {7}, {3, 4, 8, 9}
//    @Test
//    void Should_ReturnNumberOfSubsets_When_MergedMultipleTimes() {
//        var qu = new QuickUnion(10);
//        qu.union(4,3);
//        qu.union(3,8);
//
//        assertEquals(8, qu.count());
//
//        qu.union(6,5);
//        qu.union(9,4);
//        qu.union(2,1);
//        qu.union(5,0);
//
//        assertEquals(4, qu.count());
//    }
//
//    // {1, 2} {0, 3}
//    @Test
//    void Should_NotBeConnected_When_InDifferentSubsets() {
//        var qu = new QuickUnion(4);
//        qu.union(1,2);
//        qu.union(0,3);
//
//        assertFalse(qu.connected(1, 3));
//    }
//
//    // {0, 1, 2, 3}
//    @Test
//    void Should_BeConnected_When_InSameSubset() {
//        var qu = new QuickUnion(4);
//        qu.union(1,2);
//        qu.union(0,3);
//        qu.union(0,2);
//
//        assertTrue(qu.connected(1, 3));
//    }
}