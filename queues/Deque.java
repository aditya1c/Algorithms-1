/* *****************************************************************************
 *  Name:   Aditya Mamidi
 *  Date:   2020/04/29
 *  Description:    Deque Implementation using LinkedList
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size = 0;
    private Node first;
    private Node last;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Null input to Deque is not allowed.");
        Node node = new Node();
        node.item = item;
        if (size != 0) {
            first.prev = node;
            node.next = first;
            first = node;
        } else {
            first = node;
            last = node;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Null input to Deque is not allowed.");
        Node node = new Node();
        node.item = item;
        if (size != 0) {
            node.prev = last;
            last.next = node;
            last = node;
        } else {
            first = node;
            last = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException("Deque is empty. Can't remove any item");
        Item item = first.item;
        if (first.next != null) {
            first = first.next;
            first.prev = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException("Deque is empty. Can't remove any item");
        Item item = last.item;
        if (last.prev != null) {
            last = last.prev;
            last.next = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Unsupported Operation for Deque Iterator");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();
        dq.addFirst(1);
        StdOut.print(dq.removeLast());
    }

}