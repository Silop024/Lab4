/*Author Jack Webb 2020-10-04
**Last updated
**
**A test client that can test Deapth-first search
**Takes a text file with strings that act as our vertices and edges
*/
import java.util.*;
import java.io.*;

public class TestClient
{
    public static void main(String[] args) throws FileNotFoundException
    {
        File text = new File("C:/Users/chimp/Desktop/Datastructure/Lab4/part2/database.txt");
        String delim = " ";

        SymbolDigraph sg = new SymbolDigraph(text, delim);

        Digraph G = sg.G();

        System.out.println("Enter vertex X");
        Scanner in = new Scanner(System.in);
        String source = in.next();
        int s = sg.index(source);

        System.out.println("Enter vertex Y");
        String why = in.next();
        int y = sg.index(why);

        DirectedDFP deapthFirstPath = new DirectedDFP(G, s);

        if(deapthFirstPath.hasPathTo(y))
        {
            System.out.print(source + " to " + why + ": ");
            for(int x : deapthFirstPath.pathTo(y))
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
