import java.util.*;
import java.io.*;

public class TestClient
{
    public static void main(String[] args) throws FileNotFoundException
    {
        File text = new File("C:/Users/chimp/Desktop/Datastructure/Lab4/part1/database.txt");
        String delim = " ";

        SymbolGraph sg = new SymbolGraph(text, delim);

        Graph G = sg.G();

        System.out.println("What is the source vertex?");
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();

        DFP deapthFirstPath = new DFP(G, s);
        BFS breadthFirstSearch = new BFS(G, s);


        System.out.println
        ("Do you want to use DeapthFirstPath or BreadthFirstSearch?");
        System.out.println("1 for DFP 2 for BFS 3 for both");


        for(int v = 0; v < G.V(); v++)
        {
            System.out.print(s + " to " + v + ": ");
            if(deapthFirstPath.hasPathTo(v))
                for(int x : deapthFirstPath.pathTo(v))
                {
                    if(x == s)
                        System.out.print(x);
                    else
                        System.out.print("-" + x);
                }
            System.out.println();
        }
        System.out.println(G.toString());
    }
}
