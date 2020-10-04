public class DFP
{
    //Has this vertex been visited?
    private boolean[] marked;
    //Last vertex on known path from s to w
    private int[] edgeTo;
    //Source
    private final int s;
    //Takes a source vertex s as argument and computes paths from s to
    //each vertex connected to s.
    public DFP(Graph G, int s)
    {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v)
    {
        //Mark vertex as having been visited
        marked[v] = true;
        //Recursivly visit all the vertices that are adjacent to it and
        //that have not yet been marked
        for(int w : G.adj(v))
            if(!marked[w])
            {
                //Remember path from the current vertex back to the start
                edgeTo[w] = v;
                dfs(G, w);
            }
    }
    //Returns true if there is a path from s to v
    public boolean hasPathTo(int v)
    {
        return marked[v];
    }
    //
    public String toString(Graph G)
    {
        return G.toString();
    }
    //Iterate through the vertices on a path from s to any vertex connected to s.
    public Iterable<Integer> pathTo(int v)
    {
        //If no path from s to v, return null
        if(!hasPathTo(v))
            return null;
        //Create a stack of vertices, them being the path
        Stack<Integer> path = new Stack<Integer>();
        //Add all vertices on the known path from s (exluded) to w
        for(int x = v; x != s; x = edgeTo[x])
            path.push(x);
        //Add source
        path.push(s);
        return path;
    }
}
