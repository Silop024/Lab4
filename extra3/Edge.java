public class Edge implements Comparable<Edge>
{
    //One vertex connected to an edge
    private final int v;
    //The other vertex
    private final int w;
    //Edge weight
    private final double weight;

    //Constructor
    public Edge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    //Returns the weight of the edge
    public double weight()
    {
        return weight;
    }
    //Returns either of the edges connected vertices (first one input in the pair?)
    public int either()
    {
        return v;
    }
    //Returns the other vertex connected to the same edge
    public int other(int vertex)
    {
        if(vertex == v)
            return w;
        else if(vertex == w)
            return v;
        else
            throw new RuntimeException("Inconsistent edge");
    }
    //Since Edge is not a primitive/wrapper type, we need our own compareTo method
    public int compareTo(Edge that)
    {
        if(this.weight() < that.weight())
        return -1;
        else if(this.weight() > that.weight())
        return 1;
        else
        return 0;
    }
    //String representation for printing
    public String toString()
    {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
