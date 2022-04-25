public class CoordinateFactory {
    public static Coordinate makeOrdinaryCoor(int i, int j){
        return new Coordinate(i,j);
    }

    public static Coordinate makeCoorWithPriority(int i, int j, int Priority){
        return new Coordinate(i,j, Priority);
    }
}
