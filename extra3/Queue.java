import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>
{
    //Link to least recently added node
    private Node first;
    //Link to most recently added node
    private Node last;
    //Number of items on the queue
    private int N;

    //Nested class to define nodes
    private class Node
    {
        Item item;
        Node next;
    }
    //Default constructur creates an empty queue.
    public Queue()
    {
        first = null;
        last = null;
        N = 0;
    }
    //A method which is called to check if the list is empty or not.
    public boolean isEmpty()
    {
        return first == null;
    }
    /*A method which is called to check what size the list is. ie the amount of
    **elements/nodes currently in the list.
    */
    public int size()
    {
        return N;
    }
    //Adds item to the end of the queue
    public void enQ(Item item)
    {
        Node old = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty())
            first = last;
        else
            old.next = last;
        N++;
    }
    //Removes item from the beginning of the list
    public Item deQ()
    {
        Item item = first.item;
        first = first.next;
        if(isEmpty())
            last = null;
        N--;
        return item;
    }

    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext()
        {
            return current != null;
        }

        public void remove() {}

        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
