public class BFS
{
    //Is the shortest path to this vertex known?
    private boolean[] marked;
    //Last vertex on known path to this vertex from source
    private int[] edgeTo;
    //Source
    private final int s;

    public BFS(Graph G, int s)
    {
	    marked = new boolean[G.V()];
	    edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }
    //Marks all vertices connected to s
    private void bfs(Graph G, int s)
    {
	    Queue<Integer> q = new Queue<Integer>();
        //Mark the source as known
        marked[s] = true;
        //Put source in the queue
        q.enQ(s);
        while(!q.isEmpty())
	    {
	        //Remove next vertex from the queue
	        int v = q.deQ();
            //For every unmarked adjacent vertex
            for(int w : G.adj(v))
            if(!marked[w])
            {
                //Save last adge on a shortest path, mark it(now its known),add to q
                edgeTo[w] = v;
                marked[w] = true;
                q.enQ(w);
            }
        }
    }
    //Returns true if there is a path from s to v
    public boolean hasPathTo(int v)
    {
        return marked[v];
    }
    //Iterate through the vertices on a path from s to any vertex connected to s.
    //Gets a path from s to v such that no other path has fewer edges
    public Iterable<Integer> pathTo(int v)
    {
        //If no path from s to v, return null
        if(!hasPathTo(v))
            return null;
        //Create a stack of vertices?
        Stack<Integer> path = new Stack<Integer>();
        //Add all vertices on the known path from s to w, to stack
        for(int x = v; x != s; x = edgeTo[x])
        path.push(x);
        path.push(s); //Add source
        return path;
    }
}
