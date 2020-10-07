/*Author Jack Webb 2020-10-05
**Last updated
**
**This is very similar to the API for Graph. The two important differences
**are that it is based on the Edge objects and that it adds the edges()
**method which provides the ability to iterate through all the graphs
**edges, ignoring self loops
*/
import java.io.*;
import java.util.*;

public class EdgeWeightedDigraph
{
    //Instance variable to determine nr of vertices, can't be updated
    private final int V;
    //Instance variable to determine nr of edges, can be updated
    private int E;
    //List implementation using Bag ADT
    private Bag<DirectedEdge>[] adj;

    //Constructor 1. Default
    public EdgeWeightedGraph(int V)
    {
        //Set nr of vertices to the given integer and set edges to 0
        this.V = V;
        E = 0;
        //Create the array of lists with size V.
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        //For each vertex (index) in the array, initialize their lists to empty
        for(int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }
    //Constructor 2. Reads graph from an input stream, in the format V
    //followed by E followed by a list of pairs of values between
    //0 and V-1
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
    //Getters for vertices and edges
    public int V()
    {
        return V;
    }
    public int E()
    {
        return E;
    }
    //Adds an edge to the Graph
    public void addEdge(Edge e)
    {
        adj[edge.from()].add(e);
        //Tally nr of edges
        E++;
    }

    public String toString()
    {
        return "Not sure what to do";
    }
    //Allows iteration through the vertices adjacent to a given vertex
    //returns bag at index v in the array
    public Iterable<DirectedEdge> adj(int v)
    {
        return adj[v];
    }
    public Iterable<DirectedEdge> edges()
    {
        //Create the bag for storing the edges
        Bag<DirectedEdge> b = new Bag<DirectedEdge>();
        //Gathering all the edges in the edge-weighted graph by looking
        //at each vertex, and for each edge connected to that vertex,
        //if the other vertex connected to that edge is greater than the
        //looked at vertex, add the edge.
        for(int v = 0; v < V; v++)
            for(DirectedEdge e : adj[v])
                b.add(e);
        return b;
    }
}
