public class CostCalculator {

    public static int getNewCost(int totalCost, Action action, int[][] board, Move lastMove) throws OutBoundsError {
        Coordinate newCoordinate = DestinationCalculator.getNewCoordinate(lastMove, action);
        if(newCoordinate.getI() < 0 || newCoordinate.getJ() < 0 ||
                newCoordinate.getI() > board.length - 1 || newCoordinate.getJ() > board[0].length - 1 )
            throw new OutBoundsError();

        int terrainCost = board[newCoordinate.getI()][newCoordinate.getJ()];

        switch(action){
            case Left:
            case Right:
                return Turn.findCost(terrainCost, totalCost);
            case Bash:
                return Bash.findCost(terrainCost, totalCost);
            case Forward:
                return Forward.findCost(terrainCost, totalCost);
            default:
                throw new Error("Invalid Action");
        }

    }

    private static final CostFinder Forward = Integer::sum;

    private static final CostFinder Bash = (int terrainCost, int totalCost) -> 3 + totalCost;

    private static final CostFinder Turn = (int terrainCost, int totalCost) -> (int)Math.ceil(terrainCost * .5) + totalCost;
}
