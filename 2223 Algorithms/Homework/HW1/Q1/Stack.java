import java.util.Iterator;

public class Stack<Item> implements Iterator<Item>{

    private Item[] a = (Item[]) new Object[1];
    private int N = 0; //num of items

    /**
     * Checks if the array is empty.
     * @return True if it is empty.
     */
    public boolean isEmpty()
    { return N == 0; }

    /**
     * Returns size of the array.
     * @return The number of items in the array.
     */
    public int size()
    { return N; }

    /**
     * Resizes the array to avoid using an inappropriate amount of memory.
     * @param max The new size the array will be changed too.
     */
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    /**
     * Places a new item at the top of the stack.
     * @param item The new item.
     */
    public void push(Item item)
    {
        if(N == a.length)
            resize(a.length*2);
        a[N++] = item;
    }

    /**
     * Removes and returns the top item of the stack.
     * @return The removed and returned item.
     */
    public Item pop()
    {
        Item item = a[--N];
        a[N] = null; //According to book this is necessary
        if (N > 0 && N == a.length / 4) // Book gave limit to resizing as 1/4 of the array being full
            resize(a.length / 2);
        return item;
    }

    /**
     * Checks if there is there is a next item.
     * @return True if there is.
     */
    public boolean hasNext()
    {
        return N > 0;
    }

    /**
     * Returns the next item in the array.
     * @return The next item.
     */
    public Item next()
    {
        return a[N++];
    }



}
