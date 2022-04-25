public class DestinationCalculator {

    public static Coordinate getNewCoordinate(Move current, Action action){
        switch(action){
            case Left:
            case Right:
                return Turn.findDestination(current);
            case Bash:
            case Forward:
                return MoveInDirection.findDestination(current);
            default:
                throw new Error("Invalid Action");
        }

    }

    private static final DestinationFinder MoveInDirection = (Move current) -> new Coordinate(current.getI() + current.getDirection().i,  current.getJ() + current.getDirection().j);

    private static final DestinationFinder Turn = Move::getCoordinate;

}
