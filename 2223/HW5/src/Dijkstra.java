import java.util.LinkedList;
import java.util.Scanner;

public class Dijkstra {
    int[][] matrix;
    Node start;
    Node end;

    public Dijkstra(int[][] matrix) {
        this.matrix = matrix;
        System.out.println("The shortest path is of length: " + setup());
        System.out.println("The path followed is:");
        for (Node n :
                getPath()) {
            System.out.println(n.getName());

        }
    }

    Node A = new Node('A', 0);
    Node J = new Node('J', 1);
    Node M = new Node('M', 2);
    Node R = new Node('R', 3);
    Node K = new Node('K', 4);
    Node S = new Node('S', 5);
    Node I = new Node('I', 6);
    Node N = new Node('N', 7);
    Node T = new Node('T', 8);
    Node D = new Node('D', 9);

    Node[] nodes = {A,J,M,R,K,S,I,N,T,D};

    LinkedList<Node> getPath(){

        LinkedList<Node> path = new LinkedList<Node>();
        if(start.equals(end)){
            path.add(start);
            return path;
        }
        path.add(0, end);
        Node curr = end.getPen();
        while(!curr.equals(start)){
            path.add(0, curr);
            curr = curr.getPen();
        }
        path.add(0, start);
        return path;
    }

    int setup() {
        Scanner in = new Scanner(System.in);

        System.out.println("Where would you like to start (A,J,M,R,K,S,I,N,T,D)?");
        String input = in.next();
        char s = input.toUpperCase().toCharArray()[0];

        System.out.println("Where would you like to end (A,J,M,R,K,S,I,N,T,D)?");
        input = in.next();
        char e = input.toUpperCase().toCharArray()[0];

        for(Node n:nodes){
            if(s==n.getName()){
                start = n;
            }if(e==n.getName()){
                end = n;
            }
        }if(start==null||end==null) {
            System.out.println("Invalid Node");
            return -1;
        }else if(start.equals(end)){
            return 0;
        }

        return path(start, end);

    }

    private int path(Node start, Node end) {
        Queue q = new Queue();
        start.setDistFromS(0);
        Node wNode = start;
        while(!wNode.equals(end)) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[wNode.getIndex()][i] > 0){
                    int distThru = wNode.distFromS + matrix[wNode.getIndex()][i];
                    if (nodes[i].getDistFromS() > distThru) {
                        nodes[i].setPen(wNode);
                        nodes[i].setDistFromS(distThru);
                        q.add(nodes[i]);
                    }
                }
            }
            wNode = q.next();
        }return end.getDistFromS();
    }

}
