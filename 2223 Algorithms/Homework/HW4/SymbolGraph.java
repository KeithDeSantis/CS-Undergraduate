import java.util.Scanner;

public class SymbolGraph {

    public ST<String, Integer> st;
    public ST<String, Node> nodeST;
    public String[] keys;
    public Graph G;

    public SymbolGraph(int vertices)
    {
        this.G = new Graph(75);
        this.st = new ST<String, Integer>();
        this.nodeST = new ST<String, Node>();
        this.keys = new String[vertices];
    }

    public boolean contains(String s) { return st.contains(s); }

    public int index(String s) { return st.get(s); }

    public Node getNode(String s) { return nodeST.get(s); }

    public String name(int v) { return keys[v]; }

    public Graph G() { return G; }
}
