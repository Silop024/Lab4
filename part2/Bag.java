import java.util.Iterator;

public class Bag<Item> implements Iterable<Item>
{
    private Node first;

    private class Node
    {
        Item item;
        Node next;
    }

    public Bag()
    {
        first = null;
    }

    public void add(Item item)
    {
        Node old = first;
        first = new Node();
        first.item = item;
        first.next = old;
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
        //Not used but required
        public void remove() {}

        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
