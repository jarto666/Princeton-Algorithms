package com.jarto.uf;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickFindTest {

    @Test
    void Union_ConnectsComponents_SubsetsAreMerged() {
        var qf = new QuickFind(10);
        qf.union(0,5);
        qf.union(1,6);
        qf.union(1,2);
        qf.union(2,7);
        qf.union(3,8);
        qf.union(4,3);
        qf.union(4,9);

        assertEquals(3, qf.count());
    }
}