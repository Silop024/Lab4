/*Author Jack Webb 2020-10-04
**Last updated
**
**Implementation of the Graph data type using adjacency list
*/
import java.io.*;
import java.util.*;

public class Digraph
{
    //Instance variable to determine nr of vertices, can't be updated
    private final int V;
    //Instance variable to determine nr of edges, can be updated
    private int E;
    //List implementation using Bag ADT
    private Bag<Integer>[] adj;

    //Constructor 1. Default
    public Digraph(int V)
    {
        //Set nr of vertices to the given integer and set edges to 0
        this.V = V;
        this.E = 0;
        //Create the array of lists with size V.
        adj = (Bag<Integer>[]) new Bag[V];
        //For each vertex (index) in the array, initialize their lists to empty
        for(int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }
    //Constructor 2. Reads graph from an input stream, in the format V
    //followed by E followed by a list of pairs of values between
    //0 and V-1
    public Digraph(Scanner in)
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
            addEdge(v, w);
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
    public void addEdge(int v, int w)
    {
        //Add w to v's list
        adj[v].add(w);
        //Tally nr of edges
        E++;
    }
    //Returns a copy of the digraph with all edges reversed
    public Digraph reverse()
    {
        //Initialize a new digraph with same size
        Digraph R = new Digraph(V);
        //For all vertices in digraph, and for all their adjacent vertices
        //add that edge but as w -> v instead of v -> w into new
        for(int v = 0; v < V; v++)
            for(int w : adj(V))
                R.addEdge(w, v);
        return R;
    }

    public String toString()
    {
        //Initialize the string
        String s = V + " vertices " + E + " edges\n";
        //For all vertices in the graph
        for(int v = 0; v < V; v++)
        {
            //Append the vertex into the string
            s += v + " ";
            //Call our iterator to get all the adjacent vertices to v
            //for each vertex w adjacemt to v
            for(int w : this.adj(v))
                //Append the vertex into the string
                s += w + " ";
            //Append new line to have space between the adjacency lists
            s += "\n";
        }
        return s;
    }
    //Allows iteration through the vertices adjacent to a given vertex
    //returns bag at index v in the array
    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }
}
