package com.jarto.symboltables;

public class BST<Key extends Comparable, Value> implements SymbolTable<Key, Value> {

    private Node root;
    private int count;

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
        count++;
    }

    private Node put(Node cur, Key key, Value value) {
        if (cur == null) return new Node(key, value);

        if (key.compareTo(cur.key) < 0)
            cur.left = put(cur.left, key, value);
        else if (key.compareTo(cur.key) > 0)
            cur.right = put(cur.right, key, value);
        else
            cur.value = value;

        return cur;
    }

    @Override
    public Value get(Key key) {
        Node cur = root;

        while (cur != null) {
            if (key.compareTo(cur.key) < 0)
                cur = cur.left;
            else if (key.compareTo(cur.key) > 0)
                cur = cur.right;
            else
                return cur.value;
        }

        return null;
    }

    @Override
    public void delete(Key key) {

        root = delete(root, key);

        count--;
    }

    private Node delete(Node cur, Key key) {
        if (cur == null) return null;
        if (key.compareTo(cur.key) < 0)
            cur.left = delete(cur.left, key);
        else if (key.compareTo(cur.key) > 0)
            cur.right = delete(cur.right, key);
        else {
            if (cur.right == null)
                return cur.left;
            if (cur.left == null)
                return cur.right;

            Node tmp = cur;
            cur = min(tmp.right);
            cur.right = delMin(cur.right);
            cur.left = tmp.left;
        }

        return cur;
    }

    private Node min(Node cur) {

        Node current = cur;
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    private void delMin() {
        root = delMin(root);
    }

    private Node delMin(Node cur) {
        if (cur.left == null) return cur.right;
        cur.left = delMin(cur.left);
        return cur;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}
