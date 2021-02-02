package com.jarto.pq;

import com.jarto.pq.interfaces.MaxPriorityQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public abstract class PQTestBase<PQ extends MaxPriorityQueue<Integer>> {

    private PQ pq;

    protected abstract PQ createInstance();

    @BeforeEach
    public void setUp() {
        pq = createInstance();
    }

    @Test
    public void Should_ReturnMax() {
        int[] a = new int[]{1, 9, 2, 8, 3, 7, 4, 6, 5};

        for (int i = 0; i < a.length; i++) {
            pq.insert(a[i]);
        }

        int max = pq.delMax();
        assertEquals(9, max);
    }

    @Test
    public void Should_DeleteInDescendingOrder() {
        int[] a = new int[]{1, 9, 2, 8, 3, 7, 4, 6, 5};

        for (int i = 0; i < a.length; i++) {
            pq.insert(a[i]);
        }

        for (int i = a.length; i > 0; i--) {
            int max = pq.delMax();
            assertEquals(i, max);
        }
    }

    @Test
    public void Should_ThrowNoSuchElement_When_Empty() {

        pq.insert(1);
        pq.delMax();

        assertTrue(pq.isEmpty());
        assertThrows(NoSuchElementException.class, () -> pq.delMax());
    }
}
