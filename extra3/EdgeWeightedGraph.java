import  java.util.*;

public class EdgeWeightedGraph
{
    private final int V;
    private int E;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V)
    {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for(int v = 0; v < V; v++)
            adj[v] = new Bag<Edge>();
    }

    public EdgeWeightedGraph(Scanner in)
    {
        //Reads V and calls constructor 1 with it
        this(in.nextInt());
        //Reads E
        int E = in.nextInt();
        //For all edges
        for(int i = 0; i < E; i++)
        {
            //Add an edge by reading two vertices and connecting them
            int v = in.nextInt();
            int w = in.nextInt();
            double weight = in.nextDouble();
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    public int V()
    {
        return V;
    }

    public int E()
    {
        return E;
    }

    public void addEdge(Edge e)
    {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v)
    {
        return adj[v];
    }

    public Iterable<Edge> edges()
    {
        //Create the bag for storing the edges
        Bag<Edge> b = new Bag<Edge>();
        //Gathering all the edges in the edge-weighted graph by looking at each vertex,
        //and for each edge connected to that vertex, if the other vertex connected to
        //that edge is greater than the looked at vertex, add the edge.
        for(int v = 0; v < V; v++)
            for(Edge e : adj[v])
                if(e.other(v) > v)
                    b.add(e);
        return b;
    }
}
