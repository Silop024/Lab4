import java.util.*;
import java.io.*;

public class TestClient
{
    public static void main(String[] args) throws FileNotFoundException
    {
        File f = new File("/home/jack/Desktop/DataStructures/Lab4/part1/test.txt");
        Scanner in = new Scanner(f);
        Graph G = new Graph(in);
        int s = 0;
        DFP search = new DFP(G, s);

        for(int v = 0; v < G.V(); v++)
        {
            System.out.print(s + " to " + v + ": ");
            if(search.hasPathTo(v))
                for(int x : search.pathTo(v))
                {
                    if(x == s)
                        System.out.print(x);
                    else
                        System.out.print("-" + x);
                }
            System.out.println();
        }
    }
}
