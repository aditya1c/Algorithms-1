/* *****************************************************************************
 *  Name:   Aditya Mamidi
 *  Date:   2020/04/29
 *  Description:    Randomized Queue implementation using Arrays
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int n = 0;

    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
    }

    private void resize(int cap) {
        Item[] arrNew =  (Item[]) new Object[cap];
        for (int i = 0; i < n; i++) {
            arrNew[i] = arr[i];
        }
        arr = arrNew;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (n == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {

        if (item == null) throw new IllegalArgumentException();

        if (arr.length == n) resize(arr.length*2);
        arr[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (n == 0) throw new NoSuchElementException();
        if (arr.length/4 == n) resize(arr.length/2);

        int i = StdRandom.uniform(n);
        Item itemToReturn = arr[i];

        if (i < n - 1) {
            arr[i] = arr[n - 1];
            arr[n - 1] = null;
        } else {
            arr[i] = null;
        }

        n--;
        return itemToReturn;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (n == 0) throw new NoSuchElementException();
        return arr[StdRandom.uniform(n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int current = 0;
        private final Item[] itemsToIterate = (Item[]) new Object[n];

        public RandomizedQueueIterator() {
            for (int i = 0; i < n; i++) itemsToIterate[i] = arr[i];
            StdRandom.shuffle(itemsToIterate);
        }

        public boolean hasNext() {
            return current < n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return itemsToIterate[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        System.out.println("Adding 1-5");
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("5");
        for (String s: queue) {
            System.out.print(s + " ");
        }
        System.out.printf("\nRandom item: %s\n", queue.sample());
        System.out.println("Removing 2 items");
        String a = queue.dequeue();
        System.out.println(a);
        queue.dequeue();
        for (String s: queue) {
            System.out.print(s + " ");
        }
        System.out.printf("\nSize: %d", queue.size());
    }

}