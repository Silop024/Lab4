public class IndexMinPQ<Key extends Comparable<Key>>
{
    //Number of elements on priority queue
    private int N;
    //Maximum number of elements on priority queue
    private int maxN;
    //Binary heap
    private int[] pq;
    //Inverse of pq
    private int[] qp;
    //Items with priorities
    private Key[] keys;

    //Constructor, initializes an empty indexed priority queue with
    //indices between 0 and maxN -1
    public IndexMinPQ(int MaxN)
    {
        maxN = 264346;
        this.maxN = maxN;
        N = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];

        for(int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }
    //Changes the key associated with index i to the specified value
    public void change(int k, Key key)
    {
        keys[k] = key;

        swim(qp[k]);
        sink(qp[k]);
    }
    //Returns true if priority queue is empty
    public boolean isEmpty()
    {
        return N == 0;
    }
    //Returns true if k is associated with some item
    public boolean contains(int k)
    {
        return qp[k] != -1;
    }
    //Adds the new key at the end of the array, increments the size of
    //the heap and then swims up through the heap with that key to
    //restore the heap condition
    public void insert(int k, Key key)
    {
        //Increment N
        N++;

        qp[k] = N;
        //Add new element at the bottom
        pq[N] = k;
        keys[k] = key;
        //Restore the heap order
        swim(N);
    }
    // //Returns a minimal item
    // public Item min()
    // {
    //     return keys[pq[1]];
    // }
    //Takes the smallest key from the bottom, puts the item from the top
    //to the bottom, decrements the size of the heap and then sinks down
    //through the heap with that key to restore the heap condition
    public int delMin()
    {
        //Retrieve index from bottom
        int indexOfMin = pq[1];
        //Move pq[N] to pq[1], decrement the size of heap
        exch(1, N--);
        //Restore heap priority
        sink(1);
        //Set the now-unused position to null to reclaim memory
        keys[pq[N+1]] = null;
        qp[pq[N+1]] = -1;

        return indexOfMin;
    }
    //Returns true if item in index j is less than item in index i
    private boolean greater(int i, int j)
    {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }
    //Simple exchance method
    private void exch(int i, int j)
    {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
    //Exchanges the node with it's parent until order restored
    //Parent of the node at position k is position k/2.
    private void swim(int k)
    {
        //While parent is greater than child (k/2 > k)
        //Exchange them and update child (k goes to k/2)
        while(k > 1 && greater(k/2, k))
        {
            exch(k, k/2);
            k = k/2;
        }
    }
    //
    private void sink(int k)
    {
        while(2*k <= N)
        {
            int j = 2*k;
            if(j < N && greater(j, j+1))
                j++;
            if(!greater(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }
}
