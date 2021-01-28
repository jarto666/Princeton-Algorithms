package com.jarto.uf;

import static org.junit.jupiter.api.Assertions.*;

class QuickUnionOptimizedTest  extends UnionFindTestBase<QuickUnionOptimized> {

    @Override
    protected QuickUnionOptimized createInstance() {
        return new QuickUnionOptimized(10);
    }
}