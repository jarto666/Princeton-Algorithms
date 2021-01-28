import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int count;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        if (count == items.length)
            resize(items.length * 2);

        items[count++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (count < items.length / 4)
            resize(items.length / 2);

        int i = StdRandom.uniform(count);
        Item val = items[i];
        if (i == count-1) {
            items[i] = null;
        } else {
            items[i] = items[count-1];
            items[count-1] = null;
        }

        count--;
        return val;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();

        int i = StdRandom.uniform(count);
        return items[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
//        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
//        rq.enqueue(1);
//        rq.enqueue(2);
//        rq.enqueue(3);
//        rq.enqueue(4);
//        rq.enqueue(5);
//
//        for (Integer i : rq) {
//            StdOut.print(i + " ");
//        }
//
//        for (Integer i : rq) {
//            StdOut.print(i + " ");
//        }
//        StdOut.println();
//
//        StdOut.println("Removed Random = " + rq.dequeue());
//        StdOut.println("Removed Random = " + rq.dequeue());
//        StdOut.println("Removed Random = " + rq.dequeue());
//        StdOut.println("Removed Random = " + rq.dequeue());
//        StdOut.println("Removed Random = " + rq.dequeue());

        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(33);
        rq.enqueue(18);
        rq.enqueue(10);
        rq.enqueue(37);
        rq.enqueue(6);
        rq.enqueue(9);
        rq.enqueue(6);
        rq.enqueue(13);
        rq.enqueue(39);

        for (Integer i : rq) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        for (Integer i : rq) {
            StdOut.print(i + " ");
        }
    }

    private void resize(int newCapacity) {
        items = Arrays.copyOf(items, newCapacity);
    }

    private class QueueIterator implements Iterator<Item> {

        private int num = 0;
        private int[] order;

        public QueueIterator() {
            order = new int[count];
            for (int i = 0; i < count; ++i)
                order[i] = i;
            StdRandom.shuffle(order);
        }

        @Override
        public boolean hasNext() {
            return num != count;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return items[order[num++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}