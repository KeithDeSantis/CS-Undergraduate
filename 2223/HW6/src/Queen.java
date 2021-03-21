public class Queen {
    int row;
    int col;

    public Queen(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString(){
        return "[" + row + "," + col +"]";
    }

}
