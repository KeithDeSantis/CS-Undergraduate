import java.util.Scanner;

public class Graph {
    private int V;
    private int E;
    public Bag<Integer>[] adj;

    public Graph(int v)
    {
        this.V = v;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int a = 0; a < v; a++)
            adj[a] = new Bag<Integer>();
    }

    public Graph(Scanner scan) //changed from In in, in.readInt() to scanner
    {
        this(scan.nextInt());
        int E = scan.nextInt();
        for (int i = 0; i < E; i++)
        { //Add an edge
            int v = scan.nextInt();
            int w = scan.nextInt();
            addEdge(v, w);
        }
    }

    public int V() { return V; }

    public int E() { return E; }

    public void addEdge(int v, int w)
    {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v)
    { return adj[v]; }

}
