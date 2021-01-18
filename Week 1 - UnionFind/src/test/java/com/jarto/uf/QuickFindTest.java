package com.jarto.uf;

class QuickFindTest extends UnionFindTestBase<QuickFind> {

    @Override
    protected QuickFind createInstance() {
        return new QuickFind(10);
    }
}