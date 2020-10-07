public class BellmanSP {

    private double[] distTo; // length of path to v
    private Edge[] edgeTo; // last edge on path to v
    private boolean[] onQ; // Is this vertex on the queue?
    private Queue<Integer> pq; // vertices being relaxed

    public BellmanSP(EdgeWeightedGraph G, int s)
    {
        distTo = new double[G.V()];
        edgeTo = new Edge[G.V()];
        onQ = new boolean[G.V()];
        pq = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        pq.enQ(s);
        onQ[s] = true;
        while (!pq.isEmpty()) {
            int v = pq.deQ();
            onQ[v] = false;
            relax(G, v);
        }
    }


    private void relax(EdgeWeightedGraph G, int v) {
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    pq.enQ(w);
                    onQ[w] = true;
                }
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Stack<Edge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<Edge>();
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.either()])
            path.push(e);
        return path;
    }

    public String toString(Stack<Edge> st) {
        StringBuilder sb = new StringBuilder();
        for (Edge e : st)
            sb.append(e.toString());
        return String.valueOf(sb);
    }


    public Stack<Edge> thePathTo(int b, int c) {
        double weight = 0;
        if (!hasPathTo(c)) return null;
        Stack<Edge> path = new Stack<Edge>();
        for (Edge e = edgeTo[c]; e != edgeTo[b]; e = edgeTo[e.either()]) {
            path.push(e);
            weight = weight + e.weight();
        }
        if (!hasPathTo(b)) return null;
        for (Edge e = edgeTo[b]; e != null; e = edgeTo[e.either()]) {
            path.push(e);
            weight = weight + e.weight();
        }

        System.out.println(weight);
        return path;
    }
}
