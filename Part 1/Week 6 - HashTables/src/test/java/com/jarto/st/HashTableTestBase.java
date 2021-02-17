package com.jarto.st;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class HashTableTestBase<ST extends SymbolTable<String,Integer>> {

    private ST st;

    protected abstract ST createInstance();

    @BeforeEach
    public void setUp() {
        st = createInstance();
    }

    @Test
    void Should_ReturnValues_When_LessThan10Values() {

        for (Integer i = 0; i < 5; i++) {
            st.put(i.toString(), i);
        }

        for (Integer i = 0; i < 5; i++) {
            assertTrue(st.containsKey(i.toString()));
            assertEquals(i, st.get(i.toString()));
        }
    }

    // Check resizing
    @Test
    void Should_ReturnValues_When_MoreThan10Values() {
        for (Integer i = 0; i < 1000; i++) {
            st.put(i.toString(), i);
        }

        for (Integer i = 0; i < 1000; i++) {
            assertTrue(st.containsKey(i.toString()));
            assertEquals(i, st.get(i.toString()));
        }
    }

    @Test
    void Should_ReturnFalse_When_KeyDoesNotExist() {
        st.put("123", 123);
        assertFalse(st.containsKey("321"));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_RemovingNotExistingKey() {
        assertThrows(IllegalArgumentException.class, () -> st.remove("123"));
    }

    @Test
    void Should_RemoveKey_When_KeyExists() {
        for (Integer i = 0; i < 1000; i++) {
            st.put(i.toString(), i);
        }

        var keyToRemove = "999";
        st.remove(keyToRemove);
        assertFalse(st.containsKey(keyToRemove));

        for (Integer i = 0; i < 999; i++) {
            assertTrue(st.containsKey(i.toString()));
            assertEquals(i, st.get(i.toString()));
        }
    }

    @Test
    void Should_KeepDsInOrder_When_RemovingMoreThanHalf() {
        for (Integer i = 0; i < 1000; i++) {
            st.put(i.toString(), i);
        }

        for (Integer i = 0; i < 700; i++) {
            st.remove(i.toString());
        }

        for (Integer i = 0; i < 700; i++) {
            assertFalse(st.containsKey(i.toString()));
        }

        for (Integer i = 700; i < 1000; i++) {
            assertTrue(st.containsKey(i.toString()));
            assertEquals(i, st.get(i.toString()));
        }
    }
}
