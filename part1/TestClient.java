/*Author Jack Webb
**Last updated 2020-10-04
**
**A test client that can both test Deapth- and Breadth-First search
**Takes a text file with strings that act as our vertices and edges
*/


import java.util.*;
import java.io.*;

public class TestClient
{
    public static String source;
    public static String why;
    public static int s;
    public static int y;
    public static SymbolGraph sg;
    public static void main(String[] args) throws FileNotFoundException
    {
        File text = new File("C:/Users/chimp/Desktop/Datastructure/Lab4/part1/database.txt");
        String delim = " ";

        sg = new SymbolGraph(text, delim);

        Graph G = sg.G();

        System.out.println("Enter vertex X");
        Scanner in = new Scanner(System.in);
        source = in.next();
        s = sg.index(source);

        System.out.println("Enter vertex Y");
        why = in.next();
        y = sg.index(why);

        DFP deapthFirstPath = new DFP(G, s);
        BFS breadthFirstSearch = new BFS(G, s);


        System.out.println
        ("Do you want to use DeapthFirstPath or BreadthFirstSearch?");
        System.out.println("1 for DFP 2 for BFS 3 for both");
        int ans = in.nextInt();

        switch(ans)
        {
            case 3:
                printDFP(deapthFirstPath, y);
            case 2:
                printBFS(breadthFirstSearch, y);
                break;
            case 1:
                printDFP(deapthFirstPath, y);
                break;
        }

    }

    public static void printDFP(DFP dfp, int y)
    {
        if(dfp.hasPathTo(y))
        {
            System.out.print(source + " to " + why + ": ");
            for(int x : dfp.pathTo(y))
            {
                if(x == s)
                    System.out.print(sg.name(x));
                else
                    System.out.print("-" + sg.name(x));
            }
            System.out.println();
        }
        else
            System.out.println("There exists no such path");
    }

    public static void printBFS(BFS bfs, int y)
    {
        if(bfs.hasPathTo(y))
        {
            System.out.print(source + " to " + why + ": ");
            for(int x : bfs.pathTo(y))
            {
                if(x == s)
                    System.out.print(sg.name(x));
                else
                    System.out.print("-" + sg.name(x));
            }
            System.out.println();
        }
        else
            System.out.println("There exists no such path");
    }
}
