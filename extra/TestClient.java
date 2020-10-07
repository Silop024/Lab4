import java.io.*;
import java.util.*;

public class TestClient
{
    public static void main(String[] args) throws FileNotFoundException
    {
        File text = new File("C:/Users/chimp/Desktop/Datastructure/Lab4/extra/NYC.txt");
        Scanner sc = new Scanner(text);
        EdgeWeightedDigraph G = new EdgeWeightedGraph(sc);

        Scanner in = new Scanner(System.in);

        System.out.println("Enter A");
        int a = in.nextInt();
        System.out.println("Enter B");
        int b = in.nextInt();

        SP sp;

        System.out.println("Enter C or 0 if not wanted");
        int c = in.nextInt();

        if(c != 0)
            sp = new SP(G, c);
        else
            sp = new SP(G, a);

        if(sp.hasPathTo(a) && sp.hasPathTo(b))
        {
            if(c != 0)
            {
                System.out.print(a + " to " + b + " through " + c);
                System.out.printf(" (%4.2f): ", sp.distTo(a) + sp.distTo(b));
                Stack<Edge> path = new Stack<Edge>();
                for(Edge e : sp.pathTo(a))
                    path.push(e);
                for(Edge e : path)
                    path.pop();
                for(Edge e : sp.pathTo(b))
                    System.out.print(e.either() + "-");
            }
            else
            {
                System.out.print(a + " to " + b);
                System.out.printf(" (%4.2f): ", sp.distTo(b));
            }
        }
    }
}
