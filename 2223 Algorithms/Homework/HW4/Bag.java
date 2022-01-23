import java.util.Iterator;
public class Bag<Item> implements Iterable<Item>{
    public NODE first;

    public class NODE
    {
        Item item;
        NODE next;

        public Item getItem() { return this.item; }
        public NODE getNext() { return this.next; }
    }

    public int size()
    {
        int count = 0;
        for(NODE first = this.first; first.next != null; first = first.next)
        { count++; }
        return count;
    }

    public void add(Item item)
    { // like push() for stacks
        NODE oldFirst = first;
        first = new NODE();
        first.item = item;
        first.next = oldFirst;
    }

    public Iterator<Item> iterator()
    { return new ListIterator(); }

    private class ListIterator implements Iterator<Item> {
        private NODE current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }




}
