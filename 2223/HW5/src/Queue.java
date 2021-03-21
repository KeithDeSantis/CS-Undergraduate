import java.util.LinkedList;

public class Queue {
    LinkedList<Node> nodes = new LinkedList<>();

    void add(Node n){
        nodes.remove(n);
        if(nodes.isEmpty()){
            nodes.add(n);
        }else if(n.getDistFromS()>nodes.get(nodes.size()-1).getDistFromS()){
            nodes.addLast(n);
        }else{
            for(int i = 0; i < nodes.size(); i++){
                if(n.distFromS < nodes.get(i).distFromS) {
                    nodes.add(i, n);
                    break;
                }
            }
        }

    }

    Node next(){
        Node n = nodes.getFirst();
        nodes.remove(n);
        return n;
    }
}


