/*Author Jack Webb 2020-10-05
**Last updated
**
**
**
**
*/
import java.lang.*;
public class DirectedEdge implements Comparable<DirectedEdge>
{
    //One vertex connected to an edge
    private final int v;
    //The other vertex connected to an edge
    private final int w;
    //Edge weight
    private final double weight;

    //constructor
    public DirectedEdge(int v, int w, double weight)
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
    //Returns one of the edges connected vertices (int this case v)
    public int from()
    {
        return v;
    }
    //Returns the other vertex connected to the same edge
    public int to()
    {
        return w;
    }
    //Own compareTo method since own data type
    //example e.compareTo(t)
    //Returns -1 if e < t
    //Returns 1 if e > t
    //Returns 0 if equal
    public int compareTo(Edge that)
    {
        if(this.weight < that.weight)
            return -1;
        else if(this.weight > that.weight)
            return 1;
        else
            return 0;
    }
    //String representation for printing
    public String toString()
    {
        return "Not sure what to do with this";
    }
}
