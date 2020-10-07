public class MinIndexedDHeap<T extends Comparable<T>>
{

    // Current number of elements in the heap.
    private int size;

    // Maximum number of elements in the heap.
    private final int N;

    // The degree of every node in the heap.
    private final int D;

    // Lookup arrays to track the child/parent indexes of each node.
    private final int[] child, parent;

    // The Position Map (pm) maps Key Indexes (ki) to where the position of that
    // key is represented in the priority queue in the domain [0, sz).
    public final int[] pm;

    // The Inverse Map (im) stores the indexes of the keys in the range
    // [0, sz) which make up the priority queue. It should be noted that
    // 'im' and 'pm' are inverses of each other, so: pm[im[i]] = im[pm[i]] = i
    public final int[] im;

    // The values associated with the keys. It is very important  to note
    // that this array is indexed by the key indexes (aka 'ki').
    public final Object[] values;

    // Initializes a D-ary heap with a maximum capacity of maxSize.
    public IndexMinPQ(int degree, int maxSize)
    {
        D = max(2, degree);
        N = max(D + 1, maxSize);

        im = new int[N];
        pm = new int[N];
        child = new int[N];
        parent = new int[N];
        values = new Object[N];

        for (int i = 0; i < N; i++)
        {
            parent[i] = (i - 1) / D;
            child[i] = i * D + 1;
            pm[i] = im[i] = -1;
        }
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean contains(int ki)
    {
        return pm[ki] != -1;
    }

    public void insert(int ki, T value)
    {
        pm[ki] = size;
        im[size] = ki;
        values[ki] = value;
        swim(size++);
    }

    public void decrease(int ki, T value)
    {
        if (less(value, values[ki]))
        {
            values[ki] = value;
            swim(pm[ki]);
        }
    }

    private void sink(int i)
    {
        for (int j = minChild(i); j != -1; )
        {
            swap(i, j);
            i = j;
            j = minChild(i);
        }
    }

    private void swim(int i)
    {
        while (less(i, parent[i]))
        {
            swap(i, parent[i]);
            i = parent[i];
        }
    }

    private void swap(int i, int j)
    {
        pm[im[j]] = i;
        pm[im[i]] = j;
        int tmp = im[i];
        im[i] = im[j];
        im[j] = tmp;
    }

    private boolean less(int i, int j)
    {
        return ((Comparable<? super T>) values[im[i]]).compareTo((T) values[im[j]]) < 0;
    }

    private int minChild(int i)
    {
        int index = -1, from = child[i], to = min(sz, from + D);
        for (int j = from; j < to; j++) if (less(j, i)) index = i = j;
        return index;
    }
