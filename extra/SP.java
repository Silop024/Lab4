/*Author Jack Webb
**Last updated
**
**
**
**
*/




public class SP
{
    //A vertex-indexed array, contains the distance to the source such
    //that distTo[v] is hte length of the shortest known path from s to v
    private double[] distTo;
    //Parent-edge representation in the form of a vertex-indexed array
    //contains the edges on the shortest-paths tree (as for DFS, BFS)
    private Edge[] edgeTo;
    //Index priority queue to keep track of vertices that are canditates
    //for being the next to be relaxed (removed from path)
    private IndexMinPQ<Double> pq;

    //Constructor, builds the shortest-paths tree and computes shortest-
    //paths distances.
    public SP(EdgeWeightedDigraph G, int s)
    {
        System.out.println(G.V());
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()]; //might not be new? typo in my notes?
        pq = new IndexMinPQ<Double>(G.V());

        //Initialize distTo[s] to 0 and all other distTo[] entreis to
        //positive infinity.
        //This is done by convention because all vertices that are not
        //reachable from s should return infinity.
        for(int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0;

        pq.insert(s, 0.0);
        //Relax and add to the SPT a non-tree vertex with the lowest
        //distTo[] value, continuing until all vertices are on the SPT
        //or no non-tree vertex has a finite distTo[] value
        while(!pq.isEmpty())
            relax(G, pq.delMin());
    }
    //To relax a vertex, means to test whether the best known way from s
    //to w is to go from s to v, then take the edge from v to w, and, if
    //so, update our data structures to indicate that to be the case
    private void relax(EdgeWeightedDigraph G, int v)
    {
        for(DirectedEdge e : G.adj(v))
        {
            int w = e.other(v);
            if(distTo[w] > distTo[v] + e.weight())
            {
                distTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;

                if(pq.contains(w))
                    pq.decrease(w, distTo[w]);
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }
    //Returns distance from s to v, infinity if no path exists
    public double distTo(int v)
    {
        return distTo[v];
    }
    //
    public DirectedEdge edgeTo(int v)
    {
        return edgeTo[v];
    }
    //Returns true if there exists a path from s to v
    public boolean hasPathTo(int v)
    {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    //Iterable object returns path from s to v, or null if no path exists
    //and a path with no edges if v is the source
    //For reachable vertices, we travel up the SPT, pushing edges that
    //we find on a stack, in the same manner as DFP, BFP.
    public Iterable<DirectedEdge> pathTo(int v)
    {
        if(!hasPathTo(v))
            return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        //Pushing the reachable edges that we find on the stack (path)
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }
}
