public class Node {
        char name;
        int index;
        Node pen;
        int distFromS;

    public Node(char name, int index) {
        this.name = name;
        this.index = index;
        this.pen = null;
        this.distFromS= (int) Double.POSITIVE_INFINITY;
    }


    public char getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public Node getPen() {
        return pen;
    }

    public int getDistFromS() {
        return distFromS;
    }

    public void setPen(Node pen) {
        this.pen = pen;
    }

    public void setDistFromS(int distFromS) {
        this.distFromS = distFromS;
    }
}
