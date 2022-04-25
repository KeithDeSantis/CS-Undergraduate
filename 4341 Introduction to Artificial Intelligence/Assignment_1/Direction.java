public enum Direction {
    N(-1,0),S(1,0),E(0,1),W(0,-1);

    final int i;
    final int j;


    Direction(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
