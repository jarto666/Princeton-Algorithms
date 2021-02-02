package com.jarto.uf;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickUnionTest extends UnionFindTestBase<QuickUnion> {

    @Override
    protected QuickUnion createInstance() {
        return new QuickUnion(10);
    }
}