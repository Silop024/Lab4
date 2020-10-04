public class ST<Key, Value>
{
    private Node first;
    private int N;

    private class Node
    {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next)
        {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    //Creates a sumbol table
    public ST()
    {
        first = null;
        N = 0;
    }
    //Put key-value pair into the table
    public void put(Key key, Value val)
    {
        for(Node x = first; x != null; x = x.next)
            if(key.equals(x.key))
            {
                x.val = val;
                return;
            }
        first = new Node(key, val, first);
        N++;
    }
    //Get value paired with key, null if absent
    public Value get(Key key)
    {
        for(Node x = first; x != null; x = x.next)
            if(key.equals(x.key))
                return x.val;
        return null;
    }
    //Returns true if there is a value paired with the key
    public boolean contains(Key key)
    {
        return get(key) != null;
    }
    //Returns number of key-value pairs in the table
    public int size()
    {
        return N;
    }
    //Returns all keys in the table as an iterable object
    public Iterable<Key> keys()
    {
        Queue<Key> q = new Queue<Key>();
        for(Node x = first; x != null; x = x.next)
            q.enQ(x.key);
        return q;
    }

}
