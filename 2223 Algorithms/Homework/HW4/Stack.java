import java.util.Iterator;
import java.util.ListIterator;

public class Stack<Item> implements Iterable<Item> {
    public Node first;
    public int N;

    public class Node
    {
        Item item;
        Node next;

        public Item getItem() { return item; }
    }
    public boolean isEmpty() { return first == null; }
    public int size() { return N; }

    public void push(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item pop()
    {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public Iterator<Item> iterator()
    { return new ListIterator(); }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext()
        { return current != null; }
        public void remove() { }
        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
