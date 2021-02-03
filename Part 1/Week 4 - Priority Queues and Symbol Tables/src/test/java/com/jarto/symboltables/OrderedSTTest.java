package com.jarto.symboltables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedSTTest {

    @Test
    public void Should_GetValues_When_NotEmpty() {
        var st = new OrderedST<Character, Integer>();
        st.put('B', 1);
        st.put('A', 2);
        st.put('C', 3);
        st.put('D', 4);
        st.put('P', 5);
        st.put('M', 6);

        assertEquals(2, st.get('A'));
        assertEquals(1, st.get('B'));
        assertEquals(3, st.get('C'));
        assertEquals(4, st.get('D'));
        assertEquals(6, st.get('M'));
        assertEquals(5, st.get('P'));

        assertEquals(6, st.size());
    }

    @Test
    public void Should_RemoveValues_When_NotEmpty() {
        var st = new OrderedST<Character, Integer>();
        st.put('B', 1);
        st.put('A', 2);
        st.put('C', 3);
        st.put('D', 4);
        st.delete('C');
        st.put('P', 5);
        st.put('M', 6);
        st.delete('M');

        assertEquals(4, st.size());

        assertEquals(2, st.get('A'));
        assertEquals(1, st.get('B'));
        assertEquals(4, st.get('D'));
        assertEquals(5, st.get('P'));
        assertNull(st.get('C'));
        assertNull(st.get('M'));
    }
}