import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int count;

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node node = new Node(item);
        if (first == null) {
            last = node;
        } else {
            first.prev = node;
            node.next = first;
        }
        first = node;
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node node = new Node(item);
        if (last == null) {
            first = node;
        } else {
            last.next = node;
            node.prev = last;
        }
        last = node;
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item item = first.value;
        if (first.next == null) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        count--;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item item = last.value;
        if (last.prev == null) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        count--;

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addFirst(3);
        d.addLast( 4);
        d.addFirst(2);
        d.addLast( 5);
        d.addFirst(1);
        StdOut.println("Count = " + d.size());

        for (Integer i : d) {
            StdOut.print(i + " ");
        }

        StdOut.println();

        StdOut.println("Removed First = " + d.removeFirst());
        StdOut.println("Removed First = " + d.removeFirst());
        StdOut.println("Removed Last = " + d.removeLast());
        StdOut.println("Count = " + d.size());

        for (Integer i : d) {
            StdOut.print(i + " ");
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Node cur = first;

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = cur.value;
            cur = cur.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {

        Node(Item value) {
            this.value = value;
        }

        Node prev;
        Node next;
        Item value;
    }
}