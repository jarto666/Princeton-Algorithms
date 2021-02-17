package com.jarto.st;

import java.util.ArrayList;

public class HashTableSeparateChaining<Key, Value> implements SymbolTable<Key, Value> {

    private int M;
    private ArrayList<Node> buckets;
    private int size = 0;

    public HashTableSeparateChaining() {
        M = 10;
        buckets = new ArrayList<>(M);

        for (int i = 0; i < M; i++)
            buckets.add(i, null);
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Value get(Key key) {
        int i = hash(key);
        var node = buckets.get(i);

        while (node != null) {
            if (node.key.equals(key))
                return node.val;
            node = node.next;
        }
        return null;
    }

    @Override
    public void put(Key key, Value value) {
        int idx = hash(key);
        var node = buckets.get(idx);

        while (node != null) {
            if (node.key.equals(key)) {
                node.val = value;
                return;
            }
            node = node.next;
        }

        var newNode = new Node(key, value);
        newNode.next = buckets.get(idx);
        buckets.set(idx, newNode);

        size++;

        if ((float)size / M > 0.75) {
            M *= 2;
            rehash();
        }
    }

    @Override
    public boolean containsKey(Key key) {
        int i = hash(key);
        var node = buckets.get(i);

        while (node != null) {
            if (node.key.equals(key))
                return true;
            node = node.next;
        }

        return false;
    }

    @Override
    public Value remove(Key key) {
        if (!containsKey(key))
            throw new IllegalArgumentException();

        int i = hash(key);
        var node = buckets.get(i);
        Node prev = null;
        while (node != null) {
            if (node.key.equals(key))
                break;
            prev = node;
            node = node.next;
        }

        size--;
        if (prev == null) {
            buckets.set(i, node.next);
        } else {
            prev.next = node.next;
        }

        if ((float)size/M < 0.5) {
            M /= 1.5;
            rehash();
        }

        return node.val;
    }

    private void rehash() {
        var curBuckets = buckets;
        buckets = new ArrayList<>(M);
        for (int i = 0; i < M; i++)
            buckets.add(i, null);

        for (var n : curBuckets) {
            while (n != null) {
                put(n.key, n.val);
                n = n.next;
            }
        }
    }

    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }
}
