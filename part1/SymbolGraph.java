/*Author Jack Webb
**Last updated
**
**A data type used to precess graphs defined using strings instead of integers
**to define and refer to vertices where vertex names are strings, a delimiter
**separates vertex names, each line in a file represents a set of edges and it
**connects the first vertex name on the line to each of the other vertices on
**the same line, and the number of vertices V and the number of edges E are
**both implicitly defined
*/




import java.util.*;
import java.io.*;

public class SymbolGraph
{
    //Unordered symbol table with keys as string and value as integer
    private ST<String, Integer> st;
    //The indices of the strings
    private String[] keys;
    //The graph
    private Graph G;

    //Constructor, builds graph specified in the text using delim to separate
    //vertex names
    public SymbolGraph(File text, String delim)
        throws FileNotFoundException
    {
        st = new ST<String, Integer>();
        //First pass
        Scanner in = new Scanner(text);
        //Builds the index by reading strings to associate each distinct
        //string with an index
        while(in.hasNextLine())
        {
            //Takes a line in the file and splits it up into several indexes of
            //a where there is a delimiter between
            String[] a = in.nextLine().split(delim);
            //Puts the findings in that line into the symbol table
            for(int i = 0; i < a.length; i++)
                if(!st.contains(a[i]))
                    st.put(a[i], st.size());
        }
        //Inverted index to get string keys to array
        keys = new String[st.size()];
        for(String name : st.keys())
            keys[st.get(name)] = name;
        //Second pass
        in = new Scanner(text);
        //Builds the graph by connecting the first vertex on each line to
        //all the others
        G = new Graph(st.size());
        while(in.hasNextLine())
        {
            String[] a = in.nextLine().split(delim);
            int v = st.get(a[0]);
            for(int i = 1; i < a.length; i++)
                G.addEdge(v, st.get(a[i]));
        }
    }
    //Returns true if key is a vertex in the graph
    public boolean contains(String s)
    {
        return st.contains(s);
    }
    //Converts a vertex name to an index
    //Returns index associated with a key
    public int index(String s)
    {
        return st.get(s);
    }
    //Converts an index from graph processing into a name
    //Returns key associated with index v
    public String name(int v)
    {
        return keys[v];
    }
    //Getter for the underlaying graph
    public Graph G()
    {
        return G;
    }
}
