import java.util.*;

public class AStar {

    HeuristicFunction.heuristicFunction heuristicFunction = HeuristicFunction.NoHeuristics; //TODO temporary for testing
    Map<Move,Move> cameFrom;
    Map<Move, Integer> costSoFar;
    Move firstMove = null;
    int numNodesExpanded = 0;
    ArrayList<Integer> numValidSteps = new ArrayList();

    public AStar(HeuristicFunction.heuristicFunction heuristicFunction){
        this.heuristicFunction = heuristicFunction;
    }

    public Result findPath(int[][] board, Coordinate s, Coordinate g) {
        Move lastMove = generatePath(board, s, g);

        if(lastMove == null)
            return null;

        ArrayList<Move> paths = buildPath(lastMove, firstMove);
        double score = 100 - lastMove.getTotalCost();
        float numActions = paths.size() - 1;
        //double averageBranchingFactor = numValidSteps.stream().mapToDouble(a -> a).average().getAsDouble();
        double averageBranchingFactor = Math.pow(numNodesExpanded, 1/numActions); //POSSIBLE OTHER WAY TO GET EFB through b^d = spaceComplexity?

        return new Result(score, (int)numActions, numNodesExpanded, paths, averageBranchingFactor);
    }

    //s and g should have its priority of 0
    //s should have default dir of N
    private Move generatePath(int[][] board, Coordinate s, Coordinate g){
        PriorityQueue<Move> pQueue = new PriorityQueue<Move>(Comparator.comparingInt(Move::getPriority));
        firstMove = new Move(s, 0);
        firstMove.setDirection(Direction.N);
        pQueue.add(firstMove);
        cameFrom = new HashMap<>();
        costSoFar = new HashMap<>();
        cameFrom.put(firstMove, null);
        costSoFar.put(firstMove,0);

        while(!pQueue.isEmpty()){
            Move currentMove = pQueue.poll();

            if(currentMove.getCoordinate().equals(g)) {
                return currentMove; // deals with edge case that start and goal are the same
            }
            int numValidMoves = 0;
            for (Action action: Action.values()) {

                if(!currentMove.isPossible(action)) continue;
                Move nextMove;
                try { nextMove = new Move(currentMove, action, board); }
                catch (OutBoundsError e) { continue; }

                Set<Move> keys = costSoFar.keySet();

                //update the queue accordingly
                if(!keys.contains(nextMove) || nextMove.getTotalCost() < costSoFar.get(nextMove)){
                    numValidMoves++;
                    costSoFar.put(nextMove, nextMove.getTotalCost());
                    int heuristic = heuristicFunction.getHeuristics(nextMove.getCoordinate(), g);
                    nextMove.setPriority(heuristic);
                    pQueue.add(nextMove);
                    numNodesExpanded++;
                    cameFrom.put(nextMove, currentMove);

                }
            }
            this.numValidSteps.add(numValidMoves);
        }
        return null;
    }

    public ArrayList<Move> buildPath(Move lastMove, Move firstMove) {

        ArrayList<Move> movesTaken = new ArrayList<Move>();
        Stack<Move> tempStack = new Stack();

        Move current = lastMove;

        // traverse path in reverse pushing moves onto the stack
        while(!current.equals(firstMove)){
            tempStack.add(current);
            current = cameFrom.get(current);
        }

        // Add the first move to the array
        movesTaken.add(firstMove);

        // Pop the stack to build the path from start to end
        while (!tempStack.isEmpty())
            movesTaken.add(tempStack.pop());

        return movesTaken;

    }
}

